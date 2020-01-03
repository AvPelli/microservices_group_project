package be.ugent.groep10.membermanagement.adapters.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWalletRequest {
	private String ownerId;
	private double tokens;
	
	public CreateWalletRequest(String ownerId, double tokens) {
		this.ownerId = ownerId;
		this.tokens = tokens;
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
	@Override
	public String toString() {
		return "CreateWalletRequest [ownerId=" + ownerId + ", tokens=" + tokens + "]";
	}
	
}
