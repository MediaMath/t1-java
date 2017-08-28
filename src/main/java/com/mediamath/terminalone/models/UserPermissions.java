package com.mediamath.terminalone.models;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.exceptions.ClientException;

public class UserPermissions implements T1Entity {

	private static final String entityName = "UserPermissions";
	
	private User user;
	private Permissions permissions;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Permissions getPermissions() {
		return permissions;
	}
	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}
	
	@Override
	public String getEntityname() {
		return entityName;
	}
	
	@Override
	public Form getForm() {
		return null;
	}
	
	@Override
	public String getUri() throws ClientException {
		return null;
	}
	
	
	
}
