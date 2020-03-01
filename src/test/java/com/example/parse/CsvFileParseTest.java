package com.example.parse;

import com.example.constant.CommonConstant;
import com.example.model.vo.ParseParam;
import com.example.model.vo.ReflectVo;
import com.example.parse.define.InfoFormat;
import com.example.parse.define.ReflectVoDefineParse;
import com.example.parse.model.UserInfo;
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
public class CsvFileParseTest extends ParseCommonTest {

    @Test
    public void testCsvParse() {
        String filePath = "file/test.csv";
        FileParse fileParse = FileParseCreateor.createFileParse(FileParseCommonUtil.findParserType(filePath));
        List<ReflectVo> reflectVoList = fileParse.parseFile(filePath, ReflectVo.class, createReflectParam());
        Assert.assertEquals(6, reflectVoList.size());
    }

    @Test
    public void testDefineParser() {
        String filePath = "file/test.csv";
        ParseParam parseParam = super.createReflectParam()
                .setBusinessDefineParse(new ReflectVoDefineParse())
                .setEncode(CommonConstant.GBK);
        FileParse fileParse = FileParseCreateor.createFileParse(FileParseCommonUtil.findParserType(filePath));
        List<ReflectVo> reflectVoList = fileParse.parseFile(filePath, ReflectVo.class, parseParam);
        Assert.assertEquals(6, reflectVoList.size());
    }

    @Test
    public void testDefineFormat() {
        String filePath = "file/test.csv";
        ParseParam parseParam = super.createReflectParam()
                .setBusinessDefineParse(new ReflectVoDefineParse())
                .setEncode(CommonConstant.GBK).setCellFormat(new InfoFormat());
        FileParse fileParse = FileParseCreateor.createFileParse(FileParseCommonUtil.findParserType(filePath));
        List<ReflectVo> reflectVoList = fileParse.parseFile(filePath, ReflectVo.class, parseParam);
        Assert.assertEquals(6, reflectVoList.size());
        Assert.assertEquals("chengdu", reflectVoList.get(0).getUserName());
    }

    @Test
    public void testErrorColumnMap() {
        try {
            Map<String, String> fieldColumnMap = new HashMap<>(16);
            fieldColumnMap.put("A", "id");
            fieldColumnMap.put("B", "gender");
            fieldColumnMap.put("C", "num");
            Map<String, Method> columnMethodMap = FileParseCommonUtil.convertToColumnMethodMap(UserInfo.class, fieldColumnMap);
            Assert.fail("---------bug------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuotation() {
        String filePath = "file/a.csv";
        ParseParam parseParam = createInfoParam().setEncode(CommonConstant.GBK);
        FileParse fileParse = FileParseCreateor.createFileParse(FileParseCommonUtil.findParserType(filePath));
        List<UserInfo> userInfoList = fileParse.parseFile(filePath, UserInfo.class, parseParam);
        System.out.println(userInfoList);
    }

    public ParseParam createInfoParam() {
        Map<String, String> fieldColumnMap = new HashMap<>(16);
        fieldColumnMap.put("A", "name");
        fieldColumnMap.put("B", "gender");
        fieldColumnMap.put("C", "num");
        Map<String, Method> columnMethodMap = FileParseCommonUtil.convertToColumnMethodMap(UserInfo.class, fieldColumnMap);
        ParseParam parseParam = new ParseParam().setStartLine(1)
                .setFieldSetterMap(columnMethodMap);
        return parseParam;

    }
}
