package com.yanxue.entity;

public class Users {
	private String id;//'id',
	private String username;// '用户名',
	private String pwd ;
	private String realname;
	private String sex ;
	private String tel ;
	private String role ;// '权限1，超级，2，低级',
	private String isdel  ;//'是否删除0删除，1存在'
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", pwd=" + pwd
				+ ", sex=" + sex + ", tel=" + tel + ", role=" + role
				+ ", isdel=" + isdel + "]";
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
}
