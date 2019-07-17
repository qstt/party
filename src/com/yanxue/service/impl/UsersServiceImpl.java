package com.yanxue.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yanxue.dao.UserDao;
import com.yanxue.entity.JsonResult;
import com.yanxue.entity.MyResult;
import com.yanxue.entity.Users;
import com.yanxue.util.MD5Utils;

@Service
public class UsersServiceImpl extends BaseServiceImpl<Users> {
    @Resource
    UserDao dao;

    public JsonResult getAll() {
        JsonResult jr = new JsonResult();
        List<Users> list = dao.getAll();
        int total = dao.count();
        jr.setTotal(total);
        jr.setRows(list);
        return jr;
    }

    public JsonResult getJsonDataByName(String name) {
        name = "%" + name + "%";
        int total = dao.getListByNameCount(name);
        List<Users> rows = dao.getListByName(name);
        JsonResult r = new JsonResult();
        r.setRows(rows);
        r.setTotal(total);
        return r;
    }

    public MyResult resetPwd(String username) {
        String pwd = MD5Utils.getMD5("123456");
        dao.resetPwd(username, pwd);
        return new MyResult(1);
    }

    public MyResult del(String username) {
        dao.del(username);
        return new MyResult(1);
    }

    public MyResult add(Users e) {
        MyResult r = new MyResult();
        String pwd = MD5Utils.getMD5("123456");
        Users e1 = dao.checkExists(e.getUsername());
        if (e1 != null) {
            r.setStatus(0);
            return r;
        }
        e.setPwd(pwd);
        int f = dao.add(e);
        if (f > 0) {
            r.setStatus(1);
        } else {
            r.setStatus(0);
        }
        return r;
    }

    public MyResult updateUsers(Users e, HttpServletRequest req) {
        MyResult r = new MyResult();
        int f = dao.update(e);
        Users user = (Users) req.getSession().getAttribute("user");
        user.setRealname(e.getRealname());
        user.setTel(e.getTel());
        user.setSex(e.getSex());
        req.getSession().setAttribute("user", user);
        if (f > 0) {
            r.setStatus(1);
        } else {
            r.setStatus(0);
        }
        return r;
    }

    public MyResult getAllUsername() {
        List<Users> list = dao.getAll();
        return new MyResult(list);
    }
}
