package be.ugent.groep10.gamblings.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.BettableGameRepository;
import be.ugent.groep10.gamblings.persistence.WalletRepository;

@Service
public class BettingService {
	private final BetRepository betRepository;
	private final BettableGameRepository bettableGameRepository;
	private final WalletRepository walletRepository;
	
	@Autowired
	public BettingService(BetRepository betRepository, BettableGameRepository bettableGameRepository,
			WalletRepository walletRepository) {
		this.betRepository = betRepository;
		this.bettableGameRepository = bettableGameRepository;
		this.walletRepository = walletRepository;
	}
	
	public Wallet getWalletFromUser(long userId) {
		return walletRepository.findByOwnerId(userId).orElse(null);
	}
	
	public Wallet saveWallet(Wallet wallet) {
		System.out.println("Saving: " + wallet.toString());
		return walletRepository.save(wallet);
	}
}
