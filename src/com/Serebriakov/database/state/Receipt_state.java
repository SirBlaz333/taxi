package com.Serebriakov.database.state;

public enum Receipt_state {
    CREATED,
    CONFIRMED,
    DONE;
    public static Receipt_state getState(String state){
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

    public static String getStringState(Receipt_state state){
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
