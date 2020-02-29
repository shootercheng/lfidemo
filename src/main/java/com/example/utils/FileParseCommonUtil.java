package com.example.utils;

import com.example.constant.CommonConstant;
import com.example.constant.ParseName;
import com.example.exception.FileParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class FileParseCommonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileParseCommonUtil.class);

    public static Map<String, Method> convertToColumnMethodMap(Class<?> clazz, Map<String, String>  fieldColumnMap) {
        Set<Map.Entry<String, String>> entrySet = fieldColumnMap.entrySet();
        Map<String, Method> columnFieldSetter = new HashMap<>(16);
        Map<String, Method> allBeanSetter = ReflectUtil.getBeanSetterMap(clazz);
        for (Map.Entry<String, String> entry : entrySet) {
            String column = entry.getKey();
            String fieldName = entry.getValue().toLowerCase();
            Method setterMethod = allBeanSetter.get(fieldName);
            if (setterMethod == null) {
                throw new IllegalArgumentException("Bean " + clazz + " not contain field " + fieldName +
                        " please check config column map");
            }
            columnFieldSetter.put(column, allBeanSetter.get(fieldName));
        }
        return columnFieldSetter;
    }

    public static <T> void invokeValue( T t, Method method, String value) {
        if (StringUtils.isEmpty(value)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("input value is empty");
            }
            return;
        }
        if (method == null) {
            throw new IllegalArgumentException("input set method is null");
        }
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
        } catch (Exception e) {
            throw new FileParseException("invoke value error", e);
        }
    }

    public static ParseName getFileType(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("input filepath is empty");
        }
        ParseName parseName;
        if (filePath.endsWith(".csv")) {
            parseName = ParseName.CSV;
        } else if (filePath.endsWith(".xls")|| filePath.endsWith(".xlsx")) {
            parseName = ParseName.EXCEL;
        } else {
            throw new IllegalArgumentException("input filepath error " + filePath);
        }
        return parseName;
    }

    public static Map<String, Integer> EXCEL_COLUMN;

    static {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 0);
        map.put("B", 1);
        map.put("C", 2);
        map.put("D", 3);
        map.put("E", 4);
        map.put("F", 5);
        map.put("G", 6);
        map.put("H", 7);
        map.put("I", 8);
        map.put("J", 9);
        map.put("K", 10);
        map.put("L", 11);
        map.put("M", 12);
        map.put("N", 13);
        map.put("O", 14);
        map.put("P", 15);
        map.put("Q", 16);
        map.put("R", 17);
        map.put("S", 18);
        map.put("T", 19);
        map.put("U", 20);
        map.put("V", 21);
        map.put("W", 22);
        map.put("X", 23);
        map.put("Y", 24);
        map.put("Z", 25);
        map.put("AA", 26);
        map.put("AB", 27);
        EXCEL_COLUMN = Collections.unmodifiableMap(map);
    }
}
