package com.yanxue.service.impl;

import com.yanxue.util.CollectionUtils;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.yanxue.dao.CheckDao;
import com.yanxue.entity.Check;
import com.yanxue.entity.JsonResult;
import com.yanxue.entity.MyResult;
import com.yanxue.entity.Party;
import com.yanxue.entity.Student;
import com.yanxue.util.GUID;

@Service
public class CheckServiceImpl extends BaseServiceImpl<Check> {
    @Resource
    CheckDao checkDao;
    
    @Override
    public MyResult add(Check t) {
        try {
            //判断二维码是否失效
            String status = checkDao.getStatus(t.getLecture_id());
            if ("1".equals(status)) {
                return new MyResult(31);
            }
            
            t.setCheck_id(GUID.get32UUID());
            String number = "SA" + t.getStudent_number().substring(2,t.getStudent_number().length());
            t.setStudent_number(number);
            
            //判断该学号是否存在
            Party party = checkDao.getParty(number);
            if (party == null) {
                return new MyResult(32);
            }
            
            //判断是否重复签到
            Check check = checkDao.check(t.getStudent_number(), t.getLecture_id());
            if (check != null) {
                return new MyResult(2);
            }
            
            //判断一个IP是否提交多个信息
            Check checkIp = checkDao.checkIp(t.getIp(), t.getLecture_id());
            if (checkIp != null) {
                int i = checkDao.update(t);
                if (i > 0) {
                    return new MyResult(33);
                }
            }
            
            int i = checkDao.add(t);
            if (i > 0) {
                return new MyResult(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return new MyResult(0);
    }
    

    public JsonResult getAll(String lectureId) {
        List<Student> list = checkDao.getStudents(lectureId);
        int total = checkDao.getStudentsCount(lectureId);
        JsonResult jr = new JsonResult(total, list);
        return jr;
    }

    public JsonResult getAwardStudents(String lectureId) {
        List<Student> list = checkDao.getAwardStudents(lectureId);
        int total = checkDao.getAwardStudentsCount(lectureId);
        JsonResult jr = new JsonResult(total, list);
        return jr;
    }

    public JsonResult getIntentionStudents(String lectureId) {
        List<Student> list = checkDao.getIntentionStudents(lectureId);
        int total = checkDao.getIntentionStudentsCount(lectureId);
        JsonResult jr = new JsonResult(total, list);
        return jr;
    }

    

    public MyResult getCheckData(String lecid, Date time) {
        List<Check> list = checkDao.getCheckData(lecid, time);
        checkDao.updateCheckDataStatus(lecid, time);
        return new MyResult(list);
    }
    
    public MyResult getCheckData_1(String lecid, Date time) {
        List<Check> list = checkDao.getCheckData_1(lecid, time);
        return new MyResult(list);
    }

    public MyResult addAward(String lecId, String stuId) {
        MyResult r = new MyResult();
        int f = checkDao.addAward(GUID.get32UUID(), lecId, stuId);
        if (f > 0) {
            r.setStatus(1);
        } else {
            r.setStatus(0);
        }
        return r;
    }

    public JSONArray loadDataJson(String lectureId, String type) {
        JSONArray ja = new JSONArray();
        List<Student> list = null;
        switch (type) {
            case "attend":
                list = checkDao.getStudents(lectureId);
                break;
            case "award":
                list = checkDao.getAwardStudents(lectureId);
                break;
            case "intention":
                list = checkDao.getIntentionStudents(lectureId);
                break;
            default:
        }
        if (CollectionUtils.isNotEmpty(list)) {
            ja.addAll(list);
        }
        return ja;
    }
}
