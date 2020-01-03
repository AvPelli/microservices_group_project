package be.ugent.groep10.membermanagement.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	
	static final String CREATE_WALLET = "create_wallet";
	static final String WALLET_CREATED = "wallet_created";
	
	
	@Output(CREATE_WALLET)
	MessageChannel createWallet();
	
	@Input(WALLET_CREATED)
	SubscribableChannel processWalletResponse();

}
