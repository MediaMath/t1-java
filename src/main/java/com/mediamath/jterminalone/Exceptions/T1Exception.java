package com.mediamath.jterminalone.Exceptions;

public class T1Exception extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String code = ""; 
	
	private String message = ""; 
	
	public T1Exception() {
		 super();
	}

	public T1Exception(String message) {
		 super(message);
	}
	
	public T1Exception(String code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}
	
	public T1Exception(String message, Throwable pCause) {
		 super(message, pCause);
		 this.message = message;
	}
	
	public T1Exception(Throwable pCause) {
		 super(pCause);
	}
	
	@Override
	public String toString() {
		return this.code + ": " + this.message;
	}
}
