package com.Serebriakov.model.command;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.database.entity.Receipt;
import com.Serebriakov.database.entity.User;
import com.Serebriakov.exception.DBException;
import com.Serebriakov.model.util.Password;
import com.Serebriakov.model.util.ReceiptContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        UserDAO userDAO = UserDAOImpl.getInstance();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password = Password.hashcode(password);

        User user = userDAO.getUserByLogin(login);

        if(user != null && user.getPassword().equals(password)){
            request.getSession().setAttribute("currentUser", user);
            logger.debug("User '" + user.getLogin() + "' has been logged in.");
            if(user.getRoleString().equals("admin")){
                ReceiptContainer.updateList();
                List<Receipt> receipts = ReceiptContainer.getList();
                request.getSession().setAttribute("RequiredReceipts", receipts);
            }
            return "index.jsp";
        }
        request.getSession().setAttribute("errorMessage", "Wrong login or password");
        return "error_page.jsp";
    }
}
