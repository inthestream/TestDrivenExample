package org.testDriven.exception;

public class MissingValueException extends RuntimeException {
	public MissingValueException(String message) {
		super(message);
	}

}
