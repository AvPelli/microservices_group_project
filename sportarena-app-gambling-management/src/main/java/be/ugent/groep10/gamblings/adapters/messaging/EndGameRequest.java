package be.ugent.groep10.gamblings.adapters.messaging;

import be.ugent.groep10.gamblings.domain.Score;

public class EndGameRequest {

	private String sportEventId;
	private Score score;
	
	public EndGameRequest() {}
	
	public EndGameRequest(String sportEventId, Score score) {
		this.sportEventId = sportEventId;
		this.score = score;
	}

	public String getSportEventId() {
		return sportEventId;
	}

	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
	
}
