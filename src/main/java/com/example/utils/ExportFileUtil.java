package com.example.utils;

import com.example.model.vo.ExportParam;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author chengdu
 * @date 2020/2/19
 */
public class ExportFileUtil {

    public static final String CSV_ROW_SEPARATOR = "\r";

    /**
     * export file txt, csv
     * @param filePath
     * @param dataList
     * @param charset
     * @param isAppend
     * @return
     */
    public static boolean exportFile(String filePath, List<String> dataList, Charset charset, boolean isAppend){
        boolean isSucess;
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(filePath, isAppend);
            osw = new OutputStreamWriter(out, charset);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append(CSV_ROW_SEPARATOR);
                }
            }
            isSucess=true;
        } catch (Exception e) {
            e.printStackTrace();
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSucess;
    }

    public static boolean exportFilePageDb(String filePath, Function<Map<String, Object>, List<String>> pageQueryFun, ExportParam exportParam){
        boolean isSucess;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter( new OutputStreamWriter(
                    new FileOutputStream(filePath, true), exportParam.getCharset()));
            bufferedWriter.append(exportParam.getHeaderData()).append(CSV_ROW_SEPARATOR);
            int sum = exportParam.getSum();
            int pageNum = exportParam.getPageNum();
            List<Integer> indexList = calIndexList(sum, pageNum);
            Map<String, Object> searchParam = exportParam.getSearchParam();
            searchParam.put("pageNum", pageNum);
            for ( Integer index : indexList) {
                searchParam.put("startIndex", index);
                List<String> queryList = pageQueryFun.apply(searchParam);
                if (!CollectionUtils.isEmpty(queryList)) {
                    for (String rowData : queryList) {
                        bufferedWriter.append(rowData).append(CSV_ROW_SEPARATOR);
                    }
                }
            }
            isSucess=true;
        } catch (Exception e) {
            e.printStackTrace();
            isSucess=false;
        }finally{
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSucess;
    }

    private static List<Integer> calIndexList(int sum, int pageNum) {
        List<Integer> list = new ArrayList<>();
        Integer startIndex = 0;
        if (sum <= pageNum) {
            list.add(startIndex);
            return list;
        }
        while (startIndex + pageNum < sum) {
            list.add(startIndex);
            startIndex = startIndex + pageNum;
        }
        // 最后一页
        list.add(startIndex);
        return list;
    }
}
