package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.CarDAO;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.CarDAOImpl;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.web.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Random;

public class ConfirmReceiptCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        ReceiptDAO receiptDAO = ReceiptDAOImpl.getInstance();
        if(receipt!=null){
            receiptDAO.confirmReceipt(receipt.getId());
            request.getSession().removeAttribute("currentReceipt");
            request.getSession().removeAttribute("NoSuchCarError");
            request.getSession().removeAttribute("amountOfCars");
            Random random = new SecureRandom();
            int time = random.nextInt(10)+5;
            request.getSession().setAttribute("ExpectedWaitingTime", time);
            return "expected_waiting_time.jsp";
        }
        request.getSession().setAttribute("errorMessage", "This receipt is unavailable. Make new one");
        return "error_page.jsp";
    }
}
