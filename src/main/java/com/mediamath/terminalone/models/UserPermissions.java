package com.mediamath.terminalone.models;

public class UserPermissions extends Entity {
    private User user;
    private Permissions permissions;

    public UserPermissions() {
        super("UserPermissions");
    }

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
