package be.ugent.groep10.payment.domain;

public class CashOutResponse {
	private String memberId;
	private double amountOfEuros;
	private double amountOfTokens;
	private boolean succeeded;
	
	public CashOutResponse(String memberId, double amountOfEuros, double amountOfTokens, boolean succeeded) {
		super();
		this.memberId = memberId;
		this.amountOfEuros = amountOfEuros;
		this.amountOfTokens = amountOfTokens;
		this.succeeded = succeeded;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public double getAmountOfEuros() {
		return amountOfEuros;
	}

	public void setAmountOfEuros(double amountOfEuros) {
		this.amountOfEuros = amountOfEuros;
	}

	public double getAmountOfTokens() {
		return amountOfTokens;
	}

	public void setAmountOfTokens(double amountOfTokens) {
		this.amountOfTokens = amountOfTokens;
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}

	@Override
	public String toString() {
		return "CashOutResponse [memberId=" + memberId + ", amountOfEuros=" + amountOfEuros + ", amountOfTokens="
				+ amountOfTokens + ", succeeded=" + succeeded + "]";
	}
	
	

}
