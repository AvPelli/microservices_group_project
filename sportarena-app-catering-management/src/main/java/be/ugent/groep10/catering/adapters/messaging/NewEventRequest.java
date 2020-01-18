package be.ugent.groep10.catering.adapters.messaging;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Gamecreated event van de arena mgmt. service
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewEventRequest {
	private String sportEventId;
	private String startTime;
	private String endTime;
	
	public String getSportEventId() {
		return sportEventId;
	}

	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}

	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
