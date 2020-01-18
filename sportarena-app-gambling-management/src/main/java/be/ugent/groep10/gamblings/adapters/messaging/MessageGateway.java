package be.ugent.groep10.gamblings.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;


@MessagingGateway
public interface MessageGateway {
	
	@Gateway(requestChannel = Channels.WALLET_CREATED)
	public void walletCreated(CreateWalletRequest createWalletRequest);

}
