package com.example.controller;

import com.example.service.AsyncTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * @author chengdu
 * @date 2019/7/29.
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController {

    @Autowired
    private AsyncTestService asyncTestService;

    @Autowired
    @Qualifier(value = "threadPool")
    private ExecutorService threadPool;

    @RequestMapping(value = "/white/test", method = RequestMethod.GET)
    public String urlCommon(){
        return "whiteList test";
    }

    @RequestMapping(value = "/async/test", method = RequestMethod.POST)
    public String asyncTest1(){
        threadPool.execute(() -> asyncTestService.asyncTest());
        return "success";
    }

    @RequestMapping(value = "/async/testfuture", method = RequestMethod.POST)
    public String asyncFutureTest(){
//        CompletableFuture.runAsync(() -> asyncTestService.asyncTest());
        CompletableFuture.runAsync(() -> asyncTestService.asyncTest(), threadPool);
        return "success";
    }


}
