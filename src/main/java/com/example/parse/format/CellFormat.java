package com.example.parse.format;

/**
 * @author chengdu
 * @date 2020/1/17
 */
public interface CellFormat {
    <T> String format(String column, String cellValue);
}
