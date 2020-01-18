package be.ugent.groep10.authorization.adapters.messaging;

public class RegisterResponse {
	
	private String userId;
	private boolean succeeded;
	
	
	public RegisterResponse(String userId, boolean succeeded) {
		this.userId = userId;
		this.succeeded = succeeded;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isSucceeded() {
		return succeeded;
	}
	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}

	@Override
	public String toString() {
		return "RegisterResponse [userId=" + userId + ", succeeded=" + succeeded + "]";
	}
	
	

}
