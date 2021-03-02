package br.com.southsystem.exceptions;

public class VotingClosedRuntimeException extends ConflictsRuntimeException {

	public VotingClosedRuntimeException() {
		super("Voting Closed");
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.VOTING_CLOSED;
	}

}
