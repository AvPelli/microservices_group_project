package be.ugent.groep10.catering.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.groep10.catering.domain.ScheduleItem;

@MessagingGateway
public interface CateringReservationGateway {

	@Gateway(requestChannel = Channels.EVENT_REGISTERED)
	void eventRegistered(ScheduleItem reservatie);
}
