/*
 * Copyright(C) 2019 NTT Plala Inc.
 * All rights reserved.
 */

package com.yanxue.util;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author NTT Plala Inc.
 * @version 1.0
 */
public class IpUtils {

    private static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

    public static String getIpAddr(HttpServletRequest request) {
        logger.info("request: {}", request);
        String ip = request.getHeader("x-forwarded-for");
        logger.info("x-forwarded-for ip: {}", ip);
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip) && isVaildIpAddr(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            ip = ip.split(",")[0];
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip) || !isVaildIpAddr(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.info("Proxy-Client-IP ip: {}", ip);
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip) || !isVaildIpAddr(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.info("WL-Proxy-Client-IP ip: {}", ip);
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip) || !isVaildIpAddr(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            logger.info("HTTP_CLIENT_IP ip: {}", ip);
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip) || !isVaildIpAddr(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info("HTTP_X_FORWARDED_FOR ip: {}", ip);
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip) || !isVaildIpAddr(ip)) {
            ip = request.getHeader("X-Real-IP");
            logger.info("X-Real-IP ip: {}", ip);
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip) || !isVaildIpAddr(ip)) {
            ip = request.getRemoteAddr();
            logger.info("getRemoteAddr ip: {}", ip);
        }
        logger.info("获取客户端ip: {}", ip);
        return ip;
    }

    private static boolean isVaildIpAddr(String ip) {
        return ip.matches(
                "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}")   //ipv4
                ||ip.matches("([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}");
//        return IPAddressUtil.isIPv4LiteralAddress(ip) || IPAddressUtil.isIPv6LiteralAddress(ip);
    }


    public static void main(String[] args) {
        IpUtils utils = new IpUtils();
        System.out.println("utils.isVaildIpAddr(\"001.00.23.1\") = " + isVaildIpAddr("001.00.23.1"));
        System.out.println("utils.isVaildIpAddr(\"00.0.0.0\") = " + isVaildIpAddr("00.0.0.0"));
        System.out.println("utils.isVaildIpAddr(\"1.001.23.1\") = " + isVaildIpAddr("1.001.23.1"));

        String ip = "0000";
        String[] split = ip.split(",");
        System.out.println("Arrays.toString(spli) = " + Arrays.toString(split));

    }
}
