package com.yanxue.service;

import com.yanxue.entity.JsonResult;
import com.yanxue.entity.MyResult;



public interface BaseService<T> {
	MyResult add(T t);//统一添加数据接口
	MyResult update(T t);//统一修改数据接口
	MyResult del(String id);//统一删除数据接口
	MyResult getData(T t);//统一获取某一条数据接口
	JsonResult getJsonData();//统一获取用户easyui表格展示数据接口
}
