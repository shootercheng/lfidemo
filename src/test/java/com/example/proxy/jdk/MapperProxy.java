package com.example.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author James
 */
public class MapperProxy<T> implements InvocationHandler {
    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethod> cacheMapperMethod;

    public MapperProxy(Class<T> mapperInterface, Map<Method, MapperMethod> cacheMapperMethod) {
        this.mapperInterface = mapperInterface;
        this.cacheMapperMethod = cacheMapperMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperMethod mapperMethod = cacheMapperMethod.computeIfAbsent(method, m ->
                new MapperMethod(mapperInterface, method));
        return mapperMethod.execute(args);
    }
}
