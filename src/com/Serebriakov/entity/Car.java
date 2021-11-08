package com.Serebriakov.entity;

import com.Serebriakov.database.state.Car_states;
import com.Serebriakov.database.type.Car_types;

public class Car {
    private int id;
    private int maxPassengers;
    private Car_types type;
    private Car_states state;

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

    public Car_types getType() {
        return type;
    }

    public void setType(Car_types type) {
        this.type = type;
    }

    public Car_states getState() {
        return state;
    }

    public void setState(Car_states state) {
        this.state = state;
    }
}
