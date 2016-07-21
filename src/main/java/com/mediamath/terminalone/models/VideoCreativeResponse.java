package com.mediamath.terminalone.models;

public class VideoCreativeResponse {
	
	String creativeId;
	
	Status status;
	
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
	
}
