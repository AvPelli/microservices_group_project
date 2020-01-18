package be.ugent.groep10.authorization.domain;

import be.ugent.groep10.authorization.adapters.messaging.RegisterResponse;

public interface AuthorizationRegisterListener {
	
	public void onRegisterResult(RegisterResponse registerResponse);

}
