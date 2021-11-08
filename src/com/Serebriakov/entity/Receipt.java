package com.Serebriakov.entity;

import com.Serebriakov.database.state.Receipt_states;

public class Receipt {

    private int id;
    private int userId;
    private int carId;
    private int price;
    private int length;
    private double priceDouble;
    private double lengthDouble;
    private int pricePerKm;
    private String destination;
    private String departure;
    private String date;
    private Receipt_states state;

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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Receipt_states getState() {
        return state;
    }

    public void setState(Receipt_states state) {
        this.state = state;
    }
}
