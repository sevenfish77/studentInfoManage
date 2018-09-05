package com.cqnu5070.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cqnu5070.dao.CourseDao;
import com.cqnu5070.model.Course;
import com.cqnu5070.model.PageBean;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.JsonUtil;
import com.cqnu5070.util.ResponseUtil;
import com.cqnu5070.util.StringUtil;
/**
 * 年级信息浏览和查询
 * @author Administrator
 *
 */
public class CourseListServlet extends HttpServlet{
	/*声明类*/
	DbUtil dbUtil=new DbUtil();
	CourseDao courseDao=new CourseDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	} 

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*获得request请求参数*/
		String page=request.getParameter("page");	//重要page：当前页
		String rows=request.getParameter("rows");	//重要rows：每页大小
		
		String couName=request.getParameter("couName");
		String gradeId=request.getParameter("gradeId");
		//查询判断
		Course course=new Course();
			
			if(StringUtil.isNotEmpty(gradeId)){//如果不为空
				course.setGradeId(Integer.parseInt(gradeId));
			}

	
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));	//强制转换
		Connection con=null;
		
		
		try{
			/*连接数据库*/
			con=dbUtil.getCon();
			
			JSONObject result=new JSONObject();	//封装一个返回的json
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(courseDao.courseList(con, pageBean, course));//得到年级信息并转换成JSONArray
			int total=courseDao.courseCount(con, course);//得到记录数
			result.put("rows", jsonArray);
			result.put("total", total);	//此处引号中的rows和total是由前台pagination（分页，JS的封装类）所得到
			
			/*response:将年级信息和记录数打印输出到页面*/
			ResponseUtil.write(response, result); //Response.Write会在响应的页面首行输出指定的文本
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				/*关闭数据库*/
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	

	
	
}
