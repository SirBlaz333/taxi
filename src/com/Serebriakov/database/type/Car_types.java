package com.Serebriakov.database.type;

public enum Car_types {
    PUBLIC_HIRE_TAXI,
    MINICAB,
    MINIBUS;
    public static Car_types getCar(String car){
        switch (car){
            case "Public hire taxi":
                return PUBLIC_HIRE_TAXI;
            case "Minicab":
                return MINICAB;
            case "Minibus":
                return MINIBUS;
            default:
                return null;
        }
    }
}
