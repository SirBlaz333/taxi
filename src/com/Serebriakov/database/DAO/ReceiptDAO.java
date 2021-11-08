package com.Serebriakov.database.DAO;

import com.Serebriakov.entity.Receipt;

import java.sql.SQLException;
import java.util.List;

public interface ReceiptDAO {

    int addReceipt(Receipt receipt) throws SQLException;
    Receipt getReceiptById(int id) throws SQLException;
    List<Receipt> getUserReceipts(int userId) throws SQLException;
    int getStateId(String state) throws SQLException;
    void deleteReceipt(int id) throws SQLException;
    void confirmReceipt(int id) throws SQLException;

}
