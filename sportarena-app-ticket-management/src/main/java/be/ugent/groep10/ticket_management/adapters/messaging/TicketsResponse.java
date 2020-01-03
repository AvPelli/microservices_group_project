package be.ugent.groep10.ticket_management.adapters.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketsResponse {

	private String sportEventId;
	private boolean ticketsCreated;
	
	public TicketsResponse(String sportEventId, boolean ticketsCreated) {
		this.sportEventId = sportEventId;
		this.ticketsCreated = ticketsCreated;
	}
	
	public String getSportEventId() {
		return sportEventId;
	}
	
	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}
	
	public boolean getTicketCreated() {
		return ticketsCreated;
	}
	
	public void setTicketCreated(boolean ticketsCreated) {
		this.ticketsCreated = ticketsCreated;
	}
	
}
