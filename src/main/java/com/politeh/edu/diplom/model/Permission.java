package com.politeh.edu.diplom.model;

public enum Permission {
    USER_READ("user:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }


}
