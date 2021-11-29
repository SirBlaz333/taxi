package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.database.SQLQuery;
import com.Serebriakov.database.entity.Car;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.entity.state.Receipt_state;
import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.exception.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.Serebriakov.database.SQLQuery.ReceiptQuery.*;

public class ReceiptDAOImpl implements ReceiptDAO {
    private static Logger logger = LogManager.getLogger("DB");
    private static DatabaseManager dbManager;
    private static ReceiptDAOImpl receiptDAO;

    static {
        logger.info("Class " + ReceiptDAOImpl.class.getName() + " has been uploaded");
        receiptDAO = null;
    }

    private ReceiptDAOImpl() {
        dbManager = DatabaseManager.getInstance();
    }

    public static synchronized ReceiptDAOImpl getInstance() {
        if(receiptDAO == null){
            receiptDAO = new ReceiptDAOImpl();
        }
        return receiptDAO;
    }

    @Override
    public int addReceipt(Receipt receipt, Car... cars) throws DBException {
        int id = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_RECEIPT);
            PreparedStatement idPs = connection.prepareStatement(FIND_LAST_RECEIPT_ID);
            PreparedStatement carPs = connection.prepareStatement(ADD_CARS_TO_RECEIPT)){
            int k = 0;
            ps.setInt(++k, receipt.getUserId());
            ps.setInt(++k, receipt.getPrice());
            ps.setInt(++k, receipt.getLength());
            ps.setString(++k, receipt.getDestination());
            ps.setString(++k, receipt.getDeparture());
            ps.setString(++k, receipt.getTime());
            ps.setInt(++k, getStateId(Receipt_state.getStringState(receipt.getState())));
            ps.setInt(++k, receipt.getPassengers());
            ps.execute();

            ResultSet rs = idPs.executeQuery();
            while(rs.next()){
                id = rs.getInt("LAST_INSERT_ID()");
            }
            for(Car car : cars){
                carPs.setInt(1, id);
                carPs.setInt(2, car.getId());
                carPs.execute();
            }

            String message = String.format("Receipt(%d) has been added to database", id);
            logger.info(message);
        }  catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot add receipt");
        }
        return id;
    }


    @Override
    public Receipt getReceiptById(int id) throws DBException {
        Receipt receipt = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_RECEIPT_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipt = setReceipt(rs);
            }
            String message;
            if(receipt != null){
                message = String.format("Receipt(%s) received from database", id);
            } else {
                message = String.format("Cannot receive receipt(%s) from database ", id);
            }
            logger.info(message);
        }  catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot get receipt");
        }
        return receipt;
    }

    @Override
    public List<Receipt> getUserReceipts(int userId) throws DBException {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_RECEIPTS_BY_USER_ID)){
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipts.add(setReceipt(rs));
            }
            String message = String.format("User's(%d) receipts have been received from database", userId);
            logger.info(message);
        }  catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("cannot get user's receipts");
        }
        return receipts;
    }

    @Override
    public int getStateId(String state) throws DBException {
        int id = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_STATE_ID)){
            ps.setString(1, state);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
            String message = String.format("Receive receipt's state from database (%s ==> %d)", state, id);
            logger.info(message);
        }  catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot get state id");
        }
        return id;
    }

    private Receipt setReceipt(ResultSet rs) throws SQLException, DBException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getInt(1));
        int userId = rs.getInt(2);
        receipt.setUserId(userId);
        receipt.setUser(UserDAOImpl.getInstance().getUserById(userId));
        receipt.setPrice(rs.getInt(3));
        receipt.setLength(rs.getInt(4));
        receipt.setDeparture(rs.getString(5));
        receipt.setDestination(rs.getString(6));
        receipt.setTime(rs.getString(7));
        receipt.setState(Receipt_state.getState(getState(rs.getInt(8))));
        receipt.setPassengers(rs.getInt(9));
        return receipt;
    }

    private String getState(int stateId) throws DBException {
        String state = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_STATE)){
            ps.setInt(1, stateId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                state = rs.getString("state");
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("cannot get state");
        }
        return state;
    }

    @Override
    public List<Integer> getCarsIdByReceiptId(int receipt_id) throws DBException {
        List<Integer> cars = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_CARS_BY_RECEIPT_ID)){
            ps.setInt(1, receipt_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cars.add(rs.getInt("cars_id"));
            }
            String message = String.format("Received list of cars id for receipt(%d)", receipt_id);
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot get car id");
        }
        return cars;
    }

    @Override
    public void deleteReceipt(int id) throws DBException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_RECEIPT);
            PreparedStatement deleteCarsPs = connection.prepareStatement(DELETE_CARS_FROM_RECEIPT)){
            ps.setInt(1, id);
            ps.execute();
            deleteCarsPs.setInt(1, id);
            deleteCarsPs.execute();
            String message = String.format("Receipt(%d) has been deleted from database", id);
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot delete receipt");
        }
    }

    @Override
    public void confirmReceipt(int id) throws DBException {
        CarDAO carDAO = CarDAOImpl.getInstance();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(CONFIRM_RECEIPT)){
            ps.setInt(1, id);
            ps.execute();
            List<Integer> carsId = getCarsIdByReceiptId(id);
            for(int carId : carsId){
                carDAO.confirmCarForTrip(carId);
            }
            String message = String.format("Receipt(%d) has been confirmed", id);
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot confirm receipt");
        }
    }

    @Override
    public List<Receipt> getAllReceipts() throws DBException {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_RECEIPTS)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipts.add(setReceipt(rs));
            }
            logger.info("All receipts have been received from database");
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException("Cannot get all receipts");
        }
        return receipts;
    }

    public List<Receipt> getAllReceiptsWithSuchState(int state_id) throws DBException {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_RECEIPTS_WITH_STATE)){
            ps.setInt(1, state_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipts.add(setReceipt(rs));
            }
            String message = String.format("Receipts with state(%d) have been received from database", state_id);
            logger.info(message);
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            String message = String.format("Cannot get receipts with state(%d)", state_id);
            throw new DBException(message);
        }
        return receipts;
    }
}
