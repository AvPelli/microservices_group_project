package be.ugent.groep10.catering.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/*
 * Behandelt kafka messages
 */

public interface Channels {
	static final String GAME_CREATED_EVENT = "game_created_event";
	static final String EVENT_REGISTERED = "registered_events";
	static final String UPDATE_OCCUPANCY = "update_occupancy";
	
	static final String REGISTER_CATERING = "register_catering";
	static final String REGISTER_CATERING_TIMEOUT = "register_catering_timeout";
	
	static final String REGISTER_RESULT = "register_result";
	
	/*
	 * NEW_EVENT 
	 * 	Creatie van een nieuwe wedstrijd
	 * Publisher: Arena Mgmt Service
	 * Consumer: Catering Service
	 */
	@Input(GAME_CREATED_EVENT)
	SubscribableChannel newEvent();
	

	/*
	 * SEAT UPDATE
	 * 	Update aantal bezoekers van het event
	 * Publisher: Ticket service
	 * Consumer: Catering service
	 */
	@Input(UPDATE_OCCUPANCY)
	SubscribableChannel seatUpdate();
	
	/*
	 * REGISTRATION RESULT
	 */
	@Output(REGISTER_RESULT)
	MessageChannel registerResult();
	
	@Input(REGISTER_CATERING)
	SubscribableChannel registerCatering();
	
	@Input(REGISTER_CATERING_TIMEOUT)
	SubscribableChannel registerCateringTimeout();
}
