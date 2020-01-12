package com.example.parse;

import com.example.model.vo.ParseParam;

import java.util.List;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public interface FileParse {
    <T> List<T> parseFile(String filePath, Class<T> clazz, ParseParam parseParam);

    <T> List<T> parseFileBatch(String filePath, Class<?> clazz, List<ParseParam> parseParams, int batchNum);

    default BusinessDefineParse processDefineParse(ParseParam parseParam)  {
        if (parseParam.getBusinessDefineClazz() != null) {
            try {
                return parseParam.getBusinessDefineClazz().newInstance();
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
        return null;
    }
}
