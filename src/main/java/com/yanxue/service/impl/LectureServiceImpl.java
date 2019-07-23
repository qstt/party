package com.yanxue.service.impl;

import com.yanxue.constants.LectureActivityEnum;
import com.yanxue.constants.LectureStatusEnum;
import com.yanxue.util.CollectionUtils;
import com.yanxue.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yanxue.dao.LectureDao;
import com.yanxue.entity.JsonResult;
import com.yanxue.entity.Lecture;
import com.yanxue.entity.MyResult;
import com.yanxue.util.GUID;

@Service
public class LectureServiceImpl extends BaseServiceImpl<Lecture> {

    private static final Logger logger = LoggerFactory.getLogger(LectureServiceImpl.class);

    @Resource
    LectureDao lectureDao;

    // 获取所有的未开讲的讲座
    public JsonResult getAllLecture(String username, String role, String page, String pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Lecture> list = new ArrayList<Lecture>();
        int total = 0;
        if ("1".equals(role)) {// 超级管理员
            map.put("page", (Integer.valueOf(page) - 1) * Integer.valueOf(pageSize));
            map.put("pageSize", Integer.parseInt(pageSize));
            list = lectureDao.findList(map);
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    if (String.valueOf(LectureActivityEnum.CHECK.getValue()).equals(list.get(i).getActivity())) {
                        list.get(i).setActivity("发言");
                    } else {
                        list.get(i).setActivity("签到");
                    }
                }
            }
            total = lectureDao.count(map);
        } else if ("2".equals(role)) {// 讲师
            map.put("professor", username);
            list = lectureDao.getNewLecture(map);
        }
        JsonResult jr = new JsonResult(total, list);
        return jr;
    }

    public JsonResult getOldLecture(String page, String pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (Integer.valueOf(page) - 1) * Integer.valueOf(pageSize));
        map.put("pageSize", Integer.parseInt(pageSize));
        List<Lecture> list = lectureDao.getOldLecture(map);
        if (CollectionUtils.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if (String.valueOf(LectureActivityEnum.CHECK.getValue()).equals(list.get(i).getActivity())) {
                    list.get(i).setActivity("发言");
                } else {
                    list.get(i).setActivity("签到");
                }
            }
        }
        int total = lectureDao.getOldLectureCount();
        JsonResult jr = new JsonResult(total, list);
        return jr;
    }

    public MyResult getLecture(String id) {
        return new MyResult(lectureDao.getLecture(id));
    }

    @Override
    public MyResult add(Lecture t) {
        t.setId(GUID.get32UUID());
        int i = lectureDao.add(t);
        if (i > 0) {
            return new MyResult(1);
        }
        logger.error("添加失败 lecture:{}", t);
        return MyResult.failMyResult("添加失败");
    }

    @Override
    public MyResult update(Lecture t) {
        int i = lectureDao.update(t);
        if (i > 0) {
            return new MyResult(1);
        }
        logger.error("更新失败 lecture:{}", t);
        return MyResult.failMyResult("更新失败");
    }

    @Override
    public MyResult del(String id) {
        int i = lectureDao.del(id);
        if (i > 0) {
            return new MyResult(1);
        }
        logger.error("删除失败 lecture id :{}", id);
        return MyResult.failMyResult("删除失败");
    }

    public MyResult start(String id) {
        Lecture activity = lectureDao.getActivity(id);
        if (StringUtils.isNotBlank(activity.getActivity())
                && String.valueOf(LectureStatusEnum.END).equals(activity.getStatus())) {
            return new MyResult(1);
        }
        int i = lectureDao.start(id);
        if (i > 0) {
            return new MyResult(1);
        }
        logger.error("活动开始失败 lecture id:{}", id);
        return MyResult.failMyResult("活动开始失败");
    }

    /**
     * @param id
     * @return
     */
    public Lecture getActivity(String id) {
        return lectureDao.getActivity(id);
    }

}
