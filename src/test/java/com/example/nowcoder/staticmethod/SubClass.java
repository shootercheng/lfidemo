package com.example.nowcoder.staticmethod;

/**
 * @author James
 */
public class SubClass extends SuperClass {

    public void name() {
        System.out.println(super.getClass().getName());
    }

    public static void showInfo() {
        System.out.println("static sub method");
    }
}
