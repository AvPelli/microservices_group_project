package be.ugent.groep10.catering.adapters.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatOccupationUpdate {
	private String sportEventId;
	private int occupancy;
	
	public String getSportEventId() {
		return sportEventId;
	}
	
	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}
	
	public int getOccupancy() {
		return occupancy;
	}
	
	public void setOccupancy(int occupancy) {
		this.occupancy = occupancy;
	}
}
