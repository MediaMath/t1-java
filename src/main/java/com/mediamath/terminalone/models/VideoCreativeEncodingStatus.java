package com.mediamath.terminalone.models;

import java.util.ArrayList;

public class VideoCreativeEncodingStatus {
	
	private String status;
	
	private long percent;
	
	private ArrayList<VideoCreativeEncodings> encodings = new ArrayList<VideoCreativeEncodings>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getPercent() {
		return percent;
	}

	public void setPercent(long percent) {
		this.percent = percent;
	}

	public ArrayList<VideoCreativeEncodings> getEncodings() {
		return encodings;
	}

	public void setEncodings(ArrayList<VideoCreativeEncodings> encodings) {
		this.encodings = encodings;
	}

}
