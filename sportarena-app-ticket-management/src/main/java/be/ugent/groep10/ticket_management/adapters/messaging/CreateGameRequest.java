package be.ugent.groep10.ticket_management.adapters.messaging;

public class CreateGameRequest {

	private String sportEventId;
	
	public String getSportEventId() {
		return this.sportEventId;
	}
	
	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}
	
}
