package com.Serebriakov.model.tag;

import com.Serebriakov.database.entity.User;
import com.Serebriakov.database.entity.type.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class SecurityTag extends SimpleTagSupport {
    private User user;
    private String redirection;

    public void setRedirect(String redirect) {
        this.redirection = redirect;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void doTag() throws IOException {
        if(user == null || !user.getRole().equals(Role.ADMIN)){
            PageContext pageContext = (PageContext) getJspContext();
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
            request.setAttribute("errorMessage", "You have not permission to access this page");
            try {
                request.getRequestDispatcher(redirection).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
