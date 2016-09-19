package com.mediamath.terminalone.models;

public class VideoCreativeResponse {
	
	String creativeId;
	
	String key;
	
	VideoCreativeUploader uploader;
	
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
	
}
