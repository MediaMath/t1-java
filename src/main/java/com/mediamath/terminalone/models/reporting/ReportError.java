package com.mediamath.terminalone.models.reporting;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Result")
public class ReportError {
	
	@JacksonXmlProperty(localName = "status")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ReportStatus[] status;
	
	@JacksonXmlProperty(localName="entity")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ReportErrorEntityInfo[] entity;

	public ReportStatus[] getStatus() {
		return status;
	}

	public void setStatus(ReportStatus[] status) {
		this.status = status;
	}

	public ReportErrorEntityInfo[] getEntity() {
		return entity;
	}

	public void setEntity(ReportErrorEntityInfo[] entity) {
		this.entity = entity;
	}


}
