package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.database.SQLQuery;
import com.Serebriakov.entity.Car;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.entity.state.Receipt_state;
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

public class ReceiptDAOImpl implements ReceiptDAO {
    private static Logger logger = LogManager.getLogger("DBError");
    private static DatabaseManager dbManager;
    private static ReceiptDAOImpl receiptDAO;

    static {
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
            PreparedStatement ps = connection.prepareStatement(SQLQuery.ReceiptQuery.ADD_RECEIPT);
            PreparedStatement idPs = connection.prepareStatement(SQLQuery.ReceiptQuery.FIND_LAST_RECEIPT_ID);
            PreparedStatement carPs = connection.prepareStatement(SQLQuery.ReceiptQuery.ADD_CARS_TO_RECEIPT)){
            int k = 1;
            ps.setInt(k++, receipt.getUserId());
            ps.setInt(k++, receipt.getPrice());
            ps.setInt(k++, receipt.getLength());
            ps.setString(k++, receipt.getDestination());
            ps.setString(k++, receipt.getDeparture());
            ps.setString(k++, receipt.getTime());
            ps.setInt(k++, getStateId(Receipt_state.getStringState(receipt.getState())));
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
        }  catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return id;
    }


    @Override
    public Receipt getReceiptById(int id) throws DBException {
        Receipt receipt = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLQuery.ReceiptQuery.GET_RECEIPT_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipt = setReceipt(rs);
            }
        }  catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return receipt;
    }

    @Override
    public List<Receipt> getUserReceipts(int userId) throws DBException {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLQuery.ReceiptQuery.GET_RECEIPTS_BY_USER_ID)){
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipts.add(setReceipt(rs));
            }
        }  catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return receipts;
    }

    @Override
    public int getStateId(String state) throws DBException {
        int id = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLQuery.ReceiptQuery.GET_STATE_ID)){
            ps.setString(1, state);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        }  catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return id;
    }

    private Receipt setReceipt(ResultSet rs) throws SQLException, DBException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getInt(1));
        receipt.setUserId(rs.getInt(2));
        receipt.setPrice(rs.getInt(3));
        receipt.setLength(rs.getInt(4));
        receipt.setDeparture(rs.getString(5));
        receipt.setDestination(rs.getString(6));
        receipt.setTime(rs.getString(7));
        receipt.setState(Receipt_state.getState(getState(rs.getInt(8))));
        return receipt;
    }

    private String getState(int stateId) throws DBException {
        String state = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLQuery.ReceiptQuery.GET_STATE)){
            ps.setInt(1, stateId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                state = rs.getString("state");
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return state;
    }

    private List<Integer> getCarsId(int id) throws DBException {
        List<Integer> cars = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLQuery.ReceiptQuery.FIND_CARS_BY_RECEIPT_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cars.add(rs.getInt("cars_id"));
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return cars;
    }

    @Override
    public void deleteReceipt(int id) throws DBException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLQuery.ReceiptQuery.DELETE_RECEIPT);
            PreparedStatement deleteCarsPs = connection.prepareStatement(SQLQuery.ReceiptQuery.DELETE_CARS_FROM_RECEIPT)){
            ps.setInt(1, id);
            ps.execute();
            deleteCarsPs.setInt(1, id);
            deleteCarsPs.execute();
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void confirmReceipt(int id) throws DBException {
        CarDAO carDAO = CarDAOImpl.getInstance();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLQuery.ReceiptQuery.CONFIRM_RECEIPT)){
            ps.setInt(1, id);
            ps.execute();
            List<Integer> carsId = getCarsId(id);
            for(int carId : carsId){
                carDAO.confirmCarForTrip(carId);
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public List<Receipt> getAllReceipts() throws DBException {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLQuery.ReceiptQuery.GET_ALL_RECEIPTS)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipts.add(setReceipt(rs));
            }
        } catch (SQLException e){
            logger.error("Error: " + e.getMessage());
            throw new DBException(e.getMessage());
        }
        return receipts;
    }
}
