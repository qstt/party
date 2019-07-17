package com.yanxue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.yanxue.dao.SpeakDao;
import com.yanxue.dao.StudentDao;
import com.yanxue.entity.Check;
import com.yanxue.entity.JsonResult;
import com.yanxue.entity.MyResult;
import com.yanxue.entity.Party;
import com.yanxue.entity.Speak;
import com.yanxue.entity.Student;
import com.yanxue.util.GUID;

@Service
public class SpeakServiceImpl extends BaseServiceImpl<Speak> {
    @Resource
    SpeakDao dao;
    
    @Override
    public MyResult add(Speak t) {
        
        try {
            //判断二维码是否失效
            String status = dao.getStatus(t.getLecture_id());
            if ("1".equals(status)) {
                return new MyResult(31);
            }
            
            t.setSpeak_id(GUID.get32UUID());
            String number = "SA" + t.getStudent_number().substring(2,t.getStudent_number().length());
            t.setStudent_number(number);
            
            //判断该学号是否存在
            Party party = dao.getParty(number);
            if (party == null) {
                return new MyResult(32);
            }
            
            //修改发言内容
            Speak stu = dao.checkStudent(t.getStudent_number(), t.getLecture_id());
            if (stu != null) {
                t.setSpeak_id(stu.getSpeak_id());
                int update = dao.update(t);
                if (update > 0) {
                    return new MyResult(2);
                }else {
                    return new MyResult(3);
                }
            }
            
          //判断一个IP是否提交多个信息
            Speak checkIp = dao.checkIp(t.getIp(), t.getLecture_id());
            if (checkIp != null) {
                int i = dao.updateIp(t);
                if (i > 0) {
                    return new MyResult(33);
                }
            }
            
            int i = dao.add(t);
            if (i > 0) {
                return new MyResult(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new MyResult(0);
    }
    
    
    
    
    

    public JsonResult getAll(String lectureId) {
        List<Student> list = dao.getStudents(lectureId);
        int total = dao.getStudentsCount(lectureId);
        JsonResult jr = new JsonResult();
        jr.setRows(list);
        jr.setTotal(total);
        return jr;
    }

    public JsonResult getAwardStudents(String lectureId) {
        List<Student> list = dao.getAwardStudents(lectureId);
        int total = dao.getAwardStudentsCount(lectureId);
        JsonResult jr = new JsonResult();
        jr.setRows(list);
        jr.setTotal(total);
        return jr;
    }

    public JsonResult getIntentionStudents(String lectureId) {
        List<Student> list = dao.getIntentionStudents(lectureId);
        int total = dao.getIntentionStudentsCount(lectureId);
        JsonResult jr = new JsonResult();
        jr.setRows(list);
        jr.setTotal(total);
        return jr;
    }

    

    public MyResult getSpeakData(String lecid, Date time) {
        List<Speak> list = dao.getSpeakData(lecid, time);
        dao.updateSpeakDataStatus(lecid, time);
        return new MyResult(list);
    }
    
    public MyResult getSpeakData_1(String lecid, Date time) {
        List<Speak> list = dao.getSpeakData_1(lecid, time);
        return new MyResult(list);
    }

    public MyResult addAward(String lecId, String stuId) {
        MyResult r = new MyResult();
        int f = dao.addAward(GUID.get32UUID(), lecId, stuId);
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
        if ("attend".equals(type)) {
            list = dao.getStudents(lectureId);
        } else if ("award".equals(type)) {
            list = dao.getAwardStudents(lectureId);
        } else if ("intention".equals(type)) {
            list = dao.getIntentionStudents(lectureId);
        }
        for (Student bean : list) {
            ja.add(bean);
        }
        return ja;
    }
}
