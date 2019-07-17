package com.yanxue.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.yanxue.dao.StudentDao;
import com.yanxue.entity.Check;
import com.yanxue.entity.JsonResult;
import com.yanxue.entity.Lecture;
import com.yanxue.entity.MyResult;
import com.yanxue.entity.Party;
import com.yanxue.entity.Result;
import com.yanxue.entity.Speak;
import com.yanxue.entity.Student;
import com.yanxue.util.GUID;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> {
    @Resource
    StudentDao dao;

    public JsonResult getAll(String lectureId) {
        
        JsonResult jr = new JsonResult();
        List<Check> checkList = new ArrayList<Check>();
        List<Speak> speakList = new ArrayList<Speak>();
        List<Result> resultList = new ArrayList<Result>();
        
        List<Party> partyList = dao.getParty();
        Lecture lecture = dao.getStatus(lectureId);
        if (!StringUtils.isEmpty(lecture) && partyList != null) {
            if ("0".equals(lecture.getActivity())) {
                checkList = dao.getCheck(lectureId);
            } else if ("1".equals(lecture.getActivity())) {
                speakList = dao.getSpeak(lectureId);
            }
            
            if (checkList != null && !checkList.isEmpty()) {
                for (int i = 0; i < partyList.size(); i++) {
                    Result result = new Result();
                    String number = partyList.get(i).getStudent_number();
                    result.setNumber(number);
                    result.setName(partyList.get(i).getName());
                    result.setActivity(lecture.getName());
                    for (int j = 0; j < checkList.size(); j++) {
                        if (number.equals(checkList.get(j).getStudent_number())) {
                            result.setCheck("已签到");
                            break;
                        } else {
                            result.setCheck("***未签到***");
                        }
                    }
                    resultList.add(result);
                }
                
            } else if (speakList != null && !speakList.isEmpty()) {
                for (int i = 0; i < partyList.size(); i++) {
                    Result result = new Result();
                    String number = partyList.get(i).getStudent_number();
                    result.setNumber(number);
                    result.setName(partyList.get(i).getName());
                    result.setActivity(lecture.getName());
                    for (int j = 0; j < speakList.size(); j++) {
                        if (number.equals(speakList.get(j).getStudent_number())) {
                            result.setSpeak("已发言");
                            break;
                        } else {
                            result.setSpeak("***未发言***");
                        }
                    }
                    resultList.add(result);
                }
            }
            
            jr.setRows(resultList);
            jr.setTotal(29);
        }
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

    @Override
    public MyResult add(Student t) {
        t.setId(GUID.get32UUID());
        Student stu = dao.checkStudent(t.getPhone(), t.getLecture_id());
        if (stu != null) {
            return new MyResult(2);
        }
        int i = dao.add(t);
        if (i > 0) {
            return new MyResult(1);
        }
        return new MyResult(0);
    }

    public MyResult getNewData(String lecid, Date time) {
        List<Student> list = dao.getNewStudents(lecid, time);
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
        List<Check> checkList = new ArrayList<Check>();
        List<Speak> speakList = new ArrayList<Speak>();
        List<Result> resultList = new ArrayList<Result>();
        
        List<Party> partyList = dao.getParty();
        Lecture lecture = dao.getStatus(lectureId);
        if (!StringUtils.isEmpty(lecture) && partyList != null) {
            if ("0".equals(lecture.getActivity())) {
                checkList = dao.getCheck(lectureId);
            } else if ("1".equals(lecture.getActivity())) {
                speakList = dao.getSpeak(lectureId);
            }
            
            if (checkList != null && !checkList.isEmpty()) {
                for (int i = 0; i < partyList.size(); i++) {
                    Result result = new Result();
                    String number = partyList.get(i).getStudent_number();
                    result.setNumber(number);
                    result.setName(partyList.get(i).getName());
                    result.setActivity(lecture.getName());
                    for (int j = 0; j < checkList.size(); j++) {
                        if (number.equals(checkList.get(j).getStudent_number())) {
                            result.setCheck("已签到");
                            break;
                        } else {
                            result.setCheck("***未签到***");
                        }
                    }
                    resultList.add(result);
                }
                
            } else if (speakList != null && !speakList.isEmpty()) {
                for (int i = 0; i < partyList.size(); i++) {
                    Result result = new Result();
                    String number = partyList.get(i).getStudent_number();
                    result.setNumber(number);
                    result.setName(partyList.get(i).getName());
                    result.setActivity(lecture.getName());
                    for (int j = 0; j < speakList.size(); j++) {
                        if (number.equals(speakList.get(j).getStudent_number())) {
                            result.setSpeak("已发言");
                            result.setSpeakContent(speakList.get(j).getSpeak());
                            break;
                        } else {
                            result.setSpeak("***未发言***");
                        }
                    }
                    resultList.add(result);
                }
            }
            
            for (Result result : resultList) {
                ja.add(result);
            }
            
        }
        return ja;
    }

    /**
     * @param lecId
     * @return
     */
    public String getActivity(String lecId) {
        return dao.getActivity(lecId);
    }
}
