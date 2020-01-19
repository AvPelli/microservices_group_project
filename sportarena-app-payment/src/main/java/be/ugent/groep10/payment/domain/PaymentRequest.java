package be.ugent.groep10.payment.domain;

public class PaymentRequest {

	private String memberId;
	private double amountOfEuros;
	private double amountOfTokens;

	
	public PaymentRequest(String memberId, double amountOfEuros, double amountOfTokens) {
		super();
		this.memberId = memberId;
		this.amountOfEuros = amountOfEuros;
		this.amountOfTokens = amountOfTokens;
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

	@Override
	public String toString() {
		return "PaymentRequest [memberId=" + memberId + ", amountOfEuros=" + amountOfEuros + ", amountOfTokens="
				+ amountOfTokens + "]";
	}

	

}
