package com.cqnu5070.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
/**
 * 向页面输送信息
 * @author Administrator
 *
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();//响应信息通过out对象输出到网页上，当响应结束时它自动被关闭，与jsp页面无关，无需刷新页面
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
