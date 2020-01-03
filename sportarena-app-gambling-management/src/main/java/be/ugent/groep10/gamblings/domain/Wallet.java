package be.ugent.groep10.gamblings.domain;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Wallet {
	
	@Id 
	private String id;
	
//	@Id 
	private String ownerId;
	private double tokens;
	
	@SuppressWarnings("unused")
	protected Wallet() {
		this.ownerId = "";
		this.tokens = 0;
	}
	
	public Wallet(String ownerId, double tokens) {
		this.ownerId = ownerId;
		this.tokens = tokens;
	}

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public double getTokens() {
		return tokens;
	}

	public void setTokens(double tokens) {
		this.tokens = tokens;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	@Override
	public String toString() {
		return "Wallet [ownerId=" + ownerId + ", tokens=" + tokens + "]";
	}
	
}
