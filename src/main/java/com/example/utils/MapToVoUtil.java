package com.example.utils;

import com.example.type.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * parse excel, jsonObj
 * @author chengdu
 * @date 2019/8/31.
 */
public class  MapToVoUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtil.class);

    private static final Map<String, BaseTypeHandler> HANDLER_MAP;
    static {
        Map<String, BaseTypeHandler> map = new HashMap<>();
        map.put(TypeEnum.String.type(), new StringTypeHandler());
        map.put(TypeEnum.Integer.type(), new IntegerTypeHandler());
        map.put(TypeEnum.Double.type(), new DoubleTypeHandler());
        map.put(TypeEnum.Long.type(), new LongTypeHandler());
        map.put(TypeEnum.Date.type(), new DateTypeHandler());
        HANDLER_MAP = Collections.unmodifiableMap(map);
    }

    /**
     * 根据Vo setXX 反射
     * 键 用小写
     * @param clazz
     * @param map
     * @param <T>
     * @return
     */
    public static <T> T reflectByStrMap(Class<T> clazz, Map<String, String> map) {
        T object = null;
        try {
            object = clazz.newInstance();
            List<Method> methods = ReflectUtil.getBeanMethods(clazz);
            for (Method method : methods) {
                reflectMethod(method, map, object);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }

    private static <T> void reflectMethod(Method method, Map<String, String> map, T t) {
        try {
            String methodName = method.getName();
            Class<?>[] clazzs = method.getParameterTypes();
            if (clazzs.length > 0 && methodName.startsWith("set")) {
                String typeName = clazzs[0].getTypeName();
                if (!HANDLER_MAP.containsKey(typeName)){
                    LOGGER.error("unknown type handler {}", typeName);
                    return ;
                }
                String fieldName = method.getName().substring("set".length()).toLowerCase();
                String paramValue = map.get(fieldName);
                if (!StringUtils.isEmpty(paramValue)) {
                    BaseTypeHandler baseTypeHandler = HANDLER_MAP.get(typeName);
                    Object value = baseTypeHandler.convertStrToType(paramValue);
                    method.invoke(t, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static <T> T reflectByMap(Class<T> clazz, Map<String, Object> map) {
        T object = null;
        try {
            object = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (!methodName.startsWith("set")) {
                    continue;
                }
                String fieldName = method.getName().substring("set".length()).toLowerCase();
                if (map.containsKey(fieldName)) {
                    Parameter parameter = method.getParameters()[0];
                    Object value = parameter.getType().cast(map.get(fieldName));
                    method.invoke(object, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
