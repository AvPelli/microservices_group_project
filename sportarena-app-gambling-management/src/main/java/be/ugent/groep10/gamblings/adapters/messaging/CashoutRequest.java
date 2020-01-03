package be.ugent.groep10.gamblings.adapters.messaging;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashoutRequest {
	private long eventId;
	private double tokens;
	
	public CashoutRequest(long id, double tokens) {
		this.eventId = id;
		this.tokens = tokens;
	}
}
