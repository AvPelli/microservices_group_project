package be.ugent.groep10.arena.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String GAME_CREATED_EVENT = "game_created_event";
	static final String GAME_ENDED_EVENT = "game_ended_event";
	static final String UPDATE_OCCUPANCY = "update_occupancy";
	
	@Output(GAME_CREATED_EVENT)
	MessageChannel gameCreated();
	
	@Output(GAME_ENDED_EVENT)
	MessageChannel gameEnded();
	
	@Input(UPDATE_OCCUPANCY)
	SubscribableChannel updateOccupancy();
}
