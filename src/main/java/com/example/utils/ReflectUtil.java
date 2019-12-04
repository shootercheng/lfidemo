package com.example.utils;

import com.example.model.vo.ReflectVo;
import org.apache.tomcat.util.modeler.BaseModelMBean;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengdu
 * @date 2019/8/30.
 */
public class ReflectUtil {

    private static final Field[] EMPTY_FIELD = {};

    private static final  Map<Class<?>, Field[]> declaredFieldsCache = new ConcurrentHashMap<>(2^10);

    private static final  Map<Class<?>, Method[]> declaredMethodsCache = new ConcurrentHashMap<>(2^10);

    private static final  Map<Class<?>, List<Method>> beanMethodCache = new ConcurrentHashMap<>(2^5);

    public static Field[] getClassField(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("input param is null");
        }
        Field[] fields = declaredFieldsCache.get(clazz);
        if (fields == null) {
            fields = clazz.getDeclaredFields();
            declaredFieldsCache.put(clazz, fields);
        }
        return fields;
    }

    /**
     * exclude Object class
     * @param clazz
     * @return
     */
    public static List<Field[]> getAllFields(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("input param is null");
        }
        List<Field[]> allFields = new ArrayList<>();
        while(clazz != Object.class) {
            Field[] fields = getClassField(clazz);
            if (fields != null && fields.length > 0) {
                allFields.add(fields);
            }
            clazz = clazz.getSuperclass();
        }
        return allFields;
    }

    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) ||
                !Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }

    public static Field findField(Class<?> clazz, String name, Class<?> type) {
        if (clazz == null || name == null || type == null) {
            return null;
        }
        List<Field[]> fieldList = getAllFields(clazz);
        for (Field[] fieldArr : fieldList) {
            for (Field field : fieldArr) {
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                if (name.equals(fieldName) && type.equals(fieldType)) {
                    return field;
                }
            }
        }
        return null;
    }

    public static Method[] getClassMethod(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("input param class is null");
        }
        Method[] declareMethods = declaredMethodsCache.get(clazz);
        if (declareMethods == null) {
            declareMethods = clazz.getDeclaredMethods();
            declaredMethodsCache.put(clazz, declareMethods);
        }
        return declareMethods;
    }

    /**
     * get all methods, but excluding
     * Object class
     * @param clazz
     * @return
     */
    public static List<Method[]> getAllMethods(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("input param class is null");
        }
        List<Method[]> allMethods = new ArrayList<>();
        while (clazz != Object.class) {
            Method[] methods = getClassMethod(clazz);
            if (methods != null && methods.length > 0) {
                allMethods.add(methods);
            }
            clazz = clazz.getSuperclass();
        }
        return allMethods;
    }

    public static Method findMethod(Class<?> clazz, String name, Class<?>[] paramTypes) {
        if (clazz == null || name == null || name.length() == 0) {
            throw new IllegalArgumentException("input param error");
        }
        List<Method[]> allMethods = getAllMethods(clazz);
        for (Method[] methodArr : allMethods) {
            for (Method method : methodArr) {
                String methodName = method.getName();
                Class<?>[] methodParamTypes = method.getParameterTypes();
                boolean nameEquals = name.equals(methodName);
                boolean isEqualTypes = isEqualTypes(paramTypes, methodParamTypes);
                if (nameEquals && isEqualTypes) {
                    return method;
                }
            }
        }
        return null;
    }

    private static boolean isEqualTypes(Class<?>[] paramTypes, Class<?>[] methodParamTypes) {
        int paramLen = paramTypes.length;
        int methodParamLen = methodParamTypes.length;
        if (paramLen != methodParamLen) {
            return false;
        }
        for(int i = 0; i < paramLen; i++) {
            if (!paramTypes[i].equals(methodParamTypes[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * model method, setXX
     * @param clazz
     * @return
     */
    public static List<Method> getBeanMethods(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("input param class is null");
        }
        List<Method> allBeanMethods = beanMethodCache.get(clazz);
        if (allBeanMethods == null) {
            allBeanMethods = new ArrayList<>();
            Class<?> tempClazz = clazz;
            while (tempClazz != Object.class) {
                Method[] methods = getClassMethod(tempClazz);
                if (methods != null && methods.length > 0) {
                    List<Method> beanMethods = findBeanMethod(methods);
                    allBeanMethods.addAll(beanMethods);
                }
                tempClazz = tempClazz.getSuperclass();
            }
            beanMethodCache.put(clazz, allBeanMethods);
        }
        return allBeanMethods;
    }

    /**
     * find setXX, isXX method
     * @param methods
     * @return
     */
    private static List<Method> findBeanMethod(Method[] methods) {
        List<Method> beanMethods = new ArrayList<>(methods.length);
        for (Method method : methods) {
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (methodName.startsWith("set") && parameterTypes.length == 1) {
                beanMethods.add(method);
            }
        }
        return beanMethods;
    }
}
