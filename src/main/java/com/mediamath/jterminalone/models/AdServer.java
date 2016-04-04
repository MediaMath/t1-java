package com.mediamath.jterminalone.models;

public class AdServer implements T1Entity {

	private static final String entityName = "AdServer";
	
	private int id;
	private String name;
	private int version;

	public AdServer(int id, String name, int version) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getEntityname() {
		return entityName;
	}
	
}
