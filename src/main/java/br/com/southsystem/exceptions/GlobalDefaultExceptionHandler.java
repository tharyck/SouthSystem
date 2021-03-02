package br.com.southsystem.exceptions;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.Instant;

/**
 * Captures system exceptions in the context of the Dispatcher Servlet. That is,
 * exceptions from {@link FilterRegistrationBean} are not dealt with here.
 * 
 * @see <a href=
 *      "https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc">Exception
 *      Handling in Spring</a>
 */
@ControllerAdvice
@RestController
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

	public GlobalDefaultExceptionHandler() {
	};

	/**
	 * Captures generic exceptions and sends error occurred with status Internal
	 * Server Error (HTTP 500).
	 * 
	 * @param exception
	 * @param request
	 * @return ExceptionResponse
	 */
	//@formatter:off
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public final ExceptionResponse handleAllExceptions(
			Exception exception,
			WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(
						Instant.now(),
						exception.getMessage(),
						request.getDescription(false));
		return exceptionResponse;
	}
	//@formatter:on

	/**
	 * Handle all validation problems.
	 * 
	 * @see {@link ResponseEntityExceptionHandler}
	 */
	//@formatter:off
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(
						Instant.now(),
						ex.getBindingResult().getFieldError().getDefaultMessage(),
						ex.getBindingResult().getAllErrors().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);
	}
	//@formatter:on

	/**
	 * Captures Validation Exceptions and sends error occurred with status Bad
	 * Request (HTTP 400).
	 * 
	 * @param exception
	 * @param request
	 * @return ExceptionResponse
	 */
	//@formatter:off
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({
		DataManipulationRuntimeException.class,
		ConstraintViolationException.class,
		DataIntegrityViolationException.class,
		ValidationException.class
	})
	public final ExceptionResponse handleValidationException(
			Exception exception,
			WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(
						Instant.now(),
						exception.getLocalizedMessage(),
						request.getDescription(false));
		return exceptionResponse;
	}
	//@formatter:on

	/**
	 * Captures Access Denied violations or Account problems and sends error
	 * occurred with status Forbidden (HTTP 403).
	 *
	 * @param exception
	 * @param request
	 * @return
	 */
	//@formatter:off
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({
		AccessDeniedException.class,
	})
	public final ExceptionResponse handleAccessDeniedException(
			Exception exception, 
			WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(
						Instant.now(),
						exception.getMessage(),
						request.getDescription(false));
		return exceptionResponse;
	}
	//@formatter:on

	/**
	 * Captures Not Found Resource Exception and sends error occurred with status
	 * Not Found (HTTP 404).
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	//@formatter:off
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({
		NotFoundRuntimeException.class,
	})
	public final ExceptionResponse handleNotFoundRuntimeException(
			Exception exception, 
			WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(
						Instant.now(),
						exception.getMessage(),
						request.getDescription(false));
		return exceptionResponse;
	}
	//@formatter:on

}
