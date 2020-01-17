package com.example.model.vo;

import com.example.parse.BusinessDefineParse;
import com.example.parse.error.DefaultErrorRecord;
import com.example.parse.error.ErrorRecord;
import com.example.parse.format.CellFormat;
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

    private Map<String, Method> fieldSetterMap;

    private BusinessDefineParse businessDefineParse;

    private String encode;

    private ErrorRecord errorRecord;

    private CellFormat cellFormat;

    public ParseParam() {
        errorRecord = new DefaultErrorRecord(new StringBuilder(""));
    }

    public ParseParam setStartLine(int startLine) {
        this.startLine = startLine;
        return this;
    }

    public ParseParam setSheetNum(int sheetNum) {
        this.sheetNum = sheetNum;
        return this;
    }

    public ParseParam setFieldSetterMap(Map<String, Method> fieldSetterMap) {
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

    public ParseParam setErrorRecord(ErrorRecord errorRecord) {
        this.errorRecord = errorRecord;
        return this;
    }

    public ParseParam setCellFormat(CellFormat cellFormat) {
        this.cellFormat = cellFormat;
        return this;
    }
}
