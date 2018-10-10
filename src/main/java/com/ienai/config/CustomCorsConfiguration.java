package com.ienai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ienai.interceptor.AdminInterceptor;
import com.ienai.interceptor.LoginInterceptor;

@SpringBootConfiguration
public class CustomCorsConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminInterceptor adminINterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
						.addPathPatterns("/**")
						.excludePathPatterns("/goLogin")
						.excludePathPatterns("/login/**")
						.excludePathPatterns("/error/**");
		registry.addInterceptor(adminINterceptor).addPathPatterns("/admin/**");
	}
	
}
