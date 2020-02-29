package com.example.parse;

import com.example.model.vo.ExcelTypeVo;
import com.example.model.vo.ParseParam;
import com.example.model.vo.ReflectVo;
import com.example.utils.FileParseCommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class ExcelParseTest extends ParseCommonTest {

    @Test
    public void testExcel2003() {
        String filePath = "file/test2003.xls";
        FileParse fileParse = FileParseCreateor.createFileParse(FileParseCommonUtil.getFileType(filePath));
        List<ReflectVo> reflectVoList = fileParse.parseFile(filePath, ReflectVo.class, createReflectParam());
        Assert.assertEquals(6, reflectVoList.size());
    }

    @Test
    public void testExcel2007() {
        String filePath = "file/test2007.xlsx";
        FileParse fileParse = FileParseCreateor.createFileParse(FileParseCommonUtil.getFileType(filePath));
        List<ReflectVo> reflectVoList = fileParse.parseFile(filePath, ReflectVo.class, createReflectParam());
        Assert.assertEquals(6, reflectVoList.size());
    }

    @Test
    public void testExcelType() {
        String filePath = "test/test2007.xlsx";
        FileParse fileParse = FileParseCreateor.createFileParse(FileParseCommonUtil.getFileType(filePath));
        List<ExcelTypeVo> reflectVoList = fileParse.parseFile(filePath, ExcelTypeVo.class, createExcelTypeParam());
        Assert.assertEquals(6, reflectVoList.size());
    }

    public ParseParam createExcelTypeParam() {
        Map<String, String> fieldColumnMap = new HashMap<>(16);
        fieldColumnMap.put("A", "id");
        fieldColumnMap.put("B", "userName");
        fieldColumnMap.put("C", "score");
        fieldColumnMap.put("D", "date");
        fieldColumnMap.put("E", "numDate");
        fieldColumnMap.put("F", "bool");
        Map<String, Method> columnMethodMap = FileParseCommonUtil.convertToColumnMethodMap(ExcelTypeVo.class, fieldColumnMap);
        ParseParam parseParam = new ParseParam().setStartLine(1)
                .setFieldSetterMap(columnMethodMap);
        return parseParam;
    }

    @Test
    public void testColumn() {
        String name = "A";
        int column = -1;
        char[] charArr = name.toCharArray();
        for (char c : charArr) {
            column = (column + 1) * 26 + c - 'A';
        }
        System.out.print(column);
//        column + 1 / 26
    }

}
