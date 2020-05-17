package com.example.nowcoder.serializable;

import java.io.Serializable;

/**
 * transient 修饰的字段不会被序列化
 * @author James
 */
public class SerialData implements Serializable {

    private final String desc = "test data";

    private Long id;

    private String userName;

    private transient String password;

    public SerialData() {
    }

    public SerialData(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public String getDesc() {
        return desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
