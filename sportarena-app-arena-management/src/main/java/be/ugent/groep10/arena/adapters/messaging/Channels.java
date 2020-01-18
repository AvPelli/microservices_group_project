package be.ugent.groep10.arena.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
	
	static final String GAME_CREATED_EVENT = "game_created_event";
	static final String GAME_ENDED_EVENT = "game_ended_event";
	
	@Output(GAME_CREATED_EVENT)
	MessageChannel gameCreated();
	
	@Output(GAME_ENDED_EVENT)
	MessageChannel gameEnded();
	
}
