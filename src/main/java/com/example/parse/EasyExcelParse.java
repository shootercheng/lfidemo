package com.example.parse;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.CellData;
import com.example.model.vo.ParseParam;
import com.example.parse.event.ModelParserListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author chengdu
 * @date 2020/2/29
 */
public class EasyExcelParse implements FileParse {
    private static final Logger LOGGER = LoggerFactory.getLogger(EasyExcelParse.class);
    @Override
    public <T> List<T> parseFile(String filePath, Class<T> clazz, ParseParam parseParam) {
        ModelParserListener modelParserListener = new ModelParserListener(parseParam);
        EasyExcel.read(filePath, modelParserListener)
                .sheet(parseParam.getSheetNum()).headRowNumber(parseParam.getStartLine()).doRead();
        List<Map<Integer, CellData>> resultList = modelParserListener.getResultList();
        // TODO convert to vo
        return null;
    }

    @Override
    public <T> List<T> parseFileBatch(String filePath, Class<?> clazz, List<ParseParam> parseParams, int batchNum) {
        return null;
    }
}
