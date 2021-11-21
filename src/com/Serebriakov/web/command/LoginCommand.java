package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.entity.User;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.exception.DBException;
import com.Serebriakov.util.Password;
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
                List<Receipt> receipts = ReceiptDAOImpl.getInstance().getAllReceipts();
                request.getSession().setAttribute("Receipts", receipts);
                request.getSession().setAttribute("RequiredReceipts", receipts);
            }
            return "user_page.jsp";
        }
        request.getSession().setAttribute("errorMessage", "Wrong login or password");
        return "error_page.jsp";
    }
}
