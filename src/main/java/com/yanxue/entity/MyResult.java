package com.yanxue.entity;

import java.io.Serializable;
/**
 * 普通返回值
 * @author Administrator
 *
 */
public class MyResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3797570019606674283L;
	private int status;//状态码。0表示失败，1表啊是成功
	private String msg;//返回信息
	private Object data;//返回数据
	public MyResult(){
		
	}
	public MyResult(Object data){
		this.data = data;
	}
	public MyResult(String msg){
		this.msg = msg;
	}
	public MyResult(int status){
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "MyResult [status=" + status + ", msg=" + msg + ", data=" + data
				+ "]";
	}
	
}
