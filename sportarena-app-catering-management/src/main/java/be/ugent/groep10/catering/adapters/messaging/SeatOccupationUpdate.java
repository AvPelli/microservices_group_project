package be.ugent.groep10.catering.adapters.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatOccupationUpdate {
	private long eventId;
	private long occupiedSeats;

	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	public long getOccupiedSeats() {
		return occupiedSeats;
	}
	public void setOccupiedSeats(long occupiedSeats) {
		this.occupiedSeats = occupiedSeats;
	}
}
