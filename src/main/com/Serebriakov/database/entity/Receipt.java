package com.Serebriakov.database.entity;

import com.Serebriakov.database.entity.state.Receipt_state;

public class Receipt {

    private int id;
    private int userId;
    private int price;
    private int length;
    private double priceDouble;
    private double lengthDouble;
    private int pricePerKm;
    private String destination;
    private String departure;
    private String time;
    private String carType;
    private int passengers;
    private Receipt_state state;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(int pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        this.priceDouble = price/100.00;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        this.lengthDouble = length/100.00;
    }

    public double getLengthDouble(){
        return lengthDouble;
    }

    public double getPriceDouble(){
        return priceDouble;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Receipt_state getState() {
        return state;
    }

    public String getStateString(){
        return Receipt_state.getStringState(state);
    }

    public void setState(Receipt_state state) {
        this.state = state;
    }

}
