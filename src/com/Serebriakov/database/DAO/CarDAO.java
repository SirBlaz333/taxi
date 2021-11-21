package com.Serebriakov.database.DAO;

import com.Serebriakov.entity.Car;
import com.Serebriakov.entity.type.Car_type;
import com.Serebriakov.exception.DBException;

import java.util.List;

public interface CarDAO {
    void updateCarState(Car car) throws DBException;
    void confirmCarForTrip(int id) throws DBException;
    Car findCar(int passengers, Car_type type) throws DBException;
    int findCarTypeId(Car_type type) throws DBException;
    int findPrice(Car_type type, double length) throws DBException;
    List<Car> findCarList(int passengers, Car_type type) throws DBException;

}
