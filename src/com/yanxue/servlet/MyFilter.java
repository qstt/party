package com.yanxue.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class MyFilter implements Filter{
 
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
         
    }
     
    public void destroy() {
        // TODO Auto-generated method stub
         
    }
 
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        //获取HttpSession对象，判断是否登陆
        HttpServletRequest req =  (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
         
        if(session.getAttribute("user") == null){
            //非法访问，没有登陆，跳转到登陆页面
            session.setAttribute("error", "非法访问");
            // 保存客户想要去的地址, 登录成功后则直接跳转,而不是到首页
            String goURL = req.getServletPath();//(获取到地址不包括参数)
            //判断参数是否为空，不null就获取参数
            if(req.getQueryString()!=null){
                goURL+="?"+req.getQueryString();
            }
            session.setAttribute("goURL", goURL);
            res.sendRedirect(req.getContextPath() + "/login.html");
        }else{
            // 如果有下一个过滤器则跳转到下一个过滤器否则目标页面
            chain.doFilter(request, response);
        }
    }
 
 
}