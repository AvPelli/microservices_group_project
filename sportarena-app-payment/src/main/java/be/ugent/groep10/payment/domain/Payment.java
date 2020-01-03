package be.ugent.groep10.payment.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double tokens;
	private String member_bankaccount;
	
	private Payment() {
	}
	
	public Payment(double tokens) {
		this.setTokens(tokens);
	}

	//Getters & setters
	public double getTokens() {
		return tokens;
	}

	public void setTokens(double tokens) {
		this.tokens = tokens;
	}
	
}
