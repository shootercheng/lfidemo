package com.example.nowcoder.staticmethod;

/**
 * @author James
 */
public class StaticMethod {

    public static void main(String[] args) {
        SuperClass superClass = new SubClass();
        superClass.name();
        superClass.showInfo();
    }
}
