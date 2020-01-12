package com.example.parse;

import com.example.model.vo.ParseParam;
import com.example.parse.error.DefaultErrorRecord;
import com.example.parse.error.ErrorRecord;
import com.example.utils.FileParseCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class CsvFileParse implements FileParse {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileParse.class);

    private BusinessDefineParse businessDefineParse;

    @Override
    public <T> List<T> parseFile(String filePath, Class<T> clazz, ParseParam parseParam) {
        // 加载文件
        BufferedReader reader = null;
        List<T> resultList = new LinkedList<>();
        ErrorRecord errorRecord = new DefaultErrorRecord(new StringBuilder(""));
        try {
            reader = new BufferedReader(new FileReader(filePath));
            businessDefineParse = processDefineParse(parseParam);
            int readLine = 0;
            String lineStr;
            while ( (lineStr = reader.readLine()) != null) {
                if (readLine >= parseParam.getStartLine()) {
                    String[] lineArr = lineStr.split(",");
                    T t = convertArrToVo(clazz, lineArr, parseParam);
                    if (t != null) {
                        resultList.add(t);
                    } else {
                        // TODO error record
                        errorRecord.writeErrorMsg("");
                    }
                }
                readLine++;
            }
        } catch (Exception e) {
            errorRecord.writeErrorMsg(e.getMessage());
            LOGGER.error("parse csv file error {}", e.getMessage());
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
        return resultList;
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
                    FileParseCommonUtil.invokeValue(t, fieldSetterMap.get(i), inputArr[i]);
                }
            }
            if (businessDefineParse != null) {
                businessDefineParse.defineParse(t, inputArr, parseParam);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

}
