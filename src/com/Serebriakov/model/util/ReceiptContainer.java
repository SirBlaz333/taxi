package com.Serebriakov.model.util;

import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.exception.DBException;

import java.util.List;

public class ReceiptContainer {
    private static List<Receipt> receipts;
    public static List<Receipt> getList(){
        return receipts;
    }
    public static void updateList() throws DBException {
        receipts = ReceiptDAOImpl.getInstance().getAllReceipts();
    }
}
