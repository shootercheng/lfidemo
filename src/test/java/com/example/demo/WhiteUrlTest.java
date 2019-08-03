package com.example.demo;

import com.example.data.WhiteUrlData;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author chengdu
 * @date 2019/7/29.
 */
public class WhiteUrlTest {

    @Test
    public void testWhite(){
        String url = "/common/uuu";
        Assert.assertTrue(WhiteUrlData.isWhiteUrl(url));
        Assert.assertTrue(WhiteUrlData.isWhiteUrl("/login.html"));
    }
}
