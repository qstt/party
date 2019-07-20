package com.yanxue.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanxue.entity.JsonResult;
import com.yanxue.entity.Lecture;
import com.yanxue.entity.MyResult;
import com.yanxue.service.impl.LectureServiceImpl;

/**
 * 讲座
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/lecture")
public class LectureController {
    @Resource
    LectureServiceImpl service;

    @RequestMapping("/getAll.do")
    @ResponseBody
    public JsonResult getAll(String username, String role, String page, String pageSize) {
        return service.getAllLecture(username, role, page, pageSize);
    }

    @RequestMapping("/getAllOld.do")
    @ResponseBody
    public JsonResult getOldLecture(String page, String pageSize) {
        return service.getOldLecture(page, pageSize);
    }

    @RequestMapping("/getOne.do")
    @ResponseBody
    public MyResult getOne(String id) {
        return service.getLecture(id);
    }

    @RequestMapping("/add.do")
    @ResponseBody
    public MyResult addDesign(Lecture design) {
        return service.add(design);
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public MyResult updateDesign(Lecture design) {
        return service.update(design);
    }

    @RequestMapping("/del.do")
    @ResponseBody
    public MyResult delDesign(String id) {
        return service.del(id);
    }

    @RequestMapping("/start.do")
    @ResponseBody
    public MyResult start(String id) {
        return service.start(id);
    }
    
    @RequestMapping("/getActivity.do")
    @ResponseBody
    public Lecture getActivity(String id) {
        return service.getActivity(id);
    }
}
