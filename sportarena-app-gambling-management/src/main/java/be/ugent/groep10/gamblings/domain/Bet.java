package be.ugent.groep10.gamblings.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document // MongoDB
public class Bet {

	@Id
	private String id;
	private String placedByMember;
	private String bettableGameId;
	private Prediction prediction;
	private double tokensInvested;
	
	

	public Bet(String placedByMember, String bettableGameId, Prediction prediction, double tokensInvested) {
		super();
		this.placedByMember = placedByMember;
		this.bettableGameId = bettableGameId;
		this.prediction = prediction;
		this.tokensInvested = tokensInvested;
	}

	public String getId() {
		return id;
	}

	

	public String getPlacedByMember() {
		return placedByMember;
	}

	public void setPlacedByMember(String placedByMember) {
		this.placedByMember = placedByMember;
	}

	

	public String getBettableGameId() {
		return bettableGameId;
	}

	public void setBettableGameId(String bettableGameId) {
		this.bettableGameId = bettableGameId;
	}

	public Prediction getPrediction() {
		return prediction;
	}

	public void setPrediction(Prediction prediction) {
		this.prediction = prediction;
	}

	public double getTokensInvested() {
		return tokensInvested;
	}

	public void setTokensInvested(double tokensInvested) {
		this.tokensInvested = tokensInvested;
	}

	@Override
	public String toString() {
		return "Bet [id=" + id + ", placedByMember=" + placedByMember + ", bettableGame=" + bettableGameId
				+ ", prediction=" + prediction + ", tokensInvested=" + tokensInvested + "]";
	}

	
}
