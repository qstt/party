package com.yanxue.dao;

import java.util.Date;
import java.util.List;

import com.yanxue.entity.Check;
import com.yanxue.entity.Party;
import com.yanxue.entity.Speak;
import com.yanxue.entity.Student;

public interface SpeakDao extends BaseDao<Speak> {
    
    Speak checkStudent(String number,String lecid);
    
    /**
     * @param lecture_id
     * @return
     */
    String getStatus(String lecture_id);


    /**
     * @param number
     * @return
     */
    Party getParty(String number);
    
    /**
     * @param ip
     * @param lecture_id
     * @return
     */
    Speak checkIp(String ip, String lecture_id);

    /**
     * @param t
     * @return
     */
    int updateIp(Speak t);

    
    
    
    
    
    
    
	
	//获取某个讲座学员列表
	List<Student> getStudents(String lectureId);
	//获取中奖学员列表
	List<Student> getAwardStudents(String lectureId);
	
	//获取意向学生列表
	List<Student> getIntentionStudents(String lectureId);
	
	int getStudentsCount(String lectureId);
	
	int getAwardStudentsCount(String lectureId);

	int getIntentionStudentsCount(String lectureId);

	
	List<Speak> getSpeakData(String lectureId,Date time);
	
	List<Speak> getSpeakData_1(String lectureId,Date time);
	
	int addAward(String id,String lecId,String stuId);

    /**
     * @param lecid
     * @param time
     */
    void updateSpeakDataStatus(String lecid, Date time);

  
    

    
	
}
