package com.mediamath.jterminalone.models;

import java.util.List;

public class EntityInfo {

	List<T1Property> prop;
	
	int version;
	
	String name;
	
	String id;
	
	String type;

	public List<T1Property> getProp() {
		return prop;
	}

	public void setProp(List<T1Property> prop) {
		this.prop = prop;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
