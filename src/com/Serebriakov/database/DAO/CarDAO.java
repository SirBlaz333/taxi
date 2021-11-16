package com.Serebriakov.database.DAO;

import com.Serebriakov.database.state.Car_state;
import com.Serebriakov.database.type.Car_type;
import com.Serebriakov.entity.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    void updateCarState(Car car) throws SQLException;
    void confirmCarForTrip(int id) throws SQLException;
    Car findCar(int passengers, Car_type type) throws SQLException;
    int findCarTypeId(Car_type type) throws SQLException;
    int findPrice(Car_type type, double length) throws SQLException;
    List<Car> findCarList(int passengers, Car_type type) throws SQLException;
}
