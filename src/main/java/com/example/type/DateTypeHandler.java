package com.example.type;

import com.example.utils.DateUtil;

/**
 * @author chengdu
 * @date 2019/8/30.
 */
public class DateTypeHandler implements BaseTypeHandler {
    @Override
    public Object convertStrToType(String input) {
        return DateUtil.parseStrToDate(input);
    }
}
