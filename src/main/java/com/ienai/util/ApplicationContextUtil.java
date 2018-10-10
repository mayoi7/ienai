package com.ienai.util;

import org.springframework.context.ApplicationContext;

/**
 * 
 * <p>Title: ApplicationContextUtil</p>
 * <p>Description: 应用程序上下文工具类，用于在不能自动注入的场合实现对象的获取</p>
 * @author akira
 * @date 2018年9月30日 上午8:51:26
 */
public class ApplicationContextUtil {


    public static ApplicationContext applicationContext;

    /**
     * 通过名称获取bean
     */
    public static Object get(String name) {
        return applicationContext.getBean(name);
    }


    /**
     * 通过类型获取bean
     */
    public static Object get(Class<?> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 判断某个bean是不是存在
     */
    public static boolean has(String name) {
        return applicationContext.containsBean(name);
    }


}