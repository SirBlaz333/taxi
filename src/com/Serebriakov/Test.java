package com.Serebriakov;

import com.Serebriakov.database.DAO.UserDAO;
import com.Serebriakov.database.DAO.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/test")
public class Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Test#doGet");
        UserDAO userDAO = new UserDAOImpl();
        try {
            userDAO.getAllUsers().forEach(user -> System.out.println(user.getLogin() + " " + user.getRole()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
