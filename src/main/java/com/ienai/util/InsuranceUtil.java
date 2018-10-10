package com.ienai.util;

import com.ienai.po.User;

/**
 * 
 * <p>Title: InsuranceUtil</p>
 * <p>Description: 一些为了避免信息外泄的操作</p>
 * @author akira
 * @date 2018年9月30日 上午9:16:29
 */
public class InsuranceUtil {

	/**
	 * 将一个包含完整信息的User对象转化为仅含Session所需内容的对象
	 * 注意：User中至少需要有id和username
	 */
	public static User changeUserToSessionUser(User user) {
		User temp = new User();
		temp.setId(user.getId());
		temp.setUsername(user.getUsername());
		
		return temp;
	}
	
	/**
	 * 将一个完整的User转化为仅含主页需要信息的对象
	 */
	public static User changeUserToHomeUser(User user) {
		User temp = new User();
		
		temp.setId(user.getId());
		temp.setUsername(user.getUsername());
		temp.setAvatar(user.getAvatar());
		temp.setIntro(user.getIntro());
		temp.setName(user.getName());
		temp.setSecretLover(user.getSecretLover());
		
		return temp;
	}
}
