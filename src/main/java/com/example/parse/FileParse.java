package com.example.parse;

import com.example.model.vo.ParseParam;

import java.util.List;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public interface FileParse {
    <T> List<T> parseFile(String filePath, Class<T> clazz, ParseParam parseParam);

    default void checkParam(ParseParam parseParam) {
        if (parseParam.getFieldSetterMap() == null ||
                parseParam.getFieldSetterMap().size() == 0) {
            throw new IllegalArgumentException("please check field setter mapper");
        }
    }

    <T> List<T> parseFileBatch(String filePath, Class<?> clazz, List<ParseParam> parseParams, int batchNum);
}
