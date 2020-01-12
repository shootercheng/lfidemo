package com.example.model.vo;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/1/12
 */
@Data
public class ParseParam {
    private int startLine;

    private int sheetNum;

    private Map<Integer, Method> fieldSetterMap;
}
