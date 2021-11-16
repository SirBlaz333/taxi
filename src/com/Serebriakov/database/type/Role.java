package com.Serebriakov.database.type;

public enum Role {
    USER,
    ADMIN;
    public static Role getRole(String role){
        if(role.equals("user")){
            return USER;
        }
        return ADMIN;
    }
}
