package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.entity.Receipt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ConfirmReceiptCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        ReceiptDAO receiptDAO = new ReceiptDAOImpl();
        if(receipt!=null){
            receiptDAO.confirmReceipt(receipt.getId());
            request.getSession().removeAttribute("currentReceipt");
            return "user_page.jsp";
        }
        request.getSession().setAttribute("errorMessage", "There is such receipt. Try make it again");
        return "error_page.jsp";
    }
}
