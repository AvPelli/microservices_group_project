package be.ugent.groep10.payment.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String CASH_OUT = "cash_out";
	static final String PAY_TOKENS = "pay_tokens";
	
	static final String PAY_TICKET = "pay_ticket";
	
	static final String DISPLAY_PAYMENT = "display_payment";
	
	//Communicatie met Betting service
	@Input(CASH_OUT)
	SubscribableChannel cashOut();
	
	@Input(PAY_TOKENS)
	SubscribableChannel payTokens();
	
	//Communicatie met Ticket service
	@Input(PAY_TICKET)
	SubscribableChannel payTicket();
	
	//Communicatie naar API gateway: toon details aan gebruiker
	@Output(DISPLAY_PAYMENT)
	MessageChannel displayPayment();
}
