package com.yanxue.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

public class MD5Utils {

	private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

	public static String getMD5(String pwd){
		logger.info("{}.....", pwd);
		String pwd1 = DigestUtils.md5DigestAsHex(pwd.getBytes());
		String pwd2 = DigestUtils.md5DigestAsHex(pwd1.substring(4,9).getBytes());
		String pwd3 = DigestUtils.md5DigestAsHex(pwd2.substring(4,9).getBytes());
		return pwd3;
	}
	public static void main(String[] args) {
		logger.info("getMD5(666666) result :{}", getMD5("666666"));
	}
}
