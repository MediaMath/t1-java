package com.mediamath.terminalone.models;

public class T1Data {

	T1Session session;
	
	String entity_type;
	String name;
	String id;
	
	public T1Session getSession() {
		return session;
	}
	public void setSession(T1Session session) {
		this.session = session;
	}
	public String getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
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
}
