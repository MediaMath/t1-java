package com.mediamath.jterminalone.Exceptions;

public class ParseException extends T1Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ParseException(String message) {
		super(message);
	}
	
	
	public ParseException(Exception e) {
		super(e);
	}
	
	@Override
	public String toString() {
		return "Parse Exception: " + message;
	}

}
