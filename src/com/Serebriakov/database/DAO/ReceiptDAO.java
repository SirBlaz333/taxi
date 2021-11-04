package com.Serebriakov.database.DAO;

public interface ReceiptDAO {

    void addReceipt();
    void getReceiptById(int id);
    void getReceiptByUserId(int userId);
    void deleteReceipt(int id);
    void updateReceipt(int id);

}
