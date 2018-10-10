package com.ienai.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * <p>Title: DateUtil</p>
 * <p>Description: 进行日期和时间相关的操作</p>
 * @author akira
 * @date 2018年9月28日 下午6:37:13
 */
public class CustomDateUtil {
	
	/**
	 * 获取sql中支持保存的当前时间
	 * @return Timestamp
	 */
	public static Timestamp returnCurrentTimeInSql() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
	
	/**
	 * 获取字符串类型的日期格式
	 * @return
	 */
	public static String returnStringDateFormat(Date date) {
		String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
		return dateStr;
	}
	
	/**
	 * 获取当前时间并返回格式化输出串
	 * @return
	 */
	public static String returnCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	// 设置日期格式
        return df.format(new Date());	// new Date()为获取当前系统时间
	}
}
