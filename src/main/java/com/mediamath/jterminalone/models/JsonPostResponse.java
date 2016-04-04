package com.mediamath.jterminalone.models;

import java.util.List;

public class JsonPostResponse {
	
	String etag;
	
	String called_on;
	
	Status status;
	
	T1Error error;
	
	List<T1Error> errors;
	
	EntityInfo entity;


	public String getEtag() {
		return etag;
	}


	public void setEtag(String etag) {
		this.etag = etag;
	}


	public String getCalled_on() {
		return called_on;
	}


	public void setCalled_on(String called_on) {
		this.called_on = called_on;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public T1Error getError() {
		return error;
	}


	public void setError(T1Error error) {
		this.error = error;
	}


	public List<T1Error> getErrors() {
		return errors;
	}


	public void setErrors(List<T1Error> errors) {
		this.errors = errors;
	}


	public EntityInfo getEntity() {
		return entity;
	}


	public void setEntity(EntityInfo entity) {
		this.entity = entity;
	}
	

}


