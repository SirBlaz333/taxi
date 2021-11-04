package com.Serebriakov.database.DAO.impl;

import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DatabaseManager;

import java.io.IOException;

public class ReceiptDAOImpl implements ReceiptDAO {

    private DatabaseManager dbManager;

    public ReceiptDAOImpl() throws IOException {
        dbManager = DatabaseManager.getInstance();
    }

    @Override
    public void addReceipt() {

    }

    @Override
    public void getReceiptById(int id) {

    }

    @Override
    public void getReceiptByUserId(int userId) {

    }

    @Override
    public void deleteReceipt(int id) {

    }

    @Override
    public void updateReceipt(int id) {

    }
}
