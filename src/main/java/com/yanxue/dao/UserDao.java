package com.yanxue.dao;

import java.util.List;

import com.yanxue.entity.Users;



public interface UserDao {
	Users check(String username,String md5,String role);

	int changePwd(String username, String md5);

	Users get(int id);
	
	List<Users> getAll();
	
	List<Users> getAllList();
	
	int count();
	
	int add(Users d);
	
	int update(Users d);
	
	int del(String username);
	
	int resetPwd(String username,String pwd);

	int getListByNameCount(String name);

	List<Users> getListByName(String name);

	Users checkExists(String username);

}
