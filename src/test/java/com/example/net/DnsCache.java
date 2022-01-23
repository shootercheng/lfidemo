package com.example.net;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;
import sun.net.InetAddressCachePolicy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.security.Security;

/**
 * @author James
 */
public class DnsCache {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testNeverCachePolicy() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Security.setProperty("networkaddress.cache.ttl", "0");
        int policy = InetAddressCachePolicy.get();
        Assert.assertEquals(InetAddressCachePolicy.NEVER, policy);
        String url = "https://www.baidu.com/";
        System.out.println(sendGetRequest(url));
        System.out.println(sendGetRequest(url));
    }

    public static String sendGetRequest(String urlParam) {
        URLConnection con;
        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;
        try {
            URL url = new URL(urlParam);
            con = url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            //得到响应流
            InputStream inputStream = con.getInputStream();
            //将响应流转换成字符串
            resultBuffer = new StringBuffer();
            String line;
            buffer = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            while ((line = buffer.readLine()) != null) {
                resultBuffer.append(line);
            }
            return resultBuffer.toString();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";
    }
}
