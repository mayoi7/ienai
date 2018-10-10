package com.ienai.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ienai.po.User;
import com.ienai.util.CustomDateUtil;
import com.ienai.util.CustomStringUtil;

/**
 * 
 * <p>Title: LoginInterceptor</p>
 * <p>Description: 对未登陆的用户限制访问</p>
 * @author akira
 * @date 2018年9月28日 下午7:04:37
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 判断session
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		if (user != null) {	// 身份信息存在，放行

			logger.info(CustomStringUtil.returnInfoLog(request.getRemoteAddr(), user.getUsername(), "将要访问主页"));
			return true;
		} else {
			logger.info(CustomStringUtil.returnInfoLog(request.getRemoteAddr(), null,
					"请求访问" + handlerMethod.getMethod().getName() + "方法被拦截，将返回主页"));
			response.sendRedirect("goLogin");
			return false;
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
