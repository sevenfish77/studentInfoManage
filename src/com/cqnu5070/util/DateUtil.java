package com.cqnu5070.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 转换日期格式的封装方法
 * @author Administrator
 *
 */
public class DateUtil {

	/**
	 * 将日期格式转换为字符串
	 */
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	/**
	 * 将字符串转换为日期格式
	 */
	public static Date formatString(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
}
