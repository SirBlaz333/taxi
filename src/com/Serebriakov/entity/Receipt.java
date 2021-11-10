package com.Serebriakov.entity;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.state.Receipt_states;
import com.Serebriakov.database.type.Car_types;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

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
    private String carType;
    private int passengers;
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

    public static Receipt createReceipt(User user, Car car, String carType, int length, String date, int passengers, String departure, String destination) throws IOException, SQLException {
        CarDAO carDAO = new CarDAOImpl();
        Receipt receipt = new Receipt();
        receipt.setCarType(carType);
        receipt.setPassengers(passengers);
        receipt.setUserId(user.getId());
        if(car != null){
            receipt.setCarId(car.getId());
        }
        receipt.setDeparture(departure);
        receipt.setDestination(destination);
        receipt.setDate(date);
        receipt.setLength(length);
        receipt.setPricePerKm(carDAO.findPrice(Car_types.getType(carType), length));
        receipt.setPrice(receipt.getPricePerKm()*length);
        receipt.setState(Receipt_states.CREATED);

        return receipt;
    }
}
