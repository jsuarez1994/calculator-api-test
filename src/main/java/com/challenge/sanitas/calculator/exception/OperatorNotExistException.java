package com.challenge.sanitas.calculator.exception;

public class OperatorNotExistException extends Exception {

	/** the serialVersionUID */
	private static final long serialVersionUID = 914722165023877420L;

	public OperatorNotExistException(final String message) {
		super(message);
	}
}
