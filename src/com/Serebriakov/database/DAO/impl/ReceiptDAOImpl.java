package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DatabaseManager;
import com.Serebriakov.entity.state.Receipt_state;
import com.Serebriakov.entity.Car;
import com.Serebriakov.entity.Receipt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.Serebriakov.database.SQLQuery.ReceiptQuery.*;

public class ReceiptDAOImpl implements ReceiptDAO {
    private static DatabaseManager dbManager;
    private static ReceiptDAOImpl receiptDAO;

    static {
        receiptDAO = null;
    }

    private ReceiptDAOImpl() throws IOException {
        dbManager = DatabaseManager.getInstance();
    }

    public static synchronized ReceiptDAOImpl getInstance() throws IOException {
        if(receiptDAO == null){
            receiptDAO = new ReceiptDAOImpl();
        }
        return receiptDAO;
    }

    @Override
    public int addReceipt(Receipt receipt, Car... cars) throws SQLException {
        int id = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_RECEIPT);
            PreparedStatement idPs = connection.prepareStatement(FIND_LAST_RECEIPT_ID);
            PreparedStatement carPs = connection.prepareStatement(ADD_CARS_TO_RECEIPT)){
            ps.setInt(1, receipt.getUserId());
            ps.setInt(2, receipt.getPrice());
            ps.setInt(3, receipt.getLength());
            ps.setString(4, receipt.getDestination());
            ps.setString(5, receipt.getDeparture());
            ps.setString(6, receipt.getTime());
            ps.setInt(7, getStateId(Receipt_state.getStringState(receipt.getState())));
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
        }
        return id;
    }


    @Override
    public Receipt getReceiptById(int id) throws SQLException {
        Receipt receipt = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_RECEIPT_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipt = setReceipt(rs);
            }
        }
        return receipt;
    }

    @Override
    public List<Receipt> getUserReceipts(int userId) throws SQLException {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_RECEIPTS_BY_USER_ID)){
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipts.add(setReceipt(rs));
            }
        }
        return receipts;
    }

    @Override
    public int getStateId(String state) throws SQLException {
        int id = -1;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_STATE_ID)){
            ps.setString(1, state);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        }
        return id;
    }

    private Receipt setReceipt(ResultSet rs) throws SQLException {
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

    private String getState(int stateId) throws SQLException {
        String state = null;
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_STATE)){
            ps.setInt(1, stateId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                state = rs.getString("state");
            }
        }
        return state;
    }

    private List<Integer> getCarsId(int id) throws SQLException {
        List<Integer> cars = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_CARS_BY_RECEIPT_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cars.add(rs.getInt("cars_id"));
            }
        }
        return cars;
    }

    @Override
    public void deleteReceipt(int id) throws SQLException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_RECEIPT);
            PreparedStatement deleteCarsPs = connection.prepareStatement(DELETE_CARS_FROM_RECEIPT)){
            ps.setInt(1, id);
            ps.execute();
            deleteCarsPs.setInt(1, id);
            deleteCarsPs.execute();
        }
    }

    @Override
    public void confirmReceipt(int id) throws SQLException, IOException {
        CarDAO carDAO = CarDAOImpl.getInstance();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(CONFIRM_RECEIPT)){
            ps.setInt(1, id);
            ps.execute();
            List<Integer> carsId = getCarsId(id);
            for(int carId : carsId){
                carDAO.confirmCarForTrip(carId);
            }
        }
    }

    @Override
    public List<Receipt> getAllReceipts() throws SQLException {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_RECEIPTS)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                receipts.add(setReceipt(rs));
            }
        }
        return receipts;
    }
}
