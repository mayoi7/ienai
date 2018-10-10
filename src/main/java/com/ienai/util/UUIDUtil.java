package com.ienai.util;

import java.util.UUID;

/**
 * 
 * <p>Title: UUIDUtil</p>
 * <p>Description: 产生各种随机码</p>
 * @author akira
 * @date 2018年9月28日 下午6:05:21
 */
public class UUIDUtil {
	
	/**
	 * 产生一个20位的邀请码
	 * @return String 20位激活码
	 */
	public static String getinvcodeUUID() {
		String[] uuid = UUID.randomUUID().toString().split("-");
		return uuid[0] + uuid[1] + uuid[2] + uuid[3];
	}
}
