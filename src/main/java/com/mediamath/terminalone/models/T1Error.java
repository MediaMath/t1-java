package com.mediamath.terminalone.models;

import java.util.ArrayList;

public class T1Error {
	
	String type;
	
	String field;
	
	String message;
	
	String content;
	
	ArrayList<FieldError> fieldError;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<FieldError> getFieldError() {
		return fieldError;
	}

	public void setFieldError(ArrayList<FieldError> fieldError) {
		this.fieldError = fieldError;
	}

}
