package com.example.config;

import com.example.filter.DispatchFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * @author James
 */
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<DispatchFilter> createDispatchFilter() {
        FilterRegistrationBean<DispatchFilter> registerBean = new FilterRegistrationBean<>();
        registerBean.setFilter(new DispatchFilter());
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST,
                DispatcherType.FORWARD);
        registerBean.setDispatcherTypes(dispatcherTypes);
        registerBean.addUrlPatterns("/*");
        return registerBean;
    }
}
