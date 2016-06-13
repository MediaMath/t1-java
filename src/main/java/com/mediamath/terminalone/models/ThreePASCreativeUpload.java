package com.mediamath.terminalone.models;

public class ThreePASCreativeUpload implements T1Entity {
	private static final String entityName = "ThreePASCreativeUpload";
	
	ThreePASCreativeBatch batch;

	public ThreePASCreativeBatch getBatch() {
		return batch;
	}

	public void setBatch(ThreePASCreativeBatch batch) {
		this.batch = batch;
	}

	@Override
	public String getEntityname() {
		return entityName;
	}
	
}
