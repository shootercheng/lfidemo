package com.example.utils;

import com.example.constant.CommonConstant;
import com.example.constant.FileType;
import com.example.exception.FileParseException;
import com.example.parse.FileParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class FileParseCommonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileParseCommonUtil.class);

    public static Map<Integer, Method> convertToColumnMethodMap(Class<?> clazz, Map<Integer, String>  fieldColumnMap) {
        Set<Map.Entry<Integer,String>> entrySet = fieldColumnMap.entrySet();
        Map<Integer, Method> columnFieldSetter = new HashMap<>(16);
        Integer column;
        String fieldName;
        Map<String, Method> allBeanSetter = ReflectUtil.getBeanSetterMap(clazz);
        for (Map.Entry<Integer, String> entry : entrySet) {
            column = entry.getKey();
            fieldName = entry.getValue().toLowerCase();
            columnFieldSetter.put(column, allBeanSetter.get(fieldName));
        }
        return columnFieldSetter;
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
        } catch (Exception e) {
            throw new FileParseException("invoke value error", e);
        }
    }

    public static FileType getFileType(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("input filepath is empty");
        }
        FileType fileType;
        if (filePath.endsWith(".csv")) {
            fileType = FileType.CSV;
        } else if (filePath.endsWith(".xls")|| filePath.endsWith(".xlsx")) {
            fileType = FileType.EXCEL;
        } else {
            throw new IllegalArgumentException("input filepath error " + filePath);
        }
        return fileType;
    }
}
