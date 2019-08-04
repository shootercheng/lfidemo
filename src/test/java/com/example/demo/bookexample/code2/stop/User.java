package com.example.demo.bookexample.code2.stop;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public class User {
    private int id;
    private String name;

    public User(){
        id = 0;
        name = "0";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
