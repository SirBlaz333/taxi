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
        int type_id = findCarTypeId(type);
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_AVAILABLE_CAR)){
            ps.setInt(1, passengers);
            ps.setInt(2, type_id);
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
    public double findPrice(Car_types type, double length) throws SQLException {
        int typeId = findCarTypeId(type);
        double price = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_TARIFF)){
            ps.setInt(1, typeId);
            ps.setDouble(2, length);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                price = rs.getDouble("price_for_km");
            }
        }
        return price*length;
    }

}
