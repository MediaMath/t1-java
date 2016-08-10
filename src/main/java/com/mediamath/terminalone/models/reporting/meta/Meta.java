package com.mediamath.terminalone.models.reporting.meta;

import java.util.HashMap;

import com.mediamath.terminalone.models.T1Entity;

public class Meta implements T1Entity {

	private static final String entityName = "meta";

	HashMap<String, MetaData> metaData = null;

	@Override
	public String getEntityname() {
		return entityName;
	}

	public HashMap<String, MetaData> getMetaData() {
		return metaData;
	}

	public void setMetaData(HashMap<String, MetaData> metaData) {
		this.metaData = metaData;
	}

}
