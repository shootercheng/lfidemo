package com.example.demo.vo;

/**
 * 1） 只要重写 equals ，就必须重写 hashCode 。
 * 2） 因为 Set 存储的是不重复的对象，依据 hashCode 和 equals 进行判断，所以 Set 存储的对象必须重写这两个方法。
 * 3） 如果自定义对象作为 Map 的键，那么必须重写 hashCode 和 equals 。
 * @author chengdu
 * @date 2019/8/3.
 */
public class TestVo extends Object {

    private Integer id;

    private String url;

    private String name;

    public TestVo(Integer id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }
}
