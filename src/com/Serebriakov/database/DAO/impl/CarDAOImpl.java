package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.database.state.Car_states;
import com.Serebriakov.database.type.Car_types;
import com.Serebriakov.entity.Car;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.Serebriakov.database.SQLQuery.CarQuery.*;

public class CarDAOImpl implements CarDAO {
    private static DatabaseManager dbManager;

    public CarDAOImpl() throws IOException {
        dbManager = DatabaseManager.getInstance();
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public Car findCar(int passengers, Car_types type) throws SQLException {
        Car car = null;
        int typeId = findCarTypeId(type);
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_AVAILABLE_CAR)){
            ps.setInt(1, passengers);
            ps.setInt(2, typeId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                car = new Car();
                car.setType(type);
                car.setMaxPassengers(rs.getInt("max_passengers"));
                car.setId(rs.getInt("id"));
                car.setState(Car_states.AVAILABLE);
            }
        }
        return car;
    }

    @Override
    public int findCarTypeId(Car_types type) throws SQLException {
        int id = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_CAR_TYPE_ID)){
            ps.setString(1, Car_types.getStringType(type));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        }
        return id;
    }

    @Override
    public int findPrice(Car_types type, double length) throws SQLException {
        int typeId = findCarTypeId(type);
        int price = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_TARIFF)){
            ps.setInt(1, typeId);
            ps.setDouble(2, length);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                price = rs.getInt("price_for_km");
            }
        }
        return price;
    }

    @Override
    public List<Car> findCarList(int passengers, Car_types type) throws SQLException {
        List<Car> cars = new ArrayList<>();
        int currentAmountOfPassengers = passengers;
        while(passengers > 0){
            Car car = findCar(currentAmountOfPassengers, type);
            if(car == null){
                currentAmountOfPassengers--;
            } else {
                cars.add(car);
                passengers -= car.getMaxPassengers();
            }
            if(currentAmountOfPassengers == 0){
                return null;
            }
        }
        return cars;
    }

}
