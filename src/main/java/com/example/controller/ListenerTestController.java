package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author chengdu
 * @date 2019/7/29.
 */
@RestController
public class ListenerTestController {

    @RequestMapping(value = "/session/test", method = RequestMethod.GET)
    public String sessionTest(HttpServletRequest httpServletRequest){
        // 创建session 会触发 Listener sessionCreated
        HttpSession httpSession = httpServletRequest.getSession();
        String sessionId = httpSession.getId();
        Object onlineNum = httpSession.getServletContext().getAttribute("onlineNum");
        return "sessionId : " + sessionId + ", onlineNum : " + onlineNum;
    }
}
