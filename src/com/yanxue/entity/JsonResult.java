package com.yanxue.entity;

import java.io.Serializable;
/**
 * easyUI表格接收的返回值对象
 * @author Administrator
 *
 */
public class JsonResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2441995750155209398L;
	private Integer total;//总数量
	private Object rows;//所有的数据
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "JsonResult [total=" + total + ", rows=" + rows ;
	}
	
}
