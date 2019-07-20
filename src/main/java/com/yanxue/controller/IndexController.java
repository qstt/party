package com.yanxue.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanxue.entity.MyResult;
import com.yanxue.service.LoginService;

/**
 * 登陆相关处理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Resource
    LoginService service;

    @RequestMapping("/login.do")
    @ResponseBody
    public MyResult login(String username, String pwd, String role, HttpServletRequest req) {
        return service.login(username, pwd, req, role);
    }

    @RequestMapping("/getUserInfo.do")
    @ResponseBody
    public MyResult getUserInfo(HttpServletRequest req) {
        return service.getUserInfo(req);
    }

    @RequestMapping("/logout.do")
    @ResponseBody
    public MyResult logout(HttpServletRequest req) {
        return service.logout(req);
    }

    @RequestMapping("/modify.do")
    @ResponseBody
    public MyResult modify(String username, String pwd, int id, HttpServletRequest req) {
        return service.modify(username, pwd, id, req);
    }
}
