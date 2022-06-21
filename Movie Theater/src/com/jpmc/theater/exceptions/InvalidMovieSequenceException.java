package com.jpmc.theater.exceptions;

/**
 * 
 * @author roopsatsangi
 *
 */
public class InvalidMovieSequenceException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidMovieSequenceException() {}

	public InvalidMovieSequenceException(String message) {
		super(message);
	}
}