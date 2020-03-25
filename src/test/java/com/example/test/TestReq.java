package com.example.test;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author James
 */
public class TestReq {

    private static final ExecutorService fixedPool = Executors.newFixedThreadPool(20);

    @Test
    public void testReq() {
        long startTime = System.currentTimeMillis();
        String url = "http://localhost:9091/lfi/common/test/sync?" + "type=" + "1";
        RestTemplate restTemplate = new RestTemplate();
        List<Future<String>> futureList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            ReqTask reqTask = new ReqTask(url, restTemplate);
            Future<String> stringFuture = fixedPool.submit(reqTask);
            futureList.add(stringFuture);
        }
        for (Future<String> stringFuture : futureList) {
            try {
                System.out.println(stringFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("time : " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void testReqSync() {
        long startTime = System.currentTimeMillis();
        String url = "http://localhost:9091/lfi/common/test/sync?" + "type=" + "2";
        RestTemplate restTemplate = new RestTemplate();
        List<Future<String>> futureList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            ReqTask reqTask = new ReqTask(url, restTemplate);
            Future<String> stringFuture = fixedPool.submit(reqTask);
            futureList.add(stringFuture);
        }
        for (Future<String> stringFuture : futureList) {
            try {
                System.out.println(stringFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("time : " + (System.currentTimeMillis() - startTime));
    }
}
