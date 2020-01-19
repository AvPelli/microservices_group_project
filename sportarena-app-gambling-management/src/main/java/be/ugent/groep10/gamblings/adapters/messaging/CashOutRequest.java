package be.ugent.groep10.gamblings.adapters.messaging;



public class CashOutRequest {
	private String memberId;
	private double amountOfEuros;
	private double amountOfTokens;

	
	public CashOutRequest(String memberId, double amountOfEuros, double amountOfTokens) {
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
		return "CashoutRequest [memberId=" + memberId + ", amountOfEuros=" + amountOfEuros + ", amountOfTokens="
				+ amountOfTokens + "]";
	}

	
	
	
}
