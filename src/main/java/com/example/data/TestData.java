package com.example.data;

import com.example.model.po.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chengdu
 * @date 2019/7/29.
 */
public class TestData {

    private static List<User> userList = new ArrayList<>();

    private static Map<String, User> userMap;

    static {
        User user1 = new User(1, "a", "a", "a.@123.com");
        User user2 = new User(2, "b", "b", "b.@123.com");
        userList.add(user1);
        userList.add(user2);
        userMap = userList.stream().collect(Collectors.toMap(user -> user.getUserName(), user -> user));
    }

    public static List<User> getUserList(){
        return userList;
    }

    public static Map<String, User> getUserMap(){
        return userMap;
    }

    private static List<String> sessionList = Collections.synchronizedList(new ArrayList<>());

    public static List<String> getSessionList(){
        return sessionList;
    }
}
