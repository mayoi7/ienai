package com.ienai.util;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <p>Title: HttpUtil</p>
 * <p>Description: 对网络请求的一些操作</p>
 * @author akira
 * @date 2018年9月28日 下午6:23:42
 */
public class HttpUtil {

	/**
	 * 通过request获取ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("WL-Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getRemoteAddr();   
            if(ip.equals("127.0.0.1")){     
                //根据网卡取本机配置的IP     
                InetAddress inet=null;     
                try {     
                    inet = InetAddress.getLocalHost();     
                } catch (Exception e) {     
                    e.printStackTrace();     
                }     
                ip= inet.getHostAddress();     
            }  
        }   
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ip != null && ip.length() > 15){    
            if(ip.indexOf(",")>0){     
                ip = ip.substring(0,ip.indexOf(","));     
            }     
        }     
        return ip;
	}
}
