package be.ugent.groep10.gamblings.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.groep10.gamblings.adapters.messaging.CreateWalletRequest;
import be.ugent.groep10.gamblings.adapters.messaging.MessageGateway;
import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.BettableGameRepository;
import be.ugent.groep10.gamblings.persistence.WalletRepository;

@Service
public class BettingService {
	private final BetRepository betRepository;
	private final BettableGameRepository bettableGameRepository;
	private final WalletRepository walletRepository;
	private final MessageGateway messageGateway;
	
	@Autowired
	public BettingService(BetRepository betRepository, BettableGameRepository bettableGameRepository,
			WalletRepository walletRepository, MessageGateway messageGateway) {
		this.betRepository = betRepository;
		this.bettableGameRepository = bettableGameRepository;
		this.walletRepository = walletRepository;
		this.messageGateway = messageGateway;
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
