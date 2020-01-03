package be.ugent.groep10.gamblings.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "bettablegame")
public class BettableGame {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bettable_game_id")
	private long id;
	
	@Column(unique = true)
	private long matchID;
	
	private LocalDate playedOn;
	
	private double odds;
	
	
	protected BettableGame() {
	}


	public BettableGame(long matchID, LocalDate playedOn, double odds) {
		this.matchID = matchID;
		this.playedOn = playedOn;
		this.odds = odds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public long getMatchID() {
		return matchID;
	}


	public void setMatchID(long matchID) {
		this.matchID = matchID;
	}


	public LocalDate getPlayedOn() {
		return playedOn;
	}


	public void setPlayedOn(LocalDate playedOn) {
		this.playedOn = playedOn;
	}


	public double getOdds() {
		return odds;
	}


	public void setOdds(double odds) {
		this.odds = odds;
	}


	@Override
	public String toString() {
		return "BettableGame [id=" + id + ", matchID=" + matchID + ", playedOn=" + playedOn + ", odds=" + odds + "]";
	}
	
	
	

}
