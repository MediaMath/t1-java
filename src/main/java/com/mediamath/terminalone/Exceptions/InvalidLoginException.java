package com.mediamath.terminalone.Exceptions;

public class InvalidLoginException extends T1Exception {

	private static final long serialVersionUID = 1L;

	private String credentials;
	
	private String code;
	
	private String message;
		
	public InvalidLoginException(String code, String message, String credentials) {
		super(code, message);
		this.code = code;
		this.message = message;
		this.credentials = credentials;
	}
	
	
	@Override
	public String toString() {
		return "Invalid Login Exception: " + this.code + ": " + this.message + ", " + this.credentials;
	}

}
