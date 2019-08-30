package com.example.type;

/**
 * @author chengdu
 * @date 2019/8/31.
 */
public class IntegerTypeHandler implements BaseTypeHandler {
    @Override
    public Object convertStrToType(String input) {
        return Integer.valueOf(input);
    }
}
