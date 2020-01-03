package be.ugent.groep10.ticket_management.adapters.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketsRequest {

	private String sportEventId;
	
	public String getSportEventId() {
		return this.sportEventId;
	}
	
	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}
	
}
