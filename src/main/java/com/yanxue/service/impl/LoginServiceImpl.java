package com.yanxue.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yanxue.dao.UserDao;
import com.yanxue.entity.MyResult;
import com.yanxue.entity.Users;
import com.yanxue.service.LoginService;
import com.yanxue.util.MD5Utils;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Resource
    UserDao userDao;

    public MyResult login(String username, String pwd, HttpServletRequest req, String role) {
        System.out.println(pwd);
        MyResult m = new MyResult(1,"login success");
        Users u = userDao.check(username, MD5Utils.getMD5(pwd), role);
        if (u == null) {// 账号或密码错误
            m.setStatus(0);
            m.setMsg("账号或密码错误");
        } else {
            m.setStatus(1);
            req.getSession().setAttribute("user", u);
        }
        return m;
    }

    public MyResult getUserInfo(HttpServletRequest req) {
        MyResult m = new MyResult(1, "get info success");
        Users user = (Users) req.getSession().getAttribute("user");
        if (user == null) {
            m.setStatus(0);// 没有用户
            m.setMsg("用户未登录");
        } else {
            m.setStatus(1);
            m.setData(user);
        }
        return m;
    }

    public MyResult logout(HttpServletRequest req) {
        req.getSession().setAttribute("user", null);
        return new MyResult(1,"logout success");
    }

    public MyResult modify(String username, String pwd, int id, HttpServletRequest req) {
        MyResult m = new MyResult(1, "modify success");
        int f = userDao.changePwd(username, MD5Utils.getMD5(pwd));
        if (f == 0) {// 修改失败
            logger.info("change pwd request:{} username:{} pwd:{} user id:{}", req, username, pwd, id);
            m.setStatus(0);
            m.setMsg("密码修改失败");
        } else {
            Users user = userDao.get(id);
            req.getSession().setAttribute("user", user);
        }
        return m;
    }
}
