package com.ienai.service;

import com.ienai.po.Invcode;
import com.ienai.po.User;

/**
 * 
 * <p>Title: UserService</p>
 * <p>Description: 对用户信息进行操作的Service</p>
 * @author akira
 * @date 2018年9月28日 下午7:48:48
 */
public interface UserService {
	
	/**
	 * 校验邀请码合法性
	 * @return 如果查询到就返回查询到的对象，否则返回null
	 */
	public Invcode checkInvcode(String invcode) throws Exception;
	
	/**
	 * 校验用户身份
	 */
	public User checkUserAccount(String username, String password) throws Exception;
	
	/**
	 * 更新用户信息
	 */
	public boolean updateUserInfo(Integer id, User user) throws Exception;
	
	/**
	 * 通过用户id查找用户
	 */
	public User findUserById(Integer id) throws Exception;
	
	/**
	 * 通过用户名查找用户id
	 */
	public Integer findIdByUsername(String Username) throws Exception;
	
	/**
	 * 插入一条用户记录
	 */
	public void insertUser(User user) throws Exception;
	
	/**
	 * 通过id删除一个用户
	 * @retrun 返回删除的这个用户对象
	 */
	public User deleteUserById(Integer id) throws Exception;

	/**
	 * 把指定邀请码更新为已使用，这里参数的codeId无实际意义，只是为了提醒输入的record中的codeId不能为空
	 * 注意要更新isUsed和gmtModified两个属性
	 * @param codeId
	 */
	public void updateCodeInUsedById(Integer codeId, Invcode record);

	/**
	 * 检验用户名是否重复
	 * @param username
	 * @return true为不重复，即可用
	 */
	public boolean checkUsername(String username) throws Exception;
	
	/**
	 * 通过id检查管理员权限
	 * @return true为通过，可以访问管理员页面
	 * @throws Exception
	 */
	public boolean checkAdminPermissionById(Integer userId) throws Exception;


	
	/**
	 * 通过用户名密码来检查管理员权限
	 */
	public boolean checkAdminPermissionByAccount(String username, String password) throws Exception;
	
	/**
	 * 取消id为userId的用户对id为noteId的便条的care记录
	 * 注意，这种操作都要对note记录也进行修改
	 * @param id
	 */
	public void cancelCareByNoteId(Integer userId, Integer noteId) throws Exception;
	
	/**
	 * 添加id为userId的用户对id为noteId的便条的care记录。
	 * 注意，添加care的同时会自动进行like操作
	 */
	public void addCareAndLikeByNoteId(Integer userId, Integer noteId) throws Exception;

	/**
	 * 添加like记录，同上
	 */
	public void addLikeByNoteId(Integer userId, Integer noteId) throws Exception;
	
	/**
	 * 取消like记录，同上
	 */
	public void cancelLikeByNoteId(Integer userId, Integer noteId) throws Exception;
}
