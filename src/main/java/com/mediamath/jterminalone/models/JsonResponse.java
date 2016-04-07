package com.mediamath.jterminalone.models;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class JsonResponse<T> {
	
	public JsonResponse() {}

	public JsonResponse(T datavalue) {
		this.data = datavalue;
	}
	
	@SerializedName("data")
	T data;

	@SerializedName("errors")
	ArrayList<T1Error> errors;

	@SerializedName("meta")
	T1Meta meta;
	
	public ArrayList<T1Error> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<T1Error> errors) {
		this.errors = errors;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T1Meta getMeta() {
		return meta;
	}

	public void setMeta(T1Meta meta) {
		this.meta = meta;
	}

}
