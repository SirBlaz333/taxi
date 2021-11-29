package com.Serebriakov.model.command;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.database.entity.User;
import com.Serebriakov.database.entity.type.Role;
import com.Serebriakov.exception.DBException;
import com.Serebriakov.model.util.Password;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserCommand implements Command {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        password = Password.hashcode(password);
        UserDAO userDAO = UserDAOImpl.getInstance();
        User user = User.createUser(login, password, email, Role.USER);
        user.setDiscount(0);
        user.setName(name);
        try{
            userDAO.addUser(user);
            user = userDAO.getUserByLogin(login);
        } catch (DBException e){
            request.getSession().setAttribute("errorMessage", "This user is already exist");
            return "error_page.jsp";
        }
        request.getSession().setAttribute("currentUser", user);
        logger.debug("User '" + user.getLogin() + "' has been created.");
        return "index.jsp";
    }
}
