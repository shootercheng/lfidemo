package com.example.proxy.jdk;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James
 */
public class MapperProxyFactory<T> {
    private final Map<Method, MapperMethod> cacheMapperMethod = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public T newInstance(Class<T> clazz) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(clazz, cacheMapperMethod);
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, mapperProxy);
    }
}
