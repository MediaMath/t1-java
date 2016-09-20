package com.mediamath.terminalone.models;

import java.util.ArrayList;

public class VideoCreativeUploadInfo {

	private String status;
	
	private long size;
	
	private long percent;
	
	private String name;
	
	private ArrayList<String> mirrorPath = new ArrayList<String>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getPercent() {
		return percent;
	}

	public void setPercent(long percent) {
		this.percent = percent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getMirrorPath() {
		return mirrorPath;
	}

	public void setMirrorPath(ArrayList<String> mirrorPath) {
		this.mirrorPath = mirrorPath;
	}


}
