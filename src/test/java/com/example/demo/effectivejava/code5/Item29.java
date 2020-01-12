package com.example.demo.effectivejava.code5;

import org.junit.Test;

/**
 * Favor generic types
 * @author chengdu
 * @date 2019/12/22
 */
public class Item29 {

    class Box {
        private String name;
        private Object object;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }

    class BoxGeneric<T> {
        private String name;
        private T object;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public T getObject() {
            return object;
        }

        public void setObject(T object) {
            this.object = object;
        }
    }

    @Test
    public void testObj() {
        Box box = new Box();
        box.setName("object");
        box.setObject("hello world");
        String obj = (String) box.getObject();
        System.out.println(obj);
    }

    @Test
    public void testGenericType() {
        BoxGeneric<String> boxGeneric = new BoxGeneric<>();
        boxGeneric.setName("object");
        boxGeneric.setObject("hello world");
        String obj = boxGeneric.getObject();
        System.out.println(obj);
    }
}
