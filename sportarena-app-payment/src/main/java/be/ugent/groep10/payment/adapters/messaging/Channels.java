package be.ugent.groep10.payment.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	
	static final String CASH_OUT = "cash_out";
	static final String CASH_OUT_TIMEOUT = "cash_out_timeout";
	static final String CASH_OUT_RESULT = "cash_out_result";
	
	static final String PAY_TOKENS = "pay_tokens";
	static final String PAY_TOKENS_TIMEOUT = "pay_tokens_timeout";
	static final String PAY_TOKENS_RESULT = "pay_tokens_result";
	
	
	//Payment service
	@Input(PAY_TOKENS)
	SubscribableChannel payTokens();
	
	@Input(PAY_TOKENS_TIMEOUT)
	SubscribableChannel payTokensTimeout();
	
	@Output(PAY_TOKENS_RESULT)
	MessageChannel payTokensResult();
	
	@Input(CASH_OUT)
	SubscribableChannel cashOut();
	
	@Input(CASH_OUT_TIMEOUT)
	SubscribableChannel cashOutTimeout();
	
	@Output(CASH_OUT_RESULT)
	MessageChannel cashOutResult();
	
	
	
	
	
}
