package com.Serebriakov.model.command;

import com.Serebriakov.database.DAO.impl.UserDAOImpl;
import com.Serebriakov.database.entity.User;
import com.Serebriakov.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(!name.equalsIgnoreCase("")){
            user.setName(name);
        }
        if(!email.equalsIgnoreCase("")){
            user.setEmail(email);
        }
        if(!password.equalsIgnoreCase("")){
            user.setPassword(password);
        }
        UserDAOImpl.getInstance().updateUser(user);
        return "index.jsp";
    }
}
