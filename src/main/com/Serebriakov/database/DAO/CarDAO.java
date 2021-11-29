package com.Serebriakov.database.DAO;

import com.Serebriakov.database.entity.Car;
import com.Serebriakov.database.entity.state.Car_state;
import com.Serebriakov.database.entity.type.Car_type;
import com.Serebriakov.exception.DBException;

import java.util.List;

public interface CarDAO {
    Car getCar(int id) throws DBException;
    Car_type getCarType(int type_id) throws DBException;
    Car_state getCarState(int state_id) throws DBException;
    void updateCarState(Car car) throws DBException;
    void confirmCarForTrip(int id) throws DBException;
    Car findCar(int passengers, Car_type type) throws DBException;
    int findCarTypeId(Car_type type) throws DBException;
    int findPrice(Car_type type) throws DBException;
    List<Car> findCarList(int passengers, Car_type type) throws DBException;
}
