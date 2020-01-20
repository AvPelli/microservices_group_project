package be.ugent.groep10.gamblings.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Wallet {

	@Id 
	private String id;
	private String ownerId;
	private double tokens;
	private double tokensBeingTraded;
	
	
	public Wallet(String ownerId, double tokens) {
		super();
		this.ownerId = ownerId;
		this.tokens = tokens;
		this.tokensBeingTraded = 0;
	}
	
	public String getId() {
		return id;
	}


	public String getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}


	public double getTokens() {
		return tokens;
	}


	public void setTokens(double tokens) {
		this.tokens = tokens;
	}

	public double getTokensBeingTraded() {
		return tokensBeingTraded;
	}

	public void setTokensBeingTraded(double tokensBeingPurchased) {
		this.tokensBeingTraded = tokensBeingPurchased;
	}

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", ownerId=" + ownerId + ", tokens=" + tokens + ", tokensBeingPurchased="
				+ tokensBeingTraded + "]";
	}
	
	

}
