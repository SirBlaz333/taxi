package com.Serebriakov.database.DAO;

import com.Serebriakov.database.type.Car_types;
import com.Serebriakov.entity.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    void updateCar(Car car);
    Car findCar(int passengers, Car_types type) throws SQLException;
    int findCarTypeId(Car_types type) throws SQLException;
    int findPrice(Car_types type, double length) throws SQLException;
    List<Car> findCarList(int passengers, Car_types type) throws SQLException;
}
