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
	
	//Register SAGAs
	public void startMemberRegisterSaga(Member member) {
		//TODO: doen
		logger.info("Member register saga started.");
		this.gateway.registerMember(member);
	}
	
	public void startCateringRegisterSaga(CateringCompany catering) {
		logger.info("Catering register saga started.");
		this.gateway.registerCatering(catering);
	}
	
	public void startStaffRegisterSaga(Staff staff) {
		logger.info("Catering register saga started.");
		this.gateway.registerStaff(staff);
		
	}
	
	//Register Timeouts
	public void registerMemberTimeout(String oktaUserId) {
		this.gateway.registerMemberTimeout(oktaUserId);
	}
	
	public void registerCateringTimeout(String oktaUserId) {
		this.gateway.registerCateringTimeout(oktaUserId);
	}
	
	public void registerStaffTimeout(String oktaUserId) {
		this.gateway.registerStaffTimeout(oktaUserId);
	}
	
	public void registerComplete(RegisterResponse registerResponse) {
		this.listeners.forEach(l -> l.onRegisterResult(registerResponse));
	}
	
	
	
	public void registerFailed(RegisterResponse registerResponse) {
		this.listeners.forEach(l -> l.onRegisterResult(registerResponse));
	}
	

}
