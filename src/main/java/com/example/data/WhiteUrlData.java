package com.example.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author chengdu
 * @date 2019/7/29.
 */
public class WhiteUrlData {

    // TODO 读取配置文件
    private static String[] whiteStr = {"index.html","login.html", "/user/login","/common/*"};

    private static final List<Pattern> whiteList;

    static {
        List<Pattern> list = new ArrayList<>(15);
        for(String white : whiteStr){
            Pattern pattern = Pattern.compile(white);
            list.add(pattern);
        }
        whiteList = Collections.unmodifiableList(list);
    }

    public static List<Pattern> getWhiteList(){
        return whiteList;
    }

    public static boolean isWhiteUrl(String url){
        boolean isWhite = false;
        for(Pattern pattern : whiteList){
            if(pattern.matcher(url).find()){
                isWhite = true;
                break;
            }
        }
        return isWhite;
    }
}
