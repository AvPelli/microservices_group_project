package be.ugent.groep10.arena.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	static final String NEW_EVENT = "events";
	static final String EVENT_REGISTERED = "registered_events";
	
	@Output(NEW_EVENT)
	MessageChannel newEvent();
	
	/*
	 * Confirmatie van de services dat ze het evenement aangemaakt hebben
	 */
	@Input(EVENT_REGISTERED)
	SubscribableChannel eventRegistered();
}
