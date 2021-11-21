package com.Serebriakov.web.command;

import com.Serebriakov.entity.Receipt;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.exception.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Random;

public class ConfirmReceiptCommand implements Command {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        ReceiptDAO receiptDAO = ReceiptDAOImpl.getInstance();
        if(receipt!=null){
            receiptDAO.confirmReceipt(receipt.getId());
            logger.debug("Receipt '" + receipt.getId() + "' has been confirmed.");
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
