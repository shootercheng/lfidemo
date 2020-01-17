package com.example.parse;

import com.example.exception.FileParseException;
import com.example.model.vo.ParseParam;
import com.example.parse.error.DefaultErrorRecord;
import com.example.parse.error.ErrorRecord;
import com.example.utils.ExcelUtil;
import com.example.utils.FileParseCommonUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class ExcelFileParse implements FileParse {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelFileParse.class);

    @Override
    public <T> List<T> parseFile(String filePath, Class<T> clazz, ParseParam parseParam) {
        Workbook workbook = null;
        List<T> resultList = new LinkedList<>();
        try {
            workbook = ExcelUtil.getWorkBook(filePath);
            Sheet sheet = workbook.getSheetAt(parseParam.getSheetNum());
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = parseParam.getStartLine(); i < rows; i++) {
                Row row = sheet.getRow(i);
                T t = convertRowToVo(clazz, row, parseParam);
                if (t != null) {
                    resultList.add(t);
                } else {
                    parseParam.getErrorRecord()
                    .writeErrorMsg("line " + i + ":" + row +
                     "covert to vo null");
                }
            }
        } catch (Exception e) {
            LOGGER.error("parse excel error {}", e.getMessage());
            throw new FileParseException("parse excel error " + filePath, e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
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

    private  <T> T convertRowToVo(Class<T> clazz, Row row, ParseParam parseParam) {
        T t = null;
        try {
            t = clazz.newInstance();
            Map<String, Method> fieldSetterMap = parseParam.getFieldSetterMap();
            for (Map.Entry<String, Method> entry : fieldSetterMap.entrySet()) {
                Integer column = FileParseCommonUtil.EXCEL_COLUMN.get(entry.getKey());
                row.getCell(column).setCellType(CellType.STRING);
                String cellValue = row.getCell(column).getStringCellValue();
                if (parseParam.getCellFormat() != null) {
                    cellValue = parseParam.getCellFormat().format(entry.getKey(), cellValue);
                }
                FileParseCommonUtil.invokeValue(t, entry.getValue(), cellValue);
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
