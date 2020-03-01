package com.example.parse.event;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.context.AnalysisContextImpl;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ModelBuildEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.holder.ReadHolder;
import com.alibaba.excel.util.ConverterUtils;
import com.example.model.vo.ParseParam;
import com.example.utils.FileParseCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/2/29
 */
public class ModelParserListener<T> implements ReadListener<Map<Integer, CellData>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelBuildEventListener.class);

    private ParseParam parseParam;

    private List<T> resultList;

    private Class<T> clazz;

    public ModelParserListener(ParseParam parseParam, List<T> resultList, Class<T> clazz) {
        this.parseParam = parseParam;
        this.resultList = resultList;
        this.clazz = clazz;
    }


    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {
    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {

    }

    @Override
    public void invoke(Map<Integer, CellData> cellDataMap, AnalysisContext analysisContext) {
        ReadHolder currentReadHolder = analysisContext.currentReadHolder();
        T t = null;
        try {
            t = (T) clazz.newInstance();
            Map<String, Method> fieldSetterMap = parseParam.getFieldSetterMap();
            for (Map.Entry<String, Method> entry : fieldSetterMap.entrySet()) {
                String columnChar = entry.getKey();
                Integer column = FileParseCommonUtil.EXCEL_COLUMN.get(columnChar);
                CellData cellData = cellDataMap.get(column);
                if (cellData == null) {
                    LOGGER.error("column char parse no data {}", columnChar);
                    continue;
                }
                String cellValue = (String) ConverterUtils.convertToJavaObject(cellData, null, null,
                        currentReadHolder.converterMap(),
                        currentReadHolder.globalConfiguration(), analysisContext.readRowHolder().getRowIndex(), column);
                if (parseParam.getCellFormat() != null) {
                    cellValue = parseParam.getCellFormat().format(columnChar, cellValue);
                }
                FileParseCommonUtil.invokeValue(t, entry.getValue(), cellValue);
            }
            if (parseParam.getBusinessDefineParse() != null) {
                parseParam.getBusinessDefineParse().defineParse(t, cellDataMap, parseParam);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (t != null) {
            resultList.add(t);
        } else {
            if (currentReadHolder instanceof AnalysisContextImpl) {
                AnalysisContextImpl contextImpl = (AnalysisContextImpl) currentReadHolder;
                parseParam.getErrorRecord()
                        .writeErrorMsg("line " + contextImpl.getCurrentRowNum() + ":" + cellDataMap +
                                "covert to null");
            }
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return true;
    }

    public List<T> getResultList() {
        return resultList;
    }
}
