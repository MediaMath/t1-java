package com.mediamath.terminalone.models;

import javax.ws.rs.core.Form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mediamath.terminalone.exceptions.ClientException;
import org.javers.core.metamodel.annotation.DiffIgnore;

public class UserPermissions implements T1Entity{

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
	@DiffIgnore
	@JsonIgnore
	public Form getForm() {
		return null;
	}
	
	@Override
	@DiffIgnore
	@JsonIgnore
	public String getUri() throws ClientException {
		return null;
	}
}
