package com.example.parse;

import com.example.model.vo.ParseParam;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public interface BusinessDefineParse {
    <T> void defineParse(T t, Object rowData, ParseParam parseParam);
}
