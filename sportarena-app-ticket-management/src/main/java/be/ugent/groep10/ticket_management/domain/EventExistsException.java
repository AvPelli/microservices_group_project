package be.ugent.groep10.ticket_management.domain;

public class EventExistsException extends Exception {

	public EventExistsException(String sportEventId) {
		super("Refusing to create tickets: event " + sportEventId + " already exists.");
	}
	
}
