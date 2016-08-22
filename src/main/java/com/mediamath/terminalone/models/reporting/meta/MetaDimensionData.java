package com.mediamath.terminalone.models.reporting.meta;

import java.util.ArrayList;

public class MetaDimensionData {
	
	boolean access;
	String name;
	String type;
	long maxLength;
	ArrayList<String> values;
	
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(long maxLength) {
		this.maxLength = maxLength;
	}
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	

}
