package com.yanxue.dao;

import java.util.Date;
import java.util.List;

import com.yanxue.entity.Check;
import com.yanxue.entity.Lecture;
import com.yanxue.entity.Party;
import com.yanxue.entity.Speak;
import com.yanxue.entity.Student;

public interface StudentDao extends BaseDao<Student> {
	
	//获取某个讲座学员列表
	List<Student> getStudents(String lectureId);
	//获取中奖学员列表
	List<Student> getAwardStudents(String lectureId);
	
	//获取意向学生列表
	List<Student> getIntentionStudents(String lectureId);
	
	int getStudentsCount(String lectureId);
	
	int getAwardStudentsCount(String lectureId);

	int getIntentionStudentsCount(String lectureId);

	Student checkStudent(String phone,String lecid);
	
	List<Student> getNewStudents(String lectureId,Date time);
	
	int addAward(String id,String lecId,String stuId);
    /**
     * @param lectureId
     * @return
     */
    Lecture getStatus(String lectureId);
    /**
     * @param lectureId
     * @return
     */
    List<Check> getCheck(String lectureId);
    /**
     * @param lectureId
     * @return
     */
    List<Speak> getSpeak(String lectureId);
    /**
     * @return
     */
    List<Party> getParty();
    /**
     * @param lecId
     * @return
     */
    String getActivity(String lecId);
	
}
