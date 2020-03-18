package com.bongladesch.quarkus.usecases.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(final String email) {
	    super(email);
	}
}