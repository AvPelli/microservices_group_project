package be.ugent.groep10.payment.adapters.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import be.ugent.groep10.payment.domain.CashOutRequest;
import be.ugent.groep10.payment.domain.CashOutResponse;
import be.ugent.groep10.payment.domain.PaymentRequest;
import be.ugent.groep10.payment.domain.PaymentResponse;

@Service
public class CommandHandler {
	
	private final MessageGateway gateway;
	private static Logger logger = LoggerFactory.getLogger(CommandHandler.class);
	
	@Autowired
	public CommandHandler(MessageGateway gateway) {
		this.gateway = gateway;
	}
	
	
	
	@StreamListener(Channels.CASH_OUT)
	public void cashOut(CashOutRequest cashOutResuest) {
		logger.info("Cash out accepted!");
		gateway.cashOutResult(new CashOutResponse(cashOutResuest.getMemberId(), cashOutResuest.getAmountOfEuros(), cashOutResuest.getAmountOfTokens(), true));
	}
	
	@StreamListener(Channels.CASH_OUT_TIMEOUT)
	public void cashOutTimeout(CashOutRequest cashOutResuest) {
		logger.info("Cash out timeout!");
	}
	
	@StreamListener(Channels.PAY_TOKENS)
	public void payment(PaymentRequest paymentRequest) {
		logger.info("Paytokens accepted!");
		gateway.paymentResult(new PaymentResponse(paymentRequest.getMemberId(), paymentRequest.getAmountOfEuros(), paymentRequest.getAmountOfTokens(), true));
		
	}
	
	@StreamListener(Channels.PAY_TOKENS_TIMEOUT)
	public void paymentTimeout(PaymentRequest paymentRequest) {
		logger.info("Paytokens timeout!");
	}
	
	
}
