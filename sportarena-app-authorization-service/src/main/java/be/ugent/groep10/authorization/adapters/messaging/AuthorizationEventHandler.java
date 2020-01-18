package be.ugent.groep10.authorization.adapters.messaging;

import javax.imageio.spi.RegisterableService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import be.ugent.groep10.authorization.domain.AuthorizationService;


@Service
public class AuthorizationEventHandler {
	
	private static Logger logger = LoggerFactory.getLogger(AuthorizationEventHandler.class);
	private final AuthorizationService authorizationService;
	
	@Autowired
	public AuthorizationEventHandler(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
	
	@StreamListener(Channels.REGISTER_RESULT)
	public void processRegisterResponse(RegisterResponse registerResponse) {
		if(registerResponse.isSucceeded()) {
			logger.info("OktaUser registered. OktaUserId: " + registerResponse.getUserId());
			authorizationService.registerComplete(registerResponse);
		} else {
			logger.info("OktaUser could not be registered. OktaUserId: " + registerResponse.getUserId());
			authorizationService.registerFailed(registerResponse);
			
		}
	}
	
	
}
