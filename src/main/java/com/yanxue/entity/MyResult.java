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

    public MyResult(int status, String msg, Object data) {
        constractResult(status, msg, data);
    }

    public MyResult(int status, String msg) {
        constractResult(status, msg, null);
    }

    public MyResult(int status, Object data) {
        constractResult(status, null, data);
    }

    public static MyResult failMyResult(String msg) {
        return new MyResult(0, msg);
    }

    public MyResult(Object data){
        constractResult(1, null, data);
	}

    public MyResult(String msg) {
        constractResult(1, msg, null);
    }

	public MyResult(int status){
        constractResult(status, null, null);
	}

    private void constractResult(int status, String msg, Object data) {
        this.setStatus(status);
        this.setMsg(msg);
        this.setData(data);
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
		return "MyResult [status=" + status + ", msg=" + msg + ", data=" + data.toString()
				+ "]";
	}
	
}
