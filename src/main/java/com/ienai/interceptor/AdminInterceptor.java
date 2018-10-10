package com.ienai.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ienai.po.User;
import com.ienai.service.UserService;
import com.ienai.util.ApplicationContextUtil;
import com.ienai.util.CustomStringUtil;


/**
 * 
 * <p>Title: AdminInterceptor</p>
 * <p>Description: 登陆管理页面的拦截器</p>
 * @author akira
 * @date 2018年9月28日 下午7:17:07
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);
	
	// 后台登陆账号密码
	private String rootUsername = "kuroba_akira";
	private String rootPassword = "anataniaidai";
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
		if(null == user) {	// 用户没有登陆就访问管理页面
        	logger.info(CustomStringUtil
        			.returnInfoLog(request.getRemoteAddr(), null, "未登录试图访问管理页面"));
        	request.getRequestDispatcher("goFileLossError").forward(request, response);
        	return false;
        	
        } else if(user.getUsername() == rootUsername && user.getPassword() == rootPassword) { 
        	// 用户账号密码正确
        	// 超级管理员登录
        	logger.info(CustomStringUtil
        			.returnInfoLog(request.getRemoteAddr(), user.getUsername(), "超级管理员访问管理页面授权通过"));
        	return true;
        	
        } else {
        	UserService userService = (UserService) ApplicationContextUtil.get(UserService.class);
        	User temp = userService.checkUserAccount(user.getUsername(), user.getPassword());
        	if(true == temp.getIsAdmin()) {	// 管理员校验通过
        		logger.info(CustomStringUtil
            			.returnInfoLog(request.getRemoteAddr(), user.getUsername(), "获得授权访问管理页面"));
        		return true;
        	} else {	// 校验不通过
        		logger.warn(CustomStringUtil
        				.returnInfoLog(request.getRemoteAddr(), user.getUsername(), "未授权访问管理页面"));
        		request.getRequestDispatcher("goFileLossError").forward(request, response);
        		return false;
        	}
        	
        }
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
