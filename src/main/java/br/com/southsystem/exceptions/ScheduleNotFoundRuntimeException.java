package br.com.southsystem.exceptions;

public class ScheduleNotFoundRuntimeException extends ConflictsRuntimeException {

	public ScheduleNotFoundRuntimeException() {
		super("Schedule not found");
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.NOT_FOUND;
	}

}
