package com.Serebriakov.model.command;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.entity.User;
import com.Serebriakov.exception.DBException;
import com.Serebriakov.model.util.ReceiptContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Random;

public class ConfirmReceiptCommand implements Command {
    private static Logger logger = LogManager.getLogger("Info");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        User user = (User) request.getSession().getAttribute("currentUser");
        ReceiptDAO receiptDAO = ReceiptDAOImpl.getInstance();
        UserDAO userDAO = UserDAOImpl.getInstance();
        if(receipt!=null){
            receiptDAO.confirmReceipt(receipt.getId());
            user.setSpentMoney(user.getSpentMoney()+receipt.getPrice());
            userDAO.updateUser(user);
            user.setDiscount(userDAO.getDiscount(user.getSpentMoney()));
            ReceiptContainer.updateList();
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
