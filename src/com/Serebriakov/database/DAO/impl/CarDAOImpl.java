package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.entity.state.Car_state;
import com.Serebriakov.entity.type.Car_type;
import com.Serebriakov.entity.Car;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.Serebriakov.database.SQLQuery.CarQuery.*;

public class CarDAOImpl implements CarDAO {
    private static DatabaseManager dbManager;
    private static CarDAOImpl carDAO;

    static {
        carDAO = null;
    }

    private CarDAOImpl() throws IOException {
        dbManager = DatabaseManager.getInstance();
    }

    public static synchronized CarDAOImpl getInstance() throws IOException {
        if(carDAO == null){
            carDAO = new CarDAOImpl();
        }
        return carDAO;
    }

    @Override
    public void updateCarState(Car car) throws SQLException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CAR_STATE)){
            int stateId = getStateId(car.getState());
            ps.setInt(1, stateId);
            ps.setInt(2, car.getId());
            ps.execute();
        }
    }

    @Override
    public void confirmCarForTrip(int id) throws SQLException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CAR_STATE)){
            int stateId = getStateId(Car_state.ON_TRIP);
            ps.setInt(1, stateId);
            ps.setInt(2, id);
            ps.execute();
        }
    }

    private int getStateId(Car_state state){
        int state_id = 0;
        for(Car_state currentState: Car_state.values()){
            state_id++;
            if(currentState == state){
                return state_id;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Car findCar(int passengers, Car_type type) throws SQLException {
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
                car.setState(Car_state.AVAILABLE);
            }
        }
        return car;
    }

    @Override
    public int findCarTypeId(Car_type type) throws SQLException {
        int id = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_CAR_TYPE_ID)){
            ps.setString(1, Car_type.getStringType(type));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        }
        return id;
    }

    @Override
    public int findPrice(Car_type type, double length) throws SQLException {
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
    public List<Car> findCarList(int passengers, Car_type type) throws SQLException {
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
