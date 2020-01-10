package be.ugent.groep10.ticket_management.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {
	
	@Gateway(requestChannel = Channels.UPDATE_OCCUPANCY)
	public void updateOccupancy(UpdateOccupancyRequest request);
	
}
