package com.Serebriakov.database.DAO;

import com.Serebriakov.database.entity.Car;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.exception.DBException;

import java.util.List;

public interface ReceiptDAO {

    int addReceipt(Receipt receipt, Car... cars) throws DBException;
    Receipt getReceiptById(int id) throws DBException;
    List<Receipt> getUserReceipts(int userId) throws DBException;
    int getStateId(String state) throws DBException;
    void deleteReceipt(int id) throws DBException;
    void confirmReceipt(int id) throws DBException;
    List<Receipt> getAllReceipts() throws DBException;
}
