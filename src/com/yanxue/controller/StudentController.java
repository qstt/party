package com.yanxue.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.yanxue.entity.JsonResult;
import com.yanxue.entity.MyResult;
import com.yanxue.entity.Student;
import com.yanxue.service.impl.StudentServiceImpl;
import com.yanxue.util.ExcelUtils;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    StudentServiceImpl service;
    
    @RequestMapping("/getAll.do")
    @ResponseBody
    public JsonResult getAll(String lecId) {
        return service.getAll(lecId);
    }
    
    @RequestMapping("/download.do")
    public void downloadCityEcxel(String lecId, String type, HttpServletRequest request, HttpServletResponse response) {
        try {
            JSONArray ja = service.loadDataJson(lecId, type);
            response.reset();
            Map<String, String> headMap = new LinkedHashMap<String, String>();
            headMap.put("activity", "活动");
            headMap.put("number", "学号");
            headMap.put("name", "姓名");
            headMap.put("check", "签到情况");
            headMap.put("speak", "发言情况");
            headMap.put("speakContent", "发言内容");
            String title = "";
            if ("attend".equals(type)) {
                String activity = service.getActivity(lecId);
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String dateString = formatter.format(date);
                if ("0".equals(activity)) {
                    title = "签到情况" + dateString;
                } else if ("1".equals(activity)) {
                    title = "发言情况" + dateString;
                } else {
                    title = "参加党会情况" + dateString;
                }
                
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
    
    @RequestMapping("/getNewData.do")
    @ResponseBody
    public MyResult getNewData(String lecid, Date time) {
        return service.getNewData(lecid, time);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    

    @RequestMapping("/add.do")
    @ResponseBody
    public MyResult addStudent(Student t) {
        return service.add(t);
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

   
}
