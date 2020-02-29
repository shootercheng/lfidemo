package com.example.parse.event;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ModelBuildEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.example.model.vo.ExportParam;
import com.example.model.vo.ParseParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/2/29
 */
public class ModelParserListener implements ReadListener<Map<Integer, CellData>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelBuildEventListener.class);

    private List<Map<Integer, CellData>> resultList = new ArrayList<>();

    private ParseParam parseParam;

    public ModelParserListener(ParseParam parseParam) {
        this.parseParam = parseParam;
    }


    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {
    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {

    }

    @Override
    public void invoke(Map<Integer, CellData> integerCellDataMap, AnalysisContext analysisContext) {
        resultList.add(integerCellDataMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return true;
    }

    public List<Map<Integer, CellData>> getResultList() {
        return resultList;
    }
}
