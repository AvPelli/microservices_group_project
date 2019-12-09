package be.ugent.groep10.gamblings.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wallet {

	@Id
	private final long ownerId;
	private double tokens;
	
	@SuppressWarnings("unused")
	protected Wallet() {
		this.ownerId = 0;
		this.tokens = 0;
	}
	
	public Wallet(long ownerId, double tokens) {
		this.ownerId = ownerId;
		this.tokens = tokens;
	}

	public double getTokens() {
		return tokens;
	}

	public void setTokens(double tokens) {
		this.tokens = tokens;
	}

	public long getOwnerId() {
		return ownerId;
	}

	@Override
	public String toString() {
		return "Wallet [ownerId=" + ownerId + ", tokens=" + tokens + "]";
	}
	
}
