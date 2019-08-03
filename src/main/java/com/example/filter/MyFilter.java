package com.example.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author chengdu
 * @date 2019/7/28.
 */
@WebFilter(filterName = "myFilter", urlPatterns = {"/*"})
public class MyFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("init filter ...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String id = httpServletRequest.getParameter("id");
        LOGGER.info(" filter req id is {}", id);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("destory filter ..");
    }
}
