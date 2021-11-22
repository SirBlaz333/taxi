package com.Serebriakov.model.util;

public class Password {
    public static String hashcode(String password){
        StringBuilder result = new StringBuilder();
        for(int i=0; i<password.length(); i++){
            result.append(password.charAt(i)*3+1);
        }
        if(result.length()>45){
            return result.substring(0, 44);
        }
        return result.toString();
    }
}
