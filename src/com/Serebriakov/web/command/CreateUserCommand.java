package com.Serebriakov.web.command;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.entity.User;
import com.Serebriakov.entity.type.Role;
import com.Serebriakov.exception.DBException;
import com.Serebriakov.util.Password;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateUserCommand implements Command {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        password = Password.hashcode(password);
        UserDAO userDAO = UserDAOImpl.getInstance();
        User user = User.createUser(login, password, email, Role.USER);
        try{
            userDAO.addUser(user);
            user = userDAO.getUserByLogin(login);
        } catch (DBException e){
            request.getSession().setAttribute("errorMessage", "This user is already exist");
            return "error_page.jsp";
        }
        request.getSession().setAttribute("currentUser", user);
        logger.debug("User '" + user.getLogin() + "' has been created.");
        return "user_page.jsp";
    }
}
