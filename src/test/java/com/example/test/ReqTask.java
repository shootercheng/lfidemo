package com.example.test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

/**
 * @author James
 */
public class ReqTask implements Callable<String> {
    private String url;

    private final RestTemplate restTemplate;

    public ReqTask(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public String call() throws Exception {
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        return result.getBody();
    }
}
