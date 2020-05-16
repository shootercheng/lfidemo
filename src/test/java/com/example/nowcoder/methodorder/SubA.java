package com.example.nowcoder.methodorder;

/**
 * @author James
 */
public class SubA extends A {
    private B b = new B();

    public SubA()  {
        System.out.println("construct class SubA");
    }

    public static void main(String[] args) {
        // 构造一个类会先加载 父类， 先加载类字段属性
        A a = new SubA();
    }
}
