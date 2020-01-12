package com.example.parse;

import com.example.model.vo.ParseParam;
import com.example.parse.error.DefaultErrorRecord;
import com.example.parse.error.ErrorRecord;
import com.example.utils.ExcelUtil;
import com.example.utils.FileParseCommonUtil;
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
        ErrorRecord errorRecord = new DefaultErrorRecord(new StringBuilder(""));
        List<T> resultList = new LinkedList<>();
        for (int i = parseParam.getStartLine(); i < rows; i++) {
            Row row = sheet.getRow(i);
            T t = convertRowToVo(clazz, row, parseParam);
            if (t != null) {
                resultList.add(t);
            } else {
                errorRecord.writeErrorMsg("");
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
                FileParseCommonUtil.invokeValue(t, setterMethod, cellValue);
            }
            if (parseParam.getBusinessDefineParse() != null) {
                parseParam.getBusinessDefineParse().defineParse(t, row, parseParam);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
