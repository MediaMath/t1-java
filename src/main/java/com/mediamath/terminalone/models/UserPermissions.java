package com.mediamath.terminalone.models;

import com.mediamath.terminalone.exceptions.ClientException;

import javax.ws.rs.core.Form;

public class UserPermissions extends Entity {

    public UserPermissions() {
        super("UserPermissions");
    }

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

}
