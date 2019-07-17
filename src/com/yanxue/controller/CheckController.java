package com.yanxue.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.yanxue.entity.Check;
import com.yanxue.entity.JsonResult;
import com.yanxue.entity.MyResult;
import com.yanxue.entity.Student;
import com.yanxue.service.impl.CheckServiceImpl;
import com.yanxue.service.impl.StudentServiceImpl;
import com.yanxue.util.ExcelUtils;
import com.yanxue.util.IpUtils;

@Controller
@RequestMapping("/check")
public class CheckController {
    @Resource
    CheckServiceImpl service;

    @RequestMapping("/add.do")
    @ResponseBody
    public MyResult addStudent(Check t,HttpServletRequest request) {
        String ipAddr = IpUtils.getIpAddr(request);
        t.setIp(ipAddr);
        return service.add(t);
    }
    
    @RequestMapping("/getCheckData.do")
    @ResponseBody
    public MyResult getCheckData(String lecid, Date time) {
        return service.getCheckData(lecid, time);
    }
    
    @RequestMapping("/getCheckData_1.do")
    @ResponseBody
    public MyResult getCheckData_1(String lecid, Date time) {
        return service.getCheckData_1(lecid, time);
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    

    

    @RequestMapping("/getAll.do")
    @ResponseBody
    public JsonResult getAll(String lecId) {
        return service.getAll(lecId);
    }

    @RequestMapping("/addAward.do")
    @ResponseBody
    public MyResult addAward(String lecId, String stuId) {
        return service.addAward(lecId, stuId);
    }

    @RequestMapping("/getAllAward.do")
    @ResponseBody
    public JsonResult getAllAward(String lecId) {
        return service.getAwardStudents(lecId);
    }

    @RequestMapping("/getAllIntention.do")
    @ResponseBody
    public JsonResult getAllIntention(String lecId) {
        return service.getIntentionStudents(lecId);
    }

    @RequestMapping("/download.do")
    public void downloadCityEcxel(String lecId, String type, HttpServletRequest request, HttpServletResponse response) {
        try {
            JSONArray ja = service.loadDataJson(lecId, type);
            response.reset();
            Map<String, String> headMap = new LinkedHashMap<String, String>();
            headMap.put("name", "姓名");
            headMap.put("phone", "手机");
            headMap.put("major", "专业");
            headMap.put("academy", "宿舍");
            headMap.put("qq", "QQ");
            headMap.put("lession", "意向课程");
            String title = "";
            if ("attend".equals(type)) {
                title = "参加讲座学生";
            } else if ("award".equals(type)) {
                title = "讲座中奖学生";
            } else if ("intention".equals(type)) {
                title = "意向报名学生";
            }
            ExcelUtils.downloadExcelFile(title, headMap, ja, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
