package com.example.demo.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author James
 */
public class TransmittableClazz {
    private static final ThreadLocal<String> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void setValue(String value) {
        THREAD_LOCAL.set(value);
    }

    public static String getValue() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
