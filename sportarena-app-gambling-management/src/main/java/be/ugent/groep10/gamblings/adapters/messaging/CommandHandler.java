package be.ugent.groep10.gamblings.adapters.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import be.ugent.groep10.gamblings.domain.BettingService;
import be.ugent.groep10.gamblings.domain.Wallet;

@Service
public class CommandHandler {
	
	private static Logger logger = LoggerFactory.getLogger(CommandHandler.class);
	
	private final BettingService bettingService;
	
	@Autowired
	public CommandHandler(BettingService bettingService) {
		this.bettingService = bettingService;
	}
	
	@StreamListener(Channels.CREATE_WALLET)
	public void createWallet(CreateWalletRequest createWalletRequest) {
		bettingService.saveWallet(new Wallet(createWalletRequest.getOwnerId(), createWalletRequest.getTokens()));
	}
	
	@StreamListener(Channels.GAME_CREATED_EVENT)
	public void gameCreated(CreateGameRequest createGameRequest) {
		bettingService.gameCreated(createGameRequest);
	}
	
	@StreamListener(Channels.GAME_ENDED_EVENT)
	public void gameEnded(EndGameRequest endGameRequest) {
		bettingService.gameEnded(endGameRequest);
	}
	
	@StreamListener(Channels.CASH_OUT_RESULT)
	public void cashOutResult(CashOutResponse cashOutResponse) {
		bettingService.cashOutListeners.forEach(l -> l.onRegisterResult(cashOutResponse));
	}
	
	@StreamListener(Channels.PAY_TOKENS_RESULT)
	public void payTokensResult(PaymentResponse paymentResponse) {
		bettingService.payTokensListeners.forEach(l -> l.onRegisterResult(paymentResponse));
	}
	
	
}
