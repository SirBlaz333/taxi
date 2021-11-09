package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.database.type.Roles;
import com.Serebriakov.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAOImpl();
        User user = User.createUser(login, password, email, Roles.USER);
        try{
            userDAO.addUser(user);
            user = userDAO.getUserByLogin(login);
        } catch (SQLException e){
            request.getSession().setAttribute("errorMessage", "This user is already exist");
            return "login_error_page.jsp";
        }
        request.getSession().setAttribute("currentUser", user);
        return "user_page.jsp";
    }
}
