package com.mediamath.terminalone.models;

public class ThreePASCreativesBatchUpload {
	
	private ThreePASCreativeBatch batch;
	
	private String called_on;
	
	private Status status;

	public ThreePASCreativeBatch getBatch() {
		return batch;
	}

	public void setBatch(ThreePASCreativeBatch batch) {
		this.batch = batch;
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

}
