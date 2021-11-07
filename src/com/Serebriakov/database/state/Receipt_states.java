package com.Serebriakov.database.state;

public enum Receipt_states {
    CREATED,
    CONFIRMED,
    DONE;
    public static Receipt_states getState(String state){
        switch (state){
            case "created":
                return CREATED;
            case "confirmed":
                return CONFIRMED;
            case "done":
                return DONE;
            default:
                return null;
        }
    }
}
