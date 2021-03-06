package com.Serebriakov.model.command;

import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.entity.User;
import com.Serebriakov.exception.DBException;
import com.Serebriakov.model.util.ReceiptContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateReceiptsListCommand implements Command {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        HttpSession session = request.getSession();
        String sort = request.getParameter("sort");
        String filter = request.getParameter("filter");
        List<Receipt> receipts = ReceiptContainer.createRequiredReceipts(session.getId());
        if(sort.equals("Time")){
            receipts = receipts.stream().sorted(Comparator.comparing(Receipt::getTime)).collect(Collectors.toList());
        }
        if(sort.equals("Price")){
            receipts = receipts.stream().sorted(Comparator.comparing(Receipt::getPrice)).collect(Collectors.toList());
        }
        if(filter.equals("Date")){
            String date = request.getParameter("date");
            if(!date.equals("")){
                receipts = receipts.stream().filter(receipt -> {
                    String[] strings = receipt.getTime().split(" ");
                    return strings[0].equals(date);
                }).collect(Collectors.toList());
            }
        }
        if(filter.equals("User")){
            String userName = request.getParameter("userName");
            User user = UserDAOImpl.getInstance().getUserByLogin(userName);
            if(user != null){
                int userId = user.getId();
                receipts = receipts.stream().filter(receipt -> receipt.getUserId() == userId).collect(Collectors.toList());
            } else {
                receipts.clear();
            }
        }
        ReceiptContainer.setRequiredReceipts(session.getId(), receipts);
        session.setAttribute("page", 0);
        session.setAttribute("direction", "next");
        return new PageCommand().execute(request, response);
    }
}
