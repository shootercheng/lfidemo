package com.example.model.vo;

import lombok.Data;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/2/19
 */
@Data
public class ExportParam {
    String headerData;

    private Charset charset;

    private int sum;

    private int pageNum;

    private Map<String, Object> searchParam;
}
