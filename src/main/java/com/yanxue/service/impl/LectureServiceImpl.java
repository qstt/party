package com.yanxue.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yanxue.dao.LectureDao;
import com.yanxue.entity.JsonResult;
import com.yanxue.entity.Lecture;
import com.yanxue.entity.MyResult;
import com.yanxue.util.GUID;

@Service
public class LectureServiceImpl extends BaseServiceImpl<Lecture> {
    
    @Resource
    LectureDao dao;

    // 获取所有的未开讲的讲座
    public JsonResult getAllLecture(String username, String role, String page, String pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Lecture> list = new ArrayList<Lecture>();
        int total = 0;
        if ("1".equals(role)) {// 超级管理员
            map.put("page", (Integer.valueOf(page) - 1) * Integer.valueOf(pageSize));
            map.put("pageSize", Integer.parseInt(pageSize));
            list = dao.findList(map);
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if ("1".equals(list.get(i).getActivity())) {
                        list.get(i).setActivity("发言");
                    } else {
                        list.get(i).setActivity("签到");
                    }
                }
            }
            total = dao.count(map);
        } else if ("2".equals(role)) {// 讲师
            map.put("professor", username);
            list = dao.getNewLecture(map);
        }
        JsonResult jr = new JsonResult();
        jr.setRows(list);
        jr.setTotal(total);
        return jr;
    }

    public JsonResult getOldLecture(String page, String pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (Integer.valueOf(page) - 1) * Integer.valueOf(pageSize));
        map.put("pageSize", Integer.parseInt(pageSize));
        List<Lecture> list = dao.getOldLecture(map);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if ("1".equals(list.get(i).getActivity())) {
                    list.get(i).setActivity("发言");
                } else {
                    list.get(i).setActivity("签到");
                }
            }
        }
        int total = dao.getOldLectureCount();
        JsonResult jr = new JsonResult();
        jr.setRows(list);
        jr.setTotal(total);
        return jr;
    }

    public MyResult getLecture(String id) {
        return new MyResult(dao.getLecture(id));
    }

    @Override
    public MyResult add(Lecture t) {
        t.setId(GUID.get32UUID());
        int i = dao.add(t);
        if (i > 0) {
            return new MyResult(1);
        }
        return new MyResult(0);
    }

    @Override
    public MyResult update(Lecture t) {
        int i = dao.update(t);
        if (i > 0) {
            return new MyResult(1);
        }
        return new MyResult(0);
    }

    @Override
    public MyResult del(String id) {
        int i = dao.del(id);
        if (i > 0) {
            return new MyResult(1);
        }
        return new MyResult(0);
    }

    public MyResult start(String id) {
        Lecture activity = dao.getActivity(id);
        if (!StringUtils.isEmpty(activity) && "1".equals(activity.getStatus())) {
            return new MyResult(1);
        }
        int i = dao.start(id);
        if (i > 0) {
            return new MyResult(1);
        }
        return new MyResult(0);
    }

    /**
     * @param id
     * @return
     */
    public Lecture getActivity(String id) {
        return dao.getActivity(id);
    }

}
