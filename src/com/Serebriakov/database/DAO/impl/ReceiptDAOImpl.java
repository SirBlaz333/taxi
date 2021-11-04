package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DatabaseManager;
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

    private DatabaseManager dbManager;

    public ReceiptDAOImpl() throws IOException {
        dbManager = DatabaseManager.getInstance();
    }

    @Override
    public void addReceipt(Receipt receipt) throws SQLException {
        try(Connection connection = dbManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(ADD_RECEIPT)){
            ps.setInt(1, receipt.getUserId());
            ps.setInt(2, receipt.getCarId());
            ps.setInt(3, receipt.getPrice());
            ps.setInt(4, receipt.getLength());
            ps.setString(5, receipt.getDeparture());
            ps.setString(6, receipt.getDeparture());
            ps.setInt(7, receipt.getTariffId());
            ps.execute();
        }
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

    private Receipt setReceipt(ResultSet rs) throws SQLException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getInt(1));
        receipt.setUserId(rs.getInt(2));
        receipt.setCarId(rs.getInt(3));
        receipt.setPrice(rs.getInt(4));
        receipt.setLength(rs.getInt(5));
        receipt.setDeparture(rs.getString(6));
        receipt.setDestination(rs.getString(7));
        receipt.setTariffId(rs.getInt(8));
        return receipt;
    }

    @Override
    public void deleteReceipt(int id) {

    }

    @Override
    public void updateReceipt(int id) {

    }
}