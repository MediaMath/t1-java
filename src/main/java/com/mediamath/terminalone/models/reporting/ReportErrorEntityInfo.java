package com.mediamath.terminalone.models.reporting;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


public class ReportErrorEntityInfo {
	
	@JacksonXmlProperty(isAttribute = true)
	private String id;
	
	@JacksonXmlProperty(isAttribute = true)
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
