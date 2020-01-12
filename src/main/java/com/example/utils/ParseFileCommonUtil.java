package com.example.utils;

import com.example.constant.CommonConstant;
import com.example.model.vo.ParseParam;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class ParseFileCommonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParseFileCommonUtil.class);

    public static Map<Integer, Method> convertToOrderMethodMap(Class<?> clazz, Map<Integer, String>  fileOrderMap) {
        Set<Map.Entry<Integer,String>> entrySet = fileOrderMap.entrySet();
        Map<Integer, Method> orderFieldSetter = new HashMap<>();
        Integer order;
        String fieldName;
        Map<String, Method> allBeanSetter = ReflectUtil.getBeanSetterMap(clazz);
        for (Map.Entry<Integer, String> entry : entrySet) {
            order = entry.getKey();
            fieldName = entry.getValue().toLowerCase();
            orderFieldSetter.put(order, allBeanSetter.get(fieldName));
        }
        return orderFieldSetter;
    }

    public static <T> void invokeValue( T t, Method method, String value) {
        if (method.getParameterTypes().length != 1) {
            LOGGER.error("input method parameter type error");
            return;
        }
        if (!method.getName().startsWith("set")) {
            LOGGER.error("input method not setter method {}", method.getName());
            return;
        }
        String typeName = method.getParameterTypes()[0].getTypeName();
        if (!CommonConstant.HANDLER_MAP.containsKey(typeName)) {
            LOGGER.error("unknown type handler {}", typeName);
            return ;
        }
        Object typeValue = CommonConstant.HANDLER_MAP.get(typeName).convertStrToType(value);
        try {
            method.invoke(t, typeValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
