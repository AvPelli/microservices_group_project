package be.ugent.groep10.gamblings.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bet {
	
	@Id @GeneratedValue
	private long id;
	private long placedByMember;
	LocalDate placedOn;
	private long scheduleItemId;
	private double tokensInvested;	
	
	private Bet() {
		this.placedByMember = 0;
		this.placedOn = null;
		this.scheduleItemId = 0;
		this.tokensInvested = 0;
	}

	public Bet(long placedByMember, LocalDate placedOn, long scheduleItemId, double tokensInvested) {
		this.placedByMember = placedByMember;
		this.placedOn = placedOn;
		this.scheduleItemId = scheduleItemId;
		this.tokensInvested = tokensInvested;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlacedByMember() {
		return placedByMember;
	}

	public void setPlacedByMember(long placedByMember) {
		this.placedByMember = placedByMember;
	}

	public LocalDate getPlacedOn() {
		return placedOn;
	}

	public void setPlacedOn(LocalDate placedOn) {
		this.placedOn = placedOn;
	}

	public long getScheduleItemId() {
		return scheduleItemId;
	}

	public void setScheduleItemId(long scheduleItemId) {
		this.scheduleItemId = scheduleItemId;
	}

	public double getTokensInvested() {
		return tokensInvested;
	}

	public void setTokensInvested(double tokensInvested) {
		this.tokensInvested = tokensInvested;
	}

	@Override
	public String toString() {
		return "Bet [placedByMember=" + placedByMember + ", placedOn=" + placedOn + ", scheduleItemId=" + scheduleItemId
				+ ", tokensInvested=" + tokensInvested + "]";
	}
	
	
	

}
