package be.ugent.groep10.gamblings.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bet {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String placedByMember;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "bettable_game_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
//	@Cascade(CascadeType.ALL)
	@JsonIgnore
	private BettableGame bettableGame;

	private LocalDate placedOn;
	
	private double tokensInvested;
	
	private int expectedResult;
	
	
	private Bet() {
	}

	public Bet(String placedByMember, BettableGame bettableGame, LocalDate placedOn, double tokensInvested, int expectedResult) {
		this.placedByMember = placedByMember;
		this.bettableGame = bettableGame;
		this.placedOn = placedOn;
		this.tokensInvested = tokensInvested;
		this.expectedResult = expectedResult;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlacedByMember() {
		return placedByMember;
	}

	public void setPlacedByMember(String placedByMember) {
		this.placedByMember = placedByMember;
	}

	public LocalDate getPlacedOn() {
		return placedOn;
	}

	public void setPlacedOn(LocalDate placedOn) {
		this.placedOn = placedOn;
	}

	public double getTokensInvested() {
		return tokensInvested;
	}

	public void setTokensInvested(double tokensInvested) {
		this.tokensInvested = tokensInvested;
	}

	public BettableGame getBettableGame() {
		return bettableGame;
	}

	public void setBettableGame(BettableGame bettableGame) {
		this.bettableGame = bettableGame;
	}

	public int getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(int expectedResult) {
		this.expectedResult = expectedResult;
	}

	@Override
	public String toString() {
		return "Bet [placedByMember=" + placedByMember + ", placedOn=" + placedOn
				+ ", tokensInvested=" + tokensInvested + "]";
	}
	
	
	

}
