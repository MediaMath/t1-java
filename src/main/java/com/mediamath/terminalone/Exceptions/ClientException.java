package com.mediamath.terminalone.Exceptions;

public class ClientException extends T1Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ClientException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Client Excpetion: " + message;
	}

}
