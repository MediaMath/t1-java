package com.mediamath.terminalone.models.reporting;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class ReportStatus {

	@JacksonXmlProperty(isAttribute = true)
	private String code;
	
	@JacksonXmlProperty(isAttribute = true)
	private String reason;
	
	@JacksonXmlText(value = true)
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
