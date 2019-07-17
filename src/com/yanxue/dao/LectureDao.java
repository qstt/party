package com.yanxue.dao;

import java.util.List;
import java.util.Map;

import com.yanxue.entity.Lecture;

public interface LectureDao extends BaseDao<Lecture>{
	//获取新讲座的信息
	 List<Lecture> getNewLecture(Map<String, Object> map);
	 //获取某一个讲座信息
	 Lecture getLecture(String id);
	 
	 List<Lecture> getOldLecture(Map<String, Object> map);
	 
	 int getOldLectureCount();
	 
	 int start(String id);
    /**
     * @param t
     * @return
     */
    int addDesign(Lecture t);
    /**
     * @param id
     * @return
     */
    Lecture getActivity(String id);
}
