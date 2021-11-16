package com.Serebriakov.entity;

import com.Serebriakov.database.state.Car_state;
import com.Serebriakov.database.type.Car_type;

public class Car {
    private int id;
    private int maxPassengers;
    private Car_type type;
    private Car_state state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public Car_type getType() {
        return type;
    }

    public void setType(Car_type type) {
        this.type = type;
    }

    public Car_state getState() {
        return state;
    }

    public void setState(Car_state state) {
        this.state = state;
    }
}
