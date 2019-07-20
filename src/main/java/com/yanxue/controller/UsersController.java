package com.yanxue.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanxue.entity.JsonResult;
import com.yanxue.entity.MyResult;
import com.yanxue.entity.Users;
import com.yanxue.service.impl.UsersServiceImpl;

/**
 * 登陆相关处理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    @Resource
    UsersServiceImpl service;

    @RequestMapping("/getAll.do")
    @ResponseBody
    public JsonResult getAll() {
        return service.getAll();
    }

    @RequestMapping("/getAllUsername.do")
    @ResponseBody
    public MyResult getAllUsername() {
        return service.getAllUsername();
    }

    @RequestMapping("/getByName.do")
    @ResponseBody
    public JsonResult getByName(String name) {// 根据姓名查询行政人员
        return service.getJsonDataByName(name);
    }

    @RequestMapping("/resetPwd.do")
    @ResponseBody
    public MyResult resetPwd(String username) {// 重置行政人员密码
        return service.resetPwd(username);
    }

    @RequestMapping("/del.do")
    @ResponseBody
    public MyResult del(String username) {// 行政人员标记离职
        return service.del(username);
    }

    @RequestMapping("/add.do")
    @ResponseBody
    public MyResult add(Users e) {// 添加行政人员
        return service.add(e);
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public MyResult update(Users e, HttpServletRequest req) {// 更新行政人员资料
        return service.updateUsers(e, req);
    }
}
