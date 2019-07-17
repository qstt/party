package com.yanxue.service;

import javax.servlet.http.HttpServletRequest;

import com.yanxue.entity.MyResult;


public interface LoginService {

	MyResult logout(HttpServletRequest req);

	MyResult getUserInfo(HttpServletRequest req);

	MyResult login(String username, String pwd, HttpServletRequest req,String role);

	MyResult modify(String username, String pwd, int id, HttpServletRequest req);

}
