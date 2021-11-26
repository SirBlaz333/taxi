package com.Serebriakov.database.entity.type;

public enum Role {
    USER,
    ADMIN;
    public static Role getRole(String role){
        if(role.equals("user")){
            return USER;
        }
        return ADMIN;
    }
    public static String getStringRole(Role role){
        if(role.equals(Role.ADMIN)){
            return "admin";
        }
        return "user";
    }
}
