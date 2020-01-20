package be.ugent.groep10.payment.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.groep10.payment.domain.CashOutResponse;
import be.ugent.groep10.payment.domain.PaymentResponse;


@MessagingGateway
public interface MessageGateway {
	
	@Gateway(requestChannel = Channels.CASH_OUT_RESULT)
	public void cashOutResult(CashOutResponse cachOutResponse);
	
	@Gateway(requestChannel = Channels.PAY_TOKENS_RESULT)
	public void paymentResult(PaymentResponse paymentResponse); 
	
	

}
