package be.ugent.groep10.catering.adapters.messaging;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Gamecreated event van de arena mgmt. service
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewEventRequest {
	private String eventId;
	private LocalDate start;
	private LocalDate einde;
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public LocalDate getStart() {
		return start;
	}
	
	public void setStart(LocalDate start) {
		this.start = start;
	}
	
	public LocalDate getEinde() {
		return einde;
	}
	
	public void setEinde(LocalDate einde) {
		this.einde = einde;
	}
}
