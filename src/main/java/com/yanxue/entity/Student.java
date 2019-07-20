package com.yanxue.entity;

import java.util.Date;

public class Student {
	private String id;
	private String name;
	private String academy;
	private String major;
	private String phone;
	private String qq;
	private String lecture_id;
	private String intention;
	private String lession;
	private Date create_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(String lecture_id) {
		this.lecture_id = lecture_id;
	}
	public String getIntention() {
		return intention;
	}
	public void setIntention(String intention) {
		this.intention = intention;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", academy=" + academy
				+ ", major=" + major + ", phone=" + phone + ", qq=" + qq
				+ ", lecture_id=" + lecture_id + ", intention=" + intention
				+ ", create_time=" + create_time + "]";
	}
	public String getLession() {
		return lession;
	}
	public void setLession(String lession) {
		this.lession = lession;
	}
	
}
