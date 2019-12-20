package be.ugent.groep10.gamblings.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String NEW_EVENT = "events";
	static final String EVENT_REGISTERED  = "registered_events";
	static final String CREATE_WALLET = "create_wallet";
	static final String WALLET_CREATED = "wallet_created";
	
	@Input(NEW_EVENT)
	SubscribableChannel newEvent();
	
	@Output(EVENT_REGISTERED)
	MessageChannel eventRegistered();
	
	@Input(CREATE_WALLET)
	SubscribableChannel createWallet();
	
	@Output(WALLET_CREATED)
	MessageChannel walletCreated();
	
}
