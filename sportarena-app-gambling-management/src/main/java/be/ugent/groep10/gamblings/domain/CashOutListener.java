package be.ugent.groep10.gamblings.domain;

import be.ugent.groep10.gamblings.adapters.messaging.CashOutResponse;

public interface CashOutListener {
	public void onRegisterResult(CashOutResponse cashOutResponse);
}
