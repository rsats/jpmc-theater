package com.jpmc.theater.exceptions;

/**
 * 
 * @author roopsatsangi
 *
 */
public class InvalidShowStartTimeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidShowStartTimeException() {}

	public InvalidShowStartTimeException(String message) {
		super(message);
	}
}
