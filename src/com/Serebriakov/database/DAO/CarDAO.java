package com.Serebriakov.database.DAO;

import com.Serebriakov.database.type.Car_types;
import com.Serebriakov.entity.Car;

import java.sql.SQLException;

public interface CarDAO {
    void updateCar(Car car);
    Car findCar(int passengers, Car_types type) throws SQLException;
    int findCarTypeId(Car_types type) throws SQLException;
    double findPrice(Car_types type, double length) throws SQLException;
}
