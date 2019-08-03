package com.example.listener;

import com.example.data.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author chengdu
 * @date 2019/7/28.
 */

@WebListener
public class MyListener implements HttpSessionListener {

    private static int onlineNum = 0;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.info(" create session : " +se.getSession().getId());
        onlineNum++;
        se.getSession().getServletContext().setAttribute("onlineNum", onlineNum);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        String httpSessionId = httpSession.getId();
        LOGGER.info(" destory session : " +httpSessionId);
        TestData.getSessionList().remove(httpSessionId);
        onlineNum--;
        httpSession.getServletContext().setAttribute("onlineNum", onlineNum);
    }
}
