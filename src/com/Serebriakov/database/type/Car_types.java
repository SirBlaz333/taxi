package com.Serebriakov.database.type;

public enum Car_types {
    PUBLIC_HIRE_TAXI,
    MINICAB,
    MINIBUS;
    public static Car_types getType(String car){
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
    public static String getStringType(Car_types type){
        switch (type){
            case PUBLIC_HIRE_TAXI:
                return "Public hire taxi";
            case MINICAB:
                return "Minicab";
            case MINIBUS:
                return "Minibus";
            default:
                return null;
        }
    }
}
