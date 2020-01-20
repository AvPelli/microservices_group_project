package be.ugent.groep10.gamblings.domain;

import be.ugent.groep10.gamblings.adapters.messaging.PaymentResponse;

public interface PayTokensListener {

	public void onRegisterResult(PaymentResponse paymentResponse);
}
