package be.ugent.groep10.gamblings.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String GAME_CREATED_EVENT = "game_created_event";
	static final String EVENT_REGISTERED  = "registered_events";
	
	static final String CREATE_WALLET = "create_wallet";
	static final String WALLET_CREATED = "wallet_created";
	
	static final String CASH_OUT = "cash_out";
	static final String PAY_TOKENS = "pay_tokens";
	
	
	//Arena mgmt
	@Input(GAME_CREATED_EVENT)
	SubscribableChannel newEvent();
	
	@Output(EVENT_REGISTERED)
	MessageChannel eventRegistered();
	
	//Member mgmt
	@Input(CREATE_WALLET)
	SubscribableChannel createWallet();
	
	@Output(WALLET_CREATED)
	MessageChannel walletCreated();
	
	//Payment service
	@Output(CASH_OUT)
	SubscribableChannel cashOut();
	
	@Output(PAY_TOKENS)
	SubscribableChannel payTokens();
	
}
