package com.example.interceptor;

import com.example.data.TestData;
import com.example.data.WhiteUrlData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author chengdu
 * @date 2019/7/29.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("[Interceptor] {}", "LoginInterceptor");
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        // 待匹配url
        String matchUrl = uri.substring(contextPath.length());
        if(WhiteUrlData.isWhiteUrl(matchUrl)){
            return true;
        }
        boolean isLogin = checkIsLogin(request);
        if(!isLogin){
            String url = request.getRequestURL().toString();
            String reqPrefix = url.substring(0, url.length() - (uri.length() - contextPath.length()));
            response.sendRedirect(reqPrefix + "/login.html");
            return false;
        }
        return true;
    }

    private boolean checkIsLogin(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        List<String> sessionList = TestData.getSessionList();
        if(sessionList.contains(httpSession.getId())){
            return true;
        }
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        LOGGER.info("[Interceptor] {}, postHandle", "LoginInterceptor");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        LOGGER.info("[Interceptor] {}, afterCompletion", "LoginInterceptor");
    }
}
