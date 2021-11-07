package com.Serebriakov.database.type;

public enum Roles {
    USER,
    ADMIN;
    public static Roles getRole(String role){
        if(role.equals("user")){
            return USER;
        }
        return ADMIN;
    }
}
