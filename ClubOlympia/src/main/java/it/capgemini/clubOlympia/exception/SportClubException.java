package it.capgemini.clubOlympia.exception;

@SuppressWarnings("serial")
public class SportClubException extends RuntimeException {

	public SportClubException(String message) {
		super(message);
	}

	public SportClubException(String message, Exception e) {
		super(message, e);
	}

}
