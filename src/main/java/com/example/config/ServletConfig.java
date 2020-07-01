package com.example.config;

import com.example.servlet.DispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author James
 */
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<DispatcherServlet> createDispatchServlet() {
        ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<>();
        String disPatchUrl = "/test";
        servletRegistrationBean.setServlet(new DispatcherServlet(disPatchUrl));
        servletRegistrationBean.addUrlMappings("/test/*");
        return servletRegistrationBean;
    }
}
