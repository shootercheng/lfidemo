package com.example.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author James
 */
public class DefineDispatcherServlet extends HttpServlet {
    private String disPath;

    public DefineDispatcherServlet(String disPath) {
        this.disPath = disPath;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String contextPath = httpServletRequest.getContextPath();
        String uri = httpServletRequest.getRequestURI();
        String dipathUrl = uri.replace(contextPath + disPath, "");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(dipathUrl);
        requestDispatcher.forward(req, res);
    }
}
