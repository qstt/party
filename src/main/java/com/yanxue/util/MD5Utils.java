package com.yanxue.util;

import org.springframework.util.DigestUtils;

public class MD5Utils {
	public static String getMD5(String pwd){
		System.out.println(pwd + ".....");
		String pwd1 = DigestUtils.md5DigestAsHex(pwd.getBytes());
		String pwd2 = DigestUtils.md5DigestAsHex(pwd1.substring(4,9).getBytes());
		String pwd3 = DigestUtils.md5DigestAsHex(pwd2.substring(4,9).getBytes());
		return pwd3;
	}
	public static void main(String[] args) {
		System.out.println(getMD5("666666"));
		System.out.println("dsdsa  ".trim() +",");
	}
}
