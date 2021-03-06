package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.database.entity.Car;
import com.Serebriakov.database.entity.type.Car_type;
import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.entity.state.Car_state;
import com.Serebriakov.exception.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.Serebriakov.database.SQLQuery.CarQuery.*;

public class CarDAOImpl implements CarDAO {
    private static Logger logger = LogManager.getLogger("DB");
    private static DatabaseManager dbManager;
    private static CarDAOImpl carDAO;

    static {
        logger.info("Class " + CarDAOImpl.class.getName() + " has been uploaded");
        carDAO = null;
    }

    private CarDAOImpl(){
        dbManager = DatabaseManager.getInstance();
    }

    public static synchronized CarDAOImpl getInstance(){
        if(carDAO == null){
            carDAO = new CarDAOImpl();
        }
        return carDAO;
    }

    @Override
    public Car getCar(int id) throws DBException {
        Car car = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement psCar = connection.prepareStatement(GET_CAR_BY_ID)){
            psCar.setInt(1, id);
            ResultSet rs = psCar.executeQuery();
            while(rs.next()){
                car = new Car();
                car.setId(id);
                car.setType(getCarType(rs.getInt("type_id")));
                car.setMaxPassengers(rs.getInt("max_passengers"));
                car.setState(getCarState(rs.getInt("state_id")));
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot receive car by id");
        }
        return car;
    }

    @Override
    public Car_type getCarType(int type_id) throws DBException {
        Car_type car_type = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_CAR_TYPE)){
            ps.setInt(1, type_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                car_type = Car_type.getType(rs.getString("type"));
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot receive car's type");
        }
        return car_type;
    }

    @Override
    public Car_state getCarState(int state_id) throws DBException {
        Car_state car_state = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_CAR_STATE)){
            ps.setInt(1, state_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                car_state = Car_state.getState(rs.getString("state"));
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot receive car's state");
        }
        return car_state;
    }

    @Override
    public void updateCarState(Car car) throws DBException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CAR_STATE)){
            int stateId = getStateId(car.getState());
            ps.setInt(1, stateId);
            ps.setInt(2, car.getId());
            ps.execute();
            String message = String.format("Car's(%d) state has been updated to %s", car.getId(), car.getState());
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot update car's state");
        }
    }

    @Override
    public void confirmCarForTrip(int id) throws DBException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_CAR_STATE)){
            int stateId = getStateId(Car_state.ON_TRIP);
            ps.setInt(1, stateId);
            ps.setInt(2, id);
            ps.execute();
            String message = String.format("Car(%d) has been confirmed to trip", id);
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot confirm car for trip");
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
    public Car findCar(int passengers, Car_type type) throws DBException {
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
            String message;
            if(car == null){
                message = String.format("Cannot receive car with such parameters (max passengers = %d, car type = %s", passengers, type);
            } else {
                message = String.format("Car(%d) received from database", car.getId());
            }
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot find car");
        }
        return car;
    }

    @Override
    public int findCarTypeId(Car_type type) throws DBException {
        int id = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_CAR_TYPE_ID)){
            ps.setString(1, Car_type.getStringType(type));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            String message = String.format("Receive car's type id from database (%s ==> %d)", type, id);
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot find car type");
        }
        return id;
    }

    @Override
    public int findPrice(Car_type type) throws DBException {
        int typeId = findCarTypeId(type);
        int price = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_TARIFF)){
            ps.setInt(1, typeId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                price = rs.getInt("price_for_km");
            }
            String message = String.format("Tariff received from database (car type = %s, price per km = %d)", type, price);
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot find price for trip");
        }
        return price;
    }

    @Override
    public List<Car> findCarList(int passengers, Car_type type) throws DBException {
        List<Car> cars = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_AVAILABLE_CAR_LIST)) {
            int typeId = findCarTypeId(type);
            ps.setInt(1, typeId);
            ResultSet rs = ps.executeQuery();
            logger.info("Trying to find list of cars: ");
            while(rs.next()){
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setMaxPassengers(rs.getInt("max_passengers"));
                car.setState(Car_state.AVAILABLE);
                car.setType(type);
                passengers -= car.getMaxPassengers();
                String message = String.format("Car(%d) received from database", car.getId());
                logger.info(message);
                cars.add(car);
                if(passengers <= 0){
                    return cars;
                }
            }
        } catch (SQLException e) {
            logger.error("Error" + e.getMessage());
        }
        return null;
    }

}
