package com.example.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author chengdu
 * @date 2019/9/19.
 */
public class ProxyUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyUtil.class);

    public static Object jdkProxyObject(Object object, String name) {
        LoggerInterceptor interceptor = new LoggerInterceptor();
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    LOGGER.info("param is {}", Arrays.asList(args));
                    interceptor.before(name);
                    Object result = method.invoke(object, args);
                    interceptor.after(name);
                    return result;
                });
    }

    public static Object cglibProxyObject(Object object, String name) {
        LoggerInterceptor interceptor = new LoggerInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            interceptor.before(name);
            Object result = method.invoke(object, objects);
            interceptor.after(name);
            return result;
        });
        return enhancer.create();
    }

    public static <T> T cglibProxy(Class<T> clazz, String name){
        LoggerInterceptor interceptor = new LoggerInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            interceptor.before(name);
            Object result = method.invoke(clazz.newInstance(), objects);
            interceptor.after(name);
            return result;
        });
        return (T) enhancer.create();
    }
}
