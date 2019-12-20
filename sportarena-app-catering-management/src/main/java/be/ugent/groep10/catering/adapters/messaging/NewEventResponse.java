package be.ugent.groep10.catering.adapters.messaging;


/*
 * Respons op de Gamecreated event van de arena mgmt service
 */
public class NewEventResponse {
	private long eventId;
	private boolean status;
	private String description;
	
	public NewEventResponse(long id, boolean status, String description) {
		this.setEventId(id);
		this.status = status;
		this.description = description;
	}
	
	//Getters en setters
	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	public boolean getStatus() {
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
