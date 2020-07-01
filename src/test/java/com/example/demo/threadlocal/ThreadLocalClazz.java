package com.example.demo.threadlocal;

/**
 * @author James
 */
public class ThreadLocalClazz {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

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
