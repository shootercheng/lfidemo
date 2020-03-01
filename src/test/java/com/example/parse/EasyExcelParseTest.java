package com.example.parse;

import com.example.constant.ParseType;
import com.example.model.vo.ExcelTypeVo;
import com.example.model.vo.ParseParam;
import com.example.parse.model.DemoData;
import com.example.utils.FileParseCommonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/3/1
 */
public class EasyExcelParseTest {

    @Test
    public void testParse() {
        String filePath = "file/demo.xlsx";
        ParseParam parseParam = createDemoParam();
        FileParse fileParse = FileParseCreateor.createFileParse(FileParseCommonUtil.findParserType(filePath, parseParam));
        List<DemoData> demoDataList = fileParse.parseFile(filePath, DemoData.class, parseParam);
        System.out.println(demoDataList);
    }

    public ParseParam createDemoParam() {
        Map<String, String> fieldColumnMap = new HashMap<>(16);
        fieldColumnMap.put("A", "string");
        fieldColumnMap.put("B", "date");
        fieldColumnMap.put("C", "doubleData");
        fieldColumnMap.put("D", "utDate");
        Map<String, Method> columnMethodMap = FileParseCommonUtil.convertToColumnMethodMap(DemoData.class, fieldColumnMap);
        ParseParam parseParam = new ParseParam().setStartLine(1)
                .setFieldSetterMap(columnMethodMap).setParserName(ParseType.EASYEXCEL);
        return parseParam;
    }
}
