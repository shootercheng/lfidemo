package com.example.nowcoder.staticmethod;

/**
 * @author James
 */
public class SuperClass {

    public void name() {
        System.out.println(super.getClass().getName());
    }

    public static void showInfo() {
        System.out.println("static super method");
    }
}
