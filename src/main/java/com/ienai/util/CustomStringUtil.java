package com.ienai.util;

/**
 * 
 * <p>Title: CustomStringUtil</p>
 * <p>Description: 自定义处理字符串/返回指定格式串</p>
 * @author akira
 * @date 2018年9月29日 上午8:05:51
 */
public class CustomStringUtil {

	/**
	 * 输出用户进行操作的log串
	 * @param username 如果用户未登陆这里就是空
	 */
	public static String returnInfoLog(String ip, String username, String action) {
		String str = username;
		if(null == username) str = "无";
		
		return  "\n-------------------------------"
				+ "\nip：" + ip
				+ "\n用户名：" + str
				+ "\n时间：" + CustomDateUtil.returnCurrentTime()
				+ "\n行为：" + action
				+ "\n-------------------------------";
	}
}
