package com.example.controller;

import com.example.data.TestData;
import com.example.model.po.User;
import com.example.model.vo.UserVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author chengdu
 * @date 2019/7/29.
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String userLogin(UserVo userVo, HttpServletRequest request, HttpServletResponse response){
        String userName = userVo.getUserName();
        String passWord = userVo.getPassWord();
        if (!TestData.getUserMap().containsKey(userName)){
            return "userName or passWord error";
        }
        User user = TestData.getUserMap().get(userName);
        if (!user.getPassWord().equals(passWord)){
            return "userName or passWord error";
        }
        HttpSession httpSession = request.getSession();
        TestData.getSessionList().add(httpSession.getId());
        return "login success";
    }
}
