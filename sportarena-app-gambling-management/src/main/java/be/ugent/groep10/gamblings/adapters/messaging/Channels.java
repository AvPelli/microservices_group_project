package be.ugent.groep10.gamblings.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String GAME_CREATED_EVENT = "game_created_event";
	static final String GAME_ENDED_EVENT = "game_ended_event";
	
	static final String CREATE_WALLET = "create_wallet";
	static final String WALLET_CREATED = "wallet_created";
	
	static final String CASH_OUT = "cash_out";
	static final String CASH_OUT_TIMEOUT = "cash_out_timeout";
	static final String CASH_OUT_RESULT = "cash_out_result";
	static final String PAY_TOKENS = "pay_tokens";
	static final String PAY_TOKENS_TIMEOUT = "pay_tokens_timeout";
	static final String PAY_TOKENS_RESULT = "pay_tokens_result";
	
	
	//Arena mgmt
	@Input(GAME_CREATED_EVENT)
	SubscribableChannel gameCreatedEvent();
	
	@Input(GAME_ENDED_EVENT)
	SubscribableChannel gameEndedEvent();
	
	//Member mgmt
	@Input(CREATE_WALLET)
	SubscribableChannel createWallet();
	
	@Output(WALLET_CREATED)
	MessageChannel walletCreated();
	
	//Payment service
	@Output(PAY_TOKENS)
	MessageChannel payTokens();
	
	@Output(PAY_TOKENS_TIMEOUT)
	MessageChannel payTokensTimeout();
	
	@Input(PAY_TOKENS_RESULT)
	SubscribableChannel payTokensResult();
	
	@Output(CASH_OUT)
	MessageChannel cashOut();
	
	@Output(PAY_TOKENS_TIMEOUT)
	MessageChannel cashOutTimeout();
	
	@Input(CASH_OUT_RESULT)
	SubscribableChannel cashOutResult();
	
	
	
}
