package com.Serebriakov.model.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("currentUser");
        request.getSession().removeAttribute("page");
        request.getSession().removeAttribute("maxPossiblePage");
        request.getSession().removeAttribute("requiredReceipts");
        return "login.jsp";
    }
}
