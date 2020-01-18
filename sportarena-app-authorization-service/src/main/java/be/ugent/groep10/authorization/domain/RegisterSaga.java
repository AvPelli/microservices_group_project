package be.ugent.groep10.authorization.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.groep10.authorization.adapters.messaging.MessageGateway;
import be.ugent.groep10.authorization.adapters.messaging.RegisterResponse;


@Service
public class RegisterSaga {
	
	private static Logger logger = LoggerFactory.getLogger(RegisterSaga.class);
	
	private final MessageGateway gateway;
	private final Set<AuthorizationRegisterListener> listeners;

	@Autowired
	public RegisterSaga(MessageGateway gateway) {
		this.gateway = gateway;
		this.listeners = new HashSet<>(10);
	}
	
	public void registerListener(AuthorizationRegisterListener listener) {
		this.listeners.add(listener);
	}
	
	public void startMemberRegisterSaga(Member member) {
		//TODO: doen
		logger.info("Member register saga started.");
		this.gateway.registerMember(member);
	}
	
	public void registerComplete(RegisterResponse registerResponse) {
		this.listeners.forEach(l -> l.onRegisterResult(registerResponse));
	}
	
	public void registerMemberTimeout(String oktaUserId) {
		this.gateway.registerMemberTimeout(oktaUserId);
	}
	
	public void registerFailed(RegisterResponse registerResponse) {
		this.listeners.forEach(l -> l.onRegisterResult(registerResponse));
	}
	

}
