package be.ugent.groep10.catering.adapters.messaging;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Gamecreated event van de arena mgmt. service
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewEventRequest {
	private String sportEventId;
	private LocalDateTime dateTimeBegin;
	private LocalDateTime dateTimeEnd;
	
	public String getSportEventId() {
		return sportEventId;
	}

	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}

	public LocalDateTime getDateTimeBegin() {
		return dateTimeBegin;
	}

	public void setDateTimeBegin(LocalDateTime dateTimeBegin) {
		this.dateTimeBegin = dateTimeBegin;
	}

	public LocalDateTime getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}
}
