package be.ugent.groep10.gamblings.domain;

public class NotEnoughTokensException extends Exception {

	public NotEnoughTokensException(String errorMessage) {
		super(errorMessage);
	}

}


