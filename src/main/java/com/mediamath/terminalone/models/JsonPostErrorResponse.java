package com.mediamath.terminalone.models;

public class JsonPostErrorResponse {
	
	String etag;

	T1Error error;
	
	Object errors;
	
	T1Meta meta;
	
	public JsonPostErrorResponse() {}
	
	public JsonPostErrorResponse(Object errors) {
		this.setErrors(errors);
	}


	public String getEtag() {
		return etag;
	}


	public void setEtag(String etag) {
		this.etag = etag;
	}


	public T1Error getError() {
		return error;
	}


	public void setError(T1Error error) {
		this.error = error;
	}


	public Object getErrors() {
		return errors;
	}


	public void setErrors(Object errors) {
		this.errors = errors;
	}

	public T1Meta getMeta() {
		return meta;
	}

	public void setMeta(T1Meta meta) {
		this.meta = meta;
	}
}


