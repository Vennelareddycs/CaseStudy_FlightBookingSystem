package com.booking.Exceptions;

public class NoSuchFlightIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchFlightIdException(String message) {
		super(message);
	}

}
