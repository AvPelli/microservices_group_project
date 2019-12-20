package be.ugent.groep10.catering.adapters.messaging;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Gamecreated event van de arena mgmt. service
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewEventRequest {
	private long eventId;
	private LocalDate startTime;
	private LocalDate endTime;
	
	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public LocalDate getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}
	
	public LocalDate getEndTime() {
		return endTime;
	}
	
	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}
}
