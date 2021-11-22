package com.Serebriakov.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/FilterContent")
public class FilterContent implements Filter {
    private static Logger logger = LogManager.getLogger("Filter");
    private FilterConfig filterConfig;
    private static List<String> pages;

    public FilterContent(){
        if(pages == null){
            pages = new ArrayList<>();
        }
    }

    @Override
    public void init(FilterConfig filterConfig){
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getRequestURI();
        String page;
        if(header.equalsIgnoreCase("/")){
            page = "index.jsp";
        } else {
            page = header.substring(1);
        }
        if(page.equalsIgnoreCase("index.jsp")){
            if(pages.contains("controller")){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                ServletContext context = filterConfig.getServletContext();
                RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
                dispatcher.forward(servletRequest, servletResponse);
                return;
            }
        }
        if(!pages.contains(page)){
            pages.add(page);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
