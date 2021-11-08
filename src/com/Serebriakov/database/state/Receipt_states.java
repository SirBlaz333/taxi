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

    public static String getStringState(Receipt_states state){
        switch (state){
            case CREATED:
                return "created";
            case CONFIRMED:
                return "confirmed";
            case DONE:
                return "done";
            default:
                return null;
        }
    }
}
