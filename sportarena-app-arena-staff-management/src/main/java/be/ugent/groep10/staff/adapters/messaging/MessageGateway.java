package be.ugent.groep10.staff.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;


@MessagingGateway
public interface MessageGateway {
	
	@Gateway(requestChannel = Channels.REGISTER_RESULT)
	public void registerResult(RegisterResponse response);
}
