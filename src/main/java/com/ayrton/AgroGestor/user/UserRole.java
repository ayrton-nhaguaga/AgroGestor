package com.ayrton.AgroGestor.user;

public enum UserRole {
    ENGINEER("engenheiro"),
    MANAGER("manager"),
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}

