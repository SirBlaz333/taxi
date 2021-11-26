package com.Serebriakov.database.entity.state;

public enum Car_state {
    AVAILABLE,
    ON_TRIP,
    NOT_ACTIVE;
    public static Car_state getState(String state){
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
