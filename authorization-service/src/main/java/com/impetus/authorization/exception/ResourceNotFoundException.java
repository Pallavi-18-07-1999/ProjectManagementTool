package com.impetus.authorization.exception;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
