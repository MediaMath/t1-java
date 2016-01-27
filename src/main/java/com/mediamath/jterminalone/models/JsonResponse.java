package com.mediamath.jterminalone.models;

import com.google.gson.annotations.SerializedName;

public class JsonResponse<T> {

	public JsonResponse() {
		// TODO Auto-generated constructor stub
	}

	@SerializedName("data")
	T data;

	@SerializedName("error")
	String error;

	@SerializedName("meta")
	T1Meta meta;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
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
