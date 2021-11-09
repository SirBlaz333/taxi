package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUserByLogin(login);

        if(user != null && user.getPassword().equals(password)){
            request.getSession().setAttribute("currentUser", user);
            return "user_page.jsp";
        }

        request.getSession().setAttribute("errorMessage", "Wrong login or password");
        return "login_error_page.jsp";
    }
}
