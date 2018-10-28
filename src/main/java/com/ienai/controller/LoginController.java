package com.ienai.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ienai.po.Invcode;
import com.ienai.po.User;
import com.ienai.service.UserService;
import com.ienai.util.CustomStringUtil;
import com.ienai.util.HttpUtil;
import com.ienai.util.InsuranceUtil;

/**
 * 
 * <p>Title: LoginController</p>
 * <p>Description: 用户进行登陆以及注册时进行操作</p>
 * @author akira
 * @date 2018年9月28日 下午6:51:26
 */
@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 校验邀请码是否可用
	 * @throws Exception 
	 */
	@GetMapping("/checkInvcode")
	public boolean checkInvcode(String invcode) throws Exception {
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), null, "校验邀请码是否可用"));
		Invcode record = userService.checkInvcode(invcode);
		if(record == null) {	// 假如邀请码不存在
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), null, "邀请码校验未通过"));
			return false;
		}
		
		userService.updateCodeInUsedById(record.getId(), record);
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), null, "邀请码校验通过，已更新数据库"));
		return true;
	}
	
	/**
	 * 校验是否重名(username)
	 */
	@GetMapping("/checkUsernameAvailable")
	public boolean checkUsernameAvailable(String username) throws Exception {
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), null, "校验用户名是否可用"));
		boolean isNotRepeated = userService.checkUsername(username);
		if(isNotRepeated) {
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), null, "用户名校验通过"));
		} else {
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), null, "用户名校验未通过"));
		}
		
		return isNotRepeated;
	}
	
	/**
	 * 插入一条用户信息(用户注册)
	 */
	@PostMapping("insertUserInfo")
	public ModelAndView insertUserInfo(User user) throws Exception {
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), "正在申请注册"));
		userService.insertUser(user);
		
		logger.info(CustomStringUtil
				.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), "注册完毕，已更新到数据库"));
		HttpSession session = request.getSession();
		
		// 构建一个新对象存在session中
		User temp = InsuranceUtil.changeUserToSessionUser(user);
		session.setAttribute("user", temp);
		
		return new ModelAndView("index");
	}
	
	
	/**
	 * 登陆校验以及跳转
	 * @throws Exception 
	 */
	@RequestMapping("/login")
	public String login(String username, String password) throws Exception {
		User user = userService.checkUserAccount(username, password);
		HttpSession session = request.getSession();
		
		if(null == user) {
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), null, "用户信息为空请求登陆"));
			return "redirect:/error/500";
		} else if(true == user.getIsAdmin()) {	// 假如用户是管理员
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), "管理员授权通过，访问管理页面"));
			session.setAttribute("user", InsuranceUtil.changeUserToSessionUser(user));	// 把信息存储到session中
			return "redirect:/admin/admin";
		} else {
			logger.info(CustomStringUtil
					.returnInfoLog(HttpUtil.getIpAddrByRequest(request), user.getUsername(), "普通用户授权通过，访问主页"));
			session.setAttribute("user", InsuranceUtil.changeUserToSessionUser(user));	// 把信息存储到session中
			return "redirect:/index";
		}
	}
}
