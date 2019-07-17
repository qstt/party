package com.yanxue.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yanxue.entity.Users;


public class MyInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String sessionId = request.getSession().getId();
        System.out.println(sessionId);
        //判断用户的session是否正常
        //获取session
        if (sessionId == null || sessionId.equals("")) {
            System.out.println("sessionId失效");
            return false;
        }
        Users user = (Users)request.getSession().getAttribute("user");
        if(user == null){
        	request.getRequestDispatcher("login.html").forward(request, response);  
        	return false;
        }
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
