package com.Serebriakov.model.util;

import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.exception.DBException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceiptContainer {
    private static List<Receipt> receipts;
    private static HashMap<String, List<Receipt>> requiredReceipts;
    static {
        requiredReceipts = new HashMap<>();
    }

    public static List<Receipt> getAllReceipts(){
        return new ArrayList<>(receipts);
    }

    public static void updateList() throws DBException {
        receipts = ReceiptDAOImpl.getInstance().getAllReceipts();
    }

    public static boolean containsRequiredReceipts(String sessionId){
        return requiredReceipts.containsKey(sessionId);
    }

    public static List<Receipt> createRequiredReceipts(String sessionId){
        requiredReceipts.remove(sessionId);
        requiredReceipts.put(sessionId, new ArrayList<>(receipts));
        return requiredReceipts.get(sessionId);
    }

    public static List<Receipt> getRequiredReceipts(String sessionId){
        if(!ReceiptContainer.containsRequiredReceipts(sessionId)){
            ReceiptContainer.createRequiredReceipts(sessionId);
        }
        return requiredReceipts.get(sessionId);
    }

    public static void setRequiredReceipts(String sessionId, List<Receipt> receipts){
        requiredReceipts.remove(sessionId);
        requiredReceipts.put(sessionId, receipts);
    }

    public static List<Receipt> findReceipts(int first, int last){
        if(last >= receipts.size()){
            last = receipts.size() - 1;
        }
        return receipts.subList(first, last);
    }

    public static int getSize(){
        return receipts.size();
    }
}
