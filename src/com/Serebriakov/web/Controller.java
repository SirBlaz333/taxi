package com.Serebriakov.web;


import com.Serebriakov.exception.DBException;
import com.Serebriakov.web.command.container.CommandContainer;
import com.Serebriakov.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger(Thread.currentThread().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String address = "error_page.jsp";
        Command command = getCommand(req);
        try{
            address = command.execute(req, resp);
        } catch (DBException  e) {
            req.setAttribute("errorMessage", e.getMessage());
            logger.error(e.getMessage());
        }
        req.getRequestDispatcher(address).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String address = "error_page.jsp";
        Command command = getCommand(req);
        try{
            address = command.execute(req, resp);
        } catch (DBException  e) {
            req.setAttribute("errorMessage", e.getMessage());
            logger.error(e.getMessage());
        }
        resp.sendRedirect(address);
    }

    private Command getCommand(HttpServletRequest request){
        String commandName = request.getParameter("command");
        return CommandContainer.getCommand(commandName);
    }
}
