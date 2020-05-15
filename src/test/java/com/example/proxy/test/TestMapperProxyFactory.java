package com.example.proxy.test;

import com.example.proxy.jdk.MapperProxyFactory;
import com.example.proxy.sql.MySqlMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author James
 */
public class TestMapperProxyFactory {

    @Test
    public void testProxy() {
        MapperProxyFactory<MySqlMapper> mapperMapperProxyFactory = new MapperProxyFactory<>();
        MySqlMapper mySqlMapper = mapperMapperProxyFactory.newInstance(MySqlMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("startIndex", "1");
        map.put("pageSize", 1000);
        mySqlMapper.countTableSum(map);
    }
}
