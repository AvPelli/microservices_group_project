package be.ugent.groep10.arena.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.groep10.arena.domain.ScheduleItem;

@MessagingGateway
public interface ArenaGateway {
	
	@Gateway(requestChannel = Channels.NEW_EVENT)
	void sendNewEvent(ScheduleItem event);
}
