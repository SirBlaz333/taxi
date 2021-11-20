package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.ReceiptDAOImpl;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.entity.Receipt;
import com.Serebriakov.entity.User;
import com.Serebriakov.entity.type.Role;
import com.Serebriakov.web.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        UserDAO userDAO = UserDAOImpl.getInstance();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userDAO.getUserByLogin(login);

        if(user != null && user.getPassword().equals(password)){
            request.getSession().setAttribute("currentUser", user);
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
