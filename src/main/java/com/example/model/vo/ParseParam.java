package com.example.model.vo;

import com.example.parse.BusinessDefineParse;
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

    private BusinessDefineParse businessDefineParse;

    private String encode;

    public ParseParam setStartLine(int startLine) {
        this.startLine = startLine;
        return this;
    }

    public ParseParam setSheetNum(int sheetNum) {
        this.sheetNum = sheetNum;
        return this;
    }

    public ParseParam setFieldSetterMap(Map<Integer, Method> fieldSetterMap) {
        this.fieldSetterMap = fieldSetterMap;
        return this;
    }

    public ParseParam setBusinessDefineParse(BusinessDefineParse businessDefineParse) {
        this.businessDefineParse = businessDefineParse;
        return this;
    }

    public ParseParam setEncode(String encode) {
        this.encode = encode;
        return this;
    }
}
