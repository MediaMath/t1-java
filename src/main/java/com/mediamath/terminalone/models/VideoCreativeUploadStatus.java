package com.mediamath.terminalone.models;

public class VideoCreativeUploadStatus {

	private VideoCreativeUploadInfo uploadInfo;
	
	private VideoCreativeEncodingStatus encodingStatus;

	public VideoCreativeUploadInfo getUploadInfo() {
		return uploadInfo;
	}

	public void setUploadInfo(VideoCreativeUploadInfo uploadInfo) {
		this.uploadInfo = uploadInfo;
	}

	public VideoCreativeEncodingStatus getEncodingStatus() {
		return encodingStatus;
	}

	public void setEncodingStatus(VideoCreativeEncodingStatus encodingStatus) {
		this.encodingStatus = encodingStatus;
	}
	
	
}
