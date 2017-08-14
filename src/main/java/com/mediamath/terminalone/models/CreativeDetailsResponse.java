package com.mediamath.terminalone.models;

import java.util.ArrayList;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.exceptions.ClientException;

public class CreativeDetailsResponse implements T1Entity {

	
	private int duration;
	private ArrayList<Long> autoVendors = new ArrayList<Long>();
	private ArrayList<Integer> companionIds = new ArrayList<Integer>();
	private String thumbnail;
	private Status Status;
	private VideoCreative details;
	private double percent;
	private boolean isUploaded;
	private boolean readyToServe;
	private boolean isSecure;
	private boolean isRotating;
	private boolean isDynamic;
	private boolean isAudio;
	private int vastVersion;
	
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<Long> getAutoVendors() {
		return autoVendors;
	}

	public void setAutoVendors(ArrayList<Long> autoVendors) {
		this.autoVendors = autoVendors;
	}

	public ArrayList<Integer> getCompanionIds() {
		return companionIds;
	}

	public void setCompanionIds(ArrayList<Integer> companionIds) {
		this.companionIds = companionIds;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Status getStatus() {
		return Status;
	}

	public void setStatus(Status status) {
		Status = status;
	}

	public VideoCreative getDetails() {
		return details;
	}

	public void setDetails(VideoCreative details) {
		this.details = details;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public boolean isUploaded() {
		return isUploaded;
	}

	public void setUploaded(boolean isUploaded) {
		this.isUploaded = isUploaded;
	}

	public boolean isReadyToServe() {
		return readyToServe;
	}

	public void setReadyToServe(boolean readyToServe) {
		this.readyToServe = readyToServe;
	}

	public boolean isSecure() {
		return isSecure;
	}

	public void setSecure(boolean isSecure) {
		this.isSecure = isSecure;
	}

	public boolean isRotating() {
		return isRotating;
	}

	public void setRotating(boolean isRotating) {
		this.isRotating = isRotating;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public void setDynamic(boolean isDynamic) {
		this.isDynamic = isDynamic;
	}

	public boolean isAudio() {
		return isAudio;
	}

	public void setAudio(boolean isAudio) {
		this.isAudio = isAudio;
	}

	public int getVastVersion() {
		return vastVersion;
	}

	public void setVastVersion(int vastVersion) {
		this.vastVersion = vastVersion;
	}

	@Override
	public String getEntityname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Form getForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUri() throws ClientException {
		// TODO Auto-generated method stub
		return null;
	}

}
