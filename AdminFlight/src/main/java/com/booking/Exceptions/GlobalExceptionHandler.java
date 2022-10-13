package com.booking.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NoSuchFlightIdException.class)
	public ResponseEntity<ErrorDetails> handleNoFlightIdFound(NoSuchFlightIdException ex){
		ErrorDetails error = new ErrorDetails(
				ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ErrorDetails> handleNoDataExists(NoDataFoundException ex){
		ErrorDetails error = new ErrorDetails(
				ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
}