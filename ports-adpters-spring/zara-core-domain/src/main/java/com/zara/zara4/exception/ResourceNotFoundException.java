package com.zara.zara4.exception;

public class ResourceNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 7207241563126935791L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}

