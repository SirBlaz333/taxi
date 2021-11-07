package com.Serebriakov.database.state;

public enum Car_states {
    AVAILABLE,
    ON_TRIP,
    NOT_ACTIVE;
    public static Car_states getState(String state){
        switch (state){
            case "available to order":
                return AVAILABLE;
            case "is on a trip":
                return ON_TRIP;
            case "not active":
                return NOT_ACTIVE;
            default:
                return null;
        }
    }
}
