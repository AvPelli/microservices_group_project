package be.ugent.groep10.arena.domain;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document // MongoDB
public class Game {
	
	@Id
	private String id;
	private String sportclubId;
	private String teamA;
	private String teamB;
	private String reclame;
	private LocalDateTime dateTimeBegin;
	private LocalDateTime dateTimeEnd;
	private GameStatus gameStatus;
	private Score score;
	
	public Game() {
		
	}
	
	public Game(String sportclubId, String teamA, String teamB, String reclame, LocalDateTime dateTimeBegin, LocalDateTime dateTimeEnd) {
		this.sportclubId = sportclubId.toLowerCase().replace(" ", "_");
		this.teamA = teamA.toLowerCase();
		this.teamB = teamB.toLowerCase();
		this.reclame = reclame;
		this.dateTimeBegin = dateTimeBegin;
		this.dateTimeEnd = dateTimeEnd;
		this.gameStatus = GameStatus.PLANNED;
		this.score = new Score();
	
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String getId() {
		return id;
	}
	public String getSportclubId() {
		return sportclubId;
	}
	public void setSportclubId(String sportclubId) {
		this.sportclubId = sportclubId;
	}
	public String getTeamA() {
		return teamA;
	}
	public void setTeamA(String team1) {
		this.teamA = team1;
	}
	public String getTeamB() {
		return teamB;
	}
	public void setTeamB(String team2) {
		this.teamB = team2;
	}
	public String getReclame() {
		return reclame;
	}
	public void setReclame(String reclame) {
		this.reclame = reclame;
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

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", sportclubId=" + sportclubId + ", teamA=" + teamA + ", teamB=" + teamB
				+ ", reclame=" + reclame + ", dateTimeBegin=" + dateTimeBegin + ", dateTimeEnd=" + dateTimeEnd
				+ ", gameStatus=" + gameStatus + ", score=" + score + "]";
	}

	
	
	

}
