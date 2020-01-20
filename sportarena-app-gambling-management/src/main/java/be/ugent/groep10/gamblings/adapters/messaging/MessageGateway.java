package be.ugent.groep10.gamblings.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;


@MessagingGateway
public interface MessageGateway {
	
	@Gateway(requestChannel = Channels.WALLET_CREATED)
	public void walletCreated(CreateWalletRequest createWalletRequest);
	
	@Gateway(requestChannel = Channels.CASH_OUT)
	public void cashOut(CashOutRequest cashOutRequest); 
	
	@Gateway(requestChannel = Channels.CASH_OUT_TIMEOUT)
	public void cashOutTimeout(CashOutRequest cashOutRequest); 
	
	@Gateway(requestChannel = Channels.PAY_TOKENS)
	public void payTokens(PaymentRequest paymentRequest); 
	
	@Gateway(requestChannel = Channels.PAY_TOKENS_TIMEOUT)
	public void payTokensTimeout(PaymentRequest paymentRequest); 

}
