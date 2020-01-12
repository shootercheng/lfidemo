package com.example.parse;

import com.example.model.vo.ParseParam;
import com.example.utils.ExcelUtil;
import com.example.utils.ParseFileCommonUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class ExcelFileParse implements FileParse {
    @Override
    public <T> List<T> parseFile(String filePath, Class<T> clazz, ParseParam parseParam) {
        Workbook workbook = ExcelUtil.getWorkBook(filePath);
        Sheet sheet = workbook.getSheetAt(parseParam.getSheetNum());
        int rows = sheet.getPhysicalNumberOfRows();
        List<T> resultList = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            if (i >= parseParam.getStartLine()) {
                Row row = sheet.getRow(i);
                T t = convertRowToVo(clazz, row, parseParam);
                resultList.add(t);
            }
        }
        return resultList;
    }

    @Override
    public <T> List<T> parseFileBatch(String filePath, Class<?> clazz, List<ParseParam> parseParams, int batchNum) {
        return null;
    }

    private  <T> T convertRowToVo(Class<T> clazz, Row row, ParseParam parseParam) {
        T t = null;
        try {
            t = clazz.newInstance();
            Map<Integer, Method> fieldSetterMap = parseParam.getFieldSetterMap();
            Set<Map.Entry<Integer, Method>> entrySet = fieldSetterMap.entrySet();
            int column;
            Method setterMethod;
            String cellValue;
            for (Map.Entry<Integer, Method> entry : entrySet) {
                column = entry.getKey();
                setterMethod = entry.getValue();
                row.getCell(column).setCellType(CellType.STRING);
                cellValue = row.getCell(column).getStringCellValue();
                ParseFileCommonUtil.invokeValue(t, setterMethod, cellValue);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
