package com.example.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chengdu
 * @date 2019/7/29.
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorConfig.class);

    @Autowired
    private MyInterceptor myInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        LOGGER.info("init interceptors ");
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }
}
