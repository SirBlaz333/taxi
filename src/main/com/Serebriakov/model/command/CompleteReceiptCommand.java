package com.Serebriakov.model.command;

import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.entity.state.Receipt_state;
import com.Serebriakov.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteReceiptCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        Receipt receipt = (Receipt) request.getSession().getAttribute("receiptInfo");
        receipt.setState(Receipt_state.DONE);
        ReceiptDAOImpl.getInstance().updateReceiptState(receipt.getId(), Receipt_state.DONE);
        return "receipt_info.jsp";
    }
}
