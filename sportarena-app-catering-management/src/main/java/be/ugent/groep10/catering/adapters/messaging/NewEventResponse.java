package be.ugent.groep10.catering.adapters.messaging;


/*
 * Respons op de Gamecreated event van de arena mgmt service
 */
public class NewEventResponse {
	private String eventId;
	private boolean status;
	private String description;
	
	public NewEventResponse(String eventId, boolean status, String description) {
		this.eventId = eventId;
		this.status = status;
		this.description = description;
	}
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
