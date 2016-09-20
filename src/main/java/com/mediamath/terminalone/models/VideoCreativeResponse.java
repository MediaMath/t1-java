package com.mediamath.terminalone.models;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class VideoCreativeResponse {
	
	String creativeId;
	
	String key;
	
	VideoCreativeUploader uploader;
	
	Status status;
	
	@SerializedName("errors")
	ArrayList<T1Error> errors;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCreativeId() {
		return creativeId;
	}

	public void setCreativeId(String creativeId) {
		this.creativeId = creativeId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public VideoCreativeUploader getUploader() {
		return uploader;
	}

	public void setUploader(VideoCreativeUploader uploader) {
		this.uploader = uploader;
	}

	public ArrayList<T1Error> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<T1Error> errors) {
		this.errors = errors;
	}
	
}
