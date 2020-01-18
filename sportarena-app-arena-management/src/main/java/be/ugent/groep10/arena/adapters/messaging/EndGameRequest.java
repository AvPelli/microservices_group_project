package be.ugent.groep10.arena.adapters.messaging;

public class EndGameRequest {

private String sportEventId;
	
	public EndGameRequest() {}
	
	public EndGameRequest(String sportEventId) {
		this.sportEventId = sportEventId;
	}

	public String getSportEventId() {
		return sportEventId;
	}

	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}
	
}
