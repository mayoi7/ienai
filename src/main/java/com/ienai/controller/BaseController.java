package com.ienai.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ienai.util.CustomDateUtil;
import com.ienai.util.CustomStringUtil;
import com.ienai.util.HttpUtil;

/**
 * 
 * <p>Title: BaseController</p>
 * <p>Description: 负责跳转页面</p>
 * @author akira
 * @date 2018年9月28日 下午3:46:17
 */
@Controller
public class BaseController {

	@Autowired
	private HttpServletRequest request;
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@RequestMapping("/goLogin")
	public String goLogin() {
		String ip = HttpUtil.getIpAddrByRequest(request);
		logger.info(CustomStringUtil.returnInfoLog(ip, null, "访问登陆页面"));
		
		return "login/login";
	}
	
	@RequestMapping("/goSignup")
	public String goSignup() {
		String ip = HttpUtil.getIpAddrByRequest(request);
		logger.info(CustomStringUtil.returnInfoLog(ip, null, "访问注册页面"));
		
		return "login/registered";
	}
	
	@RequestMapping("/index")
	public String goIndexPage() {
		
		return "index";
	}
	
	@RequestMapping("goFileLossError")
	public String goFileLossError() {
		return "error/404.html";
	}
	
}
