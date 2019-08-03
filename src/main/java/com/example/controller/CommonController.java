package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengdu
 * @date 2019/7/29.
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController {

    @RequestMapping(value = "/white/test", method = RequestMethod.GET)
    public String urlCommon(){
        return "whiteList test";
    }
}
