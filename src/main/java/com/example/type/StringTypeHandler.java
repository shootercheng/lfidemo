package com.example.type;

/**
 * @author chengdu
 * @date 2019/8/30.
 */
public class StringTypeHandler implements BaseTypeHandler {
    @Override
    public Object convertStrToType(String input) {
        return String.valueOf(input);
    }
}
