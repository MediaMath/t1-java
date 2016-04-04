package com.mediamath.jterminalone.models;

import java.util.List;

public class T1Error {
	
	String type;
	
	String field;
	
	String message;
	
	String content;
	
	List<FieldError> fieldError;

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

	public List<FieldError> getFieldError() {
		return fieldError;
	}

	public void setFieldError(List<FieldError> fieldError) {
		this.fieldError = fieldError;
	}
	

}
