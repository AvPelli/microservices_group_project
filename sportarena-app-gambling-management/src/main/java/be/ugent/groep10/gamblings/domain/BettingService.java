package be.ugent.groep10.gamblings.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import be.ugent.groep10.gamblings.adapters.messaging.CashOutRequest;
import be.ugent.groep10.gamblings.adapters.messaging.CashOutResponse;
import be.ugent.groep10.gamblings.adapters.messaging.Channels;
import be.ugent.groep10.gamblings.adapters.messaging.CreateGameRequest;
import be.ugent.groep10.gamblings.adapters.messaging.CreateWalletRequest;
import be.ugent.groep10.gamblings.adapters.messaging.EndGameRequest;
import be.ugent.groep10.gamblings.adapters.messaging.MessageGateway;
import be.ugent.groep10.gamblings.adapters.messaging.PaymentRequest;
import be.ugent.groep10.gamblings.adapters.messaging.PaymentResponse;
import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.BettableGameRepository;
import be.ugent.groep10.gamblings.persistence.WalletRepository;

@Service
public class BettingService {

	private final BetRepository betRepository;
	private final BettableGameRepository bettableGameRepository;
	private final WalletRepository walletRepository;
	private final MessageGateway messageGateway;
	
	private static Logger logger = LoggerFactory.getLogger(BettingService.class);
	
	public final Set<PayTokensListener> payTokensListeners;
	public final Set<CashOutListener> cashOutListeners;
	
	private final double MONEY_TO_TOKEN_RATIO;
	
	@Autowired
	public BettingService(BetRepository betRepository, BettableGameRepository bettableGameRepository,
			WalletRepository walletRepository, MessageGateway messageGateway) {
		this.betRepository = betRepository;
		this.bettableGameRepository = bettableGameRepository;
		this.walletRepository = walletRepository;
		this.messageGateway = messageGateway;
		this.payTokensListeners = new HashSet<>(10);
		this.cashOutListeners = new HashSet<>(10);
		this.MONEY_TO_TOKEN_RATIO = 0.2;
	}
	
	public Bet addBet(String matchId, String ownerId, Prediction prediction, double tokens) {
		BettableGame game = (BettableGame) bettableGameRepository.findByMatchId(matchId);
		Wallet wallet = walletRepository.findById(ownerId).get();
		if(game != null && wallet != null && wallet.getTokens() >= tokens) {
			Bet bet = new Bet(ownerId, matchId, prediction, tokens);
			betRepository.save(bet);
			wallet.setTokens(wallet.getTokens() - tokens);
			walletRepository.save(wallet);
			return bet;
		}
		return null;
	}
	
	public void gameCreated(CreateGameRequest createGameRequest) {
		BettableGame game = new BettableGame(createGameRequest.getSportEventId(), createGameRequest.getDateTimeBegin());
		bettableGameRepository.save(game);
	}
	
	public void gameEnded(EndGameRequest endGameRequest) {
		BettableGame game = (BettableGame) bettableGameRepository.findByMatchId(endGameRequest.getSportEventId());
		
		List<Bet> bets = betRepository.findBetsByBettableGameId(endGameRequest.getSportEventId());
		for(Bet bet : bets) {
			Wallet wallet = walletRepository.findByOwnerId(bet.getPlacedByMember()).get();
			if(wallet != null && endGameRequest.getScore().getScoreA() == bet.getPrediction().getPoinstTeamA() 
					&& endGameRequest.getScore().getScoreB() == bet.getPrediction().getPointsTeamB()) {
				wallet.setTokens(wallet.getTokens() + bet.getTokensInvested()*bet.getPrediction().getRatio());
			}
			
			
		}
		
		
		bettableGameRepository.delete(game);
	}
	
	
	public void registerPayTokensListener(PayTokensListener payTokensListener) {
		this.payTokensListeners.add(payTokensListener);
	}
	
	public void registerCashOutListener(CashOutListener cashOutListener) {
		this.cashOutListeners.add(cashOutListener);
	}
	
	public void payTokens(String ownerId, double tokens) throws NotEnoughTokensException,TransactionHappeningException, MemberDoesntExistException {
		
		
		//TODO check if possible + add tokens + tokensBeingTraded  
		Optional<Wallet> walletOption = walletRepository.findByOwnerId(ownerId);
		if(walletOption.get() == null) {
			throw new MemberDoesntExistException("Member doesn't exist");
		}
		Wallet wallet = walletOption.get();
		if(wallet.getTokensBeingTraded() != 0) {
			throw new TransactionHappeningException("Transaction already happening");
		}
		wallet.setTokens(wallet.getTokens() + tokens);
		wallet.setTokensBeingTraded(wallet.getTokensBeingTraded() - tokens);
		walletRepository.save(wallet);
		
		PaymentRequest paymentRequest = new PaymentRequest(ownerId, tokens*MONEY_TO_TOKEN_RATIO, tokens);
			
		logger.info("Pay tokens started.");
		this.messageGateway.payTokens(paymentRequest);
	}
	
	public void cashOut(String ownerId, double tokens) throws MemberDoesntExistException,TransactionHappeningException,  NotEnoughTokensException {
		
		// TODO check if possible +  remove tokens + tokensBeingTraded  
		Optional<Wallet> walletOption = walletRepository.findByOwnerId(ownerId);
		if(walletOption.get() == null) {
			throw new MemberDoesntExistException("Member doesn't exist");
		}
		Wallet wallet = walletOption.get();
		if(wallet.getTokensBeingTraded() != 0) {
			throw new TransactionHappeningException("Transaction already happening");
		}
		if(wallet.getTokens() < tokens) {
			throw new NotEnoughTokensException("Not enough tokens to cash out");
		}
		wallet.setTokens(wallet.getTokens() - tokens);
		wallet.setTokensBeingTraded(wallet.getTokensBeingTraded() + tokens);
		walletRepository.save(wallet);
		
		CashOutRequest cashOutRequest = new CashOutRequest(ownerId, tokens*MONEY_TO_TOKEN_RATIO, tokens);
		
		logger.info("Cash Out started.");
		this.messageGateway.cashOut(cashOutRequest);;
	}
	
	public void payTokensTimeout(String ownerId, double tokens) {
		Optional<Wallet> walletOption = walletRepository.findByOwnerId(ownerId);
		Wallet wallet = walletOption.get();
		wallet.setTokens(wallet.getTokens() - tokens);
		wallet.setTokensBeingTraded(0);
		this.messageGateway.payTokensTimeout(new PaymentRequest(ownerId, tokens/MONEY_TO_TOKEN_RATIO, tokens));
	}
	
	public void cashOutTimeout(String ownerId, double tokens) {
		Optional<Wallet> walletOption = walletRepository.findByOwnerId(ownerId);
		Wallet wallet = walletOption.get();
		wallet.setTokens(wallet.getTokens() + tokens);
		wallet.setTokensBeingTraded(0);
		this.messageGateway.cashOutTimeout(new CashOutRequest(ownerId, tokens/MONEY_TO_TOKEN_RATIO, tokens));
	}
	
	public void cashOutProcessResult(CashOutResponse cashOutResponse) {
		
		Optional<Wallet> walletOption = walletRepository.findByOwnerId(cashOutResponse.getMemberId());
		Wallet wallet = walletOption.get();
		
		wallet.setTokensBeingTraded(0);
		if(!cashOutResponse.isSucceeded()) {
			wallet.setTokens(wallet.getTokens() - cashOutResponse.getAmountOfTokens());
		}
		walletRepository.save(wallet);
	}
	
	
	
	public void payTokensProcessResult(PaymentResponse paymentResponse) {
		
		Optional<Wallet> walletOption = walletRepository.findByOwnerId(paymentResponse.getMemberId());
		Wallet wallet = walletOption.get();
		
		wallet.setTokensBeingTraded(0);
		if(!paymentResponse.isSucceeded()) {
			wallet.setTokens(wallet.getTokens() + paymentResponse.getAmountOfTokens());
		}
		
		walletRepository.save(wallet);
	}
	
	
	
	
	
	
	public List<Prediction> generatePredictions(String matchId) {
		List<Prediction> predictions = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				double winChance = 2 + i*i + j*j + (i-j)*(i-j);
				predictions.add(new Prediction(i, j, winChance));
				if(i != j) {
					predictions.add(new Prediction(j, i, winChance));
				}
			}
		}
		return predictions;
	}
	
	
	public Wallet getWalletFromUser(String userId) {
		return walletRepository.findByOwnerId(userId).orElse(null);
	}
	
	public Wallet saveWallet(Wallet wallet) {
		System.out.println("Saving: " + wallet.toString());
		if(walletRepository.findByOwnerId(wallet.getOwnerId()).isPresent()){
			this.messageGateway.walletCreated(new CreateWalletRequest(wallet.getOwnerId(), -1));
			return null;
		}
		this.messageGateway.walletCreated(new CreateWalletRequest(wallet.getOwnerId(), wallet.getTokens()));
		return walletRepository.save(wallet);
	}
	
	
	
	
	
	
	
	
	

}
