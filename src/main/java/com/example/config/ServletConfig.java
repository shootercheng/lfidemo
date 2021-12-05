package com.example.config;

import com.example.servlet.DefineDispatcherServlet;
import com.example.servlet.DispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author James
 */
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<DefineDispatcherServlet> createDefineDispatchServlet() {
        ServletRegistrationBean<DefineDispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<>();
        String disPatchUrl = "/app";
        servletRegistrationBean.setServlet(new DefineDispatcherServlet(disPatchUrl));
        servletRegistrationBean.addUrlMappings("/app/*");
        return servletRegistrationBean;
    }

//    @Bean
    public ServletRegistrationBean<DispatcherServlet> createDispatchServlet() {
        ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<>();
        String disPatchUrl = "/app";
        servletRegistrationBean.setServlet(new DispatcherServlet(disPatchUrl));
        servletRegistrationBean.addUrlMappings("/app/*");
        return servletRegistrationBean;
    }
}
