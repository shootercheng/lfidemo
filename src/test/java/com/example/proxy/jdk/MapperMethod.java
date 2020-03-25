package com.example.proxy.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author James
 */
public class MapperMethod {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperMethod.class);

    private final Class<?> mapperInterface;
    private final Method method;

    public MapperMethod(Class<?> mapperInterface, Method method) {
        this.mapperInterface = mapperInterface;
        this.method = method;
    }

    public Object execute(Object[] args) {
        LOGGER.info("execute sql....");
        String statementId = mapperInterface.getName() + "." + method.getName();
        LOGGER.info("statement id {}", statementId);
        LOGGER.info("execute param {}", args);
        return args;
    }
}
