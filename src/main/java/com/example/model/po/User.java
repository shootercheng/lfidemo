package com.example.model.po;

import lombok.Data;

/**
 * @author chengdu
 * @date 2019/7/29.
 */

@Data
public class User {

    private Integer userId;

    private String userName;

    private String passWord;

    private String email;

    public User(Integer userId, String userName, String passWord, String email) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }
}
