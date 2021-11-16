package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.database.type.Role;
import com.Serebriakov.entity.User;
import com.Serebriakov.web.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = UserDAOImpl.getInstance();
        User user = User.createUser(login, password, email, Role.USER);
        try{
            userDAO.addUser(user);
            user = userDAO.getUserByLogin(login);
        } catch (SQLException e){
            request.getSession().setAttribute("errorMessage", "This user is already exist");
            return "error_page.jsp";
        }
        request.getSession().setAttribute("currentUser", user);
        return "user_page.jsp";
    }
}
