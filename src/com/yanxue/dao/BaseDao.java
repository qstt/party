package com.yanxue.dao;

import java.util.List;
import java.util.Map;


public interface BaseDao<T> {
	//分页查询
	List<T> findList(Map<String, Object> map);
	//统计总数
	int count(Map<String, Object> map);
	//添加数据
	int add(T t);
	//修改数据
	int update(T t);
	//删除数据
	int del(String id);
}
