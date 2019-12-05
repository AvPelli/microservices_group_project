package be.ugent.groep10.catering.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/*
 * Behandeld kafka messages
 */

public interface Channels {
	static final String NEW_EVENT = "events";
	static final String EVENT_REGISTERED = "registered_events";
	static final String SEAT_UPDATE = "seats";
	
	/*
	 * NEW_EVENT 
	 * 	Creatie van een nieuwe wedstrijd
	 * Publisher: Arena Mgmt Service
	 * Consumer: Catering Service
	 */
	@Input(NEW_EVENT)
	SubscribableChannel newEvent();
	
	/*
	 * EVENT_REGISTERED
	 * 	Ontvangstbevestiging en scheduleitem aangemaakt
	 * Publisher: Catering Service
	 * Consumer: Arena Mgmt Service
	 */
	@Output(EVENT_REGISTERED)
	MessageChannel eventRegistered();

	/*
	 * SEAT UPDATE
	 * 	Update aantal bezoekers van het event
	 * Publisher: Ticket service
	 * Consumer: Catering service
	 */
	@Input(SEAT_UPDATE)
	SubscribableChannel seatUpdate();
}
