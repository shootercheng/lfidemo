package com.example.constant;

import com.example.type.*;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class CommonConstant {

    public static final Map<String, BaseTypeHandler> HANDLER_MAP;
    static {
        Map<String, BaseTypeHandler> map = new HashMap<>();
        map.put(TypeEnum.String.type(), new StringTypeHandler());
        map.put(TypeEnum.Integer.type(), new IntegerTypeHandler());
        map.put(TypeEnum.Double.type(), new DoubleTypeHandler());
        map.put(TypeEnum.Long.type(), new LongTypeHandler());
        map.put(TypeEnum.Date.type(), new DateTypeHandler());
        map.put(TypeEnum.Boolean.type(), new BooleanTypeHandler());
        HANDLER_MAP = Collections.unmodifiableMap(map);
    }

    public static final String GBK = "GBK";

    public static final String UTF8 = "UTF-8";

    public static final String UTF16 = "UTF-16";

    public static final String UNICODE = "Unicode";


    public static final String INDENT = "    ";

    public static final String CRLF = System.lineSeparator();
}
