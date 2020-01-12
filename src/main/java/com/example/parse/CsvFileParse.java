package com.example.parse;

import com.example.model.vo.ParseParam;
import com.example.utils.ParseFileCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class CsvFileParse implements FileParse {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileParse.class);

    @Override
    public <T> List<T> parseFile(String filePath, Class<T> clazz, ParseParam parseParam) {
        // 加载文件
        BufferedReader reader = null;
        List<T> resultList = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            int readLine = 0;
            String lineStr;
            while ( (lineStr = reader.readLine()) != null) {
                if (readLine >= parseParam.getStartLine()) {
                    String[] lineArr = lineStr.split(",");
                    T t = convertArrToVo(clazz, lineArr, parseParam);
                    resultList.add(t);
                }
                readLine++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public <T> List<T> parseFileBatch(String filePath, Class<?> clazz, List<ParseParam> parseParams, int batchNum) {
        return null;
    }

    private <T> T convertArrToVo(Class<T> clazz, String[] inputArr, ParseParam parseParam) {
        T t = null;
        try {
            t = clazz.newInstance();
            Map<Integer, Method> fieldSetterMap = parseParam.getFieldSetterMap();
            for (int i = 0; i < inputArr.length; i++) {
                if (fieldSetterMap.containsKey(i)) {
                    ParseFileCommonUtil.invokeValue(t, fieldSetterMap.get(i), inputArr[i]);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

}
