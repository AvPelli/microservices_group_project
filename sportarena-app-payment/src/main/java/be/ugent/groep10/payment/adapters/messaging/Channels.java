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
