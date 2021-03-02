package br.com.southsystem.exceptions;

public class AssociateAlreadyVotedRuntimeException extends ConflictsRuntimeException {

	public AssociateAlreadyVotedRuntimeException() {
		super("Associated Already voted");
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.ASSOCIATED_ALREADY_VOTED;
	}

}
