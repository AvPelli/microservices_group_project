package be.ugent.groep10.ticket_management.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	static final String GAME_CREATED_EVENT = "game_created_event";
	static final String GAME_ENDED_EVENT = "game_ended_event";
	static final String UPDATE_OCCUPANCY = "update_occupancy";
	
	@Input(GAME_CREATED_EVENT)
	SubscribableChannel createTickets();
	
	@Input(GAME_ENDED_EVENT)
	MessageChannel endGame();
	
	@Output(UPDATE_OCCUPANCY)
	MessageChannel updateOccupancy();
	
}
