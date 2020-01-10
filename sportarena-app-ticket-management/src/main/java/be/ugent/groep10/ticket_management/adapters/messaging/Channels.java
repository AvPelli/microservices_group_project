package be.ugent.groep10.ticket_management.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	static final String CREATE_TICKETS = "create_tickets";
	static final String TICKETS_CREATED = "tickets_created";
	static final String UPDATE_OCCUPANCY = "update_occupancy";
	
	@Input(CREATE_TICKETS)
	SubscribableChannel createTickets();
	
	@Output(TICKETS_CREATED)
	MessageChannel ticketsCreated();
	
	@Output(UPDATE_OCCUPANCY)
	MessageChannel updateOccupancy();
	
}
