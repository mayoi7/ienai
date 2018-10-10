package com.ienai.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ienai.po.Invcode;
import com.ienai.po.User;
import com.ienai.service.UserService;
import com.ienai.util.CustomStringUtil;
import com.ienai.util.HttpUtil;
import com.ienai.util.InsuranceUtil;

/**
 * 
 * <p>Title: UserController</p>
 * <p>Description: 提供用户信息的查询/校验/更新，
 * 其中一部分和注册重合的操作已经移入LoginController中</p>
 * @author akira
 * @date 2018年9月29日 上午8:04:00
 */
@RestController
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	

	
}
