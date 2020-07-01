package com.example.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author James
 */
public class DispatchFilter implements Filter {
    private Logger LOGGER = LoggerFactory.getLogger(DispatchFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("init dispatch filter config");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("dispatch filter do filter ......");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        LOGGER.info("request uri {}", uri);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.info("destroy dispatch filter");
    }
}
