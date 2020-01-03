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
public class BettingCommandHandler {
	
	private static Logger logger = LoggerFactory.getLogger(BettingCommandHandler.class);
	
	private final BettingService bettingService;
	
	@Autowired
	public BettingCommandHandler(BettingService bettingService) {
		this.bettingService = bettingService;
	}
	
	@StreamListener(Channels.CREATE_WALLET)
	@SendTo(Channels.WALLET_CREATED)
	public CreateWalletRequest createWallet(CreateWalletRequest createWalletRequest) {
		System.out.println(createWalletRequest.toString());
		Wallet existingWallet = bettingService.getWalletFromUser(createWalletRequest.getOwnerId());
		if(existingWallet != null) {
			logger.info("Can't create wallet for member(" + createWalletRequest.getOwnerId() + ") becouse it already exists.");
			createWalletRequest.setTokens(existingWallet.getTokens());
			return createWalletRequest;
		}
		logger.info("New wallet for member(" + createWalletRequest.getOwnerId() + ")added with " + createWalletRequest.getTokens() + " tokens.");
		bettingService.saveWallet(new Wallet(createWalletRequest.getOwnerId(), createWalletRequest.getTokens()));
		return createWalletRequest;
	}
}
