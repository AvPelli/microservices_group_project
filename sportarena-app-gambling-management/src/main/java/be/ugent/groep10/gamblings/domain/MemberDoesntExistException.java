package be.ugent.groep10.gamblings.domain;

public class MemberDoesntExistException extends Exception {

	public MemberDoesntExistException(String errorMessage) {
		super(errorMessage);
	}

}


