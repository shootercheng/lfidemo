package com.example.utilstest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/2/19
 */
public class TestService {

    public int insertList(List<Integer> list) {
        return list.size();
    }

    public List<String> queryDbPage(Map<String, Object> requestParam) {
        System.out.print("startIndex " + requestParam.get("startIndex"));
        System.out.println(" pageNum " + requestParam.get("pageNum"));
        String rowData = "chengdu,man,name";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(String.valueOf(requestParam.get("pageNum"))); i++) {
            int num = Integer.valueOf(String.valueOf(requestParam.get("startIndex"))) + i;
            list.add(num + " " + rowData);
        }
        return list;
    }

    @Test
    public void testMap() {
        Map<Integer, String> map = new HashMap<>(16);
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.remove(1);
        System.out.println(map);
    }
}
