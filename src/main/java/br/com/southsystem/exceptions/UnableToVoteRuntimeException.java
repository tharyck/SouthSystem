package br.com.southsystem.exceptions;

public class UnableToVoteRuntimeException extends ConflictsRuntimeException {

	public UnableToVoteRuntimeException() {
		super("Unable to vote");
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.UNABLE_TO_VOTE;
	}

}
