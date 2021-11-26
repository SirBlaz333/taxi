package com.Serebriakov.model.command;

import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.DAO.ReceiptDAO;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.exception.DBException;
import com.Serebriakov.model.util.ReceiptContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteReceiptCommand implements Command {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        Receipt receipt = (Receipt) request.getSession().getAttribute("currentReceipt");
        ReceiptDAO receiptDAO = ReceiptDAOImpl.getInstance();
        if(receipt!=null){
            receiptDAO.deleteReceipt(receipt.getId());
            ReceiptContainer.updateList();
            request.getSession().removeAttribute("currentReceipt");
            request.getSession().removeAttribute("NoSuchCarError");
            request.getSession().removeAttribute("amountOfCars");
            return "index.jsp";
        }
        request.getSession().setAttribute("errorMessage", "There is such receipt. Try make it again");
        return "error_page.jsp";
    }
}
