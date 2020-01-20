package be.ugent.groep10.gamblings.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document // MongoDB
public class BettableGame {

	@Id
	private String id;
	private String matchId;
	private LocalDateTime playedOn;
	
	public BettableGame(String matchId, LocalDateTime playedOn) {
		super();
		this.matchId = matchId;
		this.playedOn = playedOn;
	}
	
	public String getId() {
		return id;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public LocalDateTime getPlayedOn() {
		return playedOn;
	}

	public void setPlayedOn(LocalDateTime playedOn) {
		this.playedOn = playedOn;
	}


	@Override
	public String toString() {
		return "BettableGame [id=" + id + ", matchId=" + matchId + ", playedOn=" + playedOn + "]";
	}
	
	
	

}
