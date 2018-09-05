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

import com.cqnu5070.dao.GradeDao;
import com.cqnu5070.model.Grade;
import com.cqnu5070.model.PageBean;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.JsonUtil;
import com.cqnu5070.util.ResponseUtil;
/**
 * 年级信息浏览和查询
 * @author Administrator
 *
 */
public class GradeListServlet extends HttpServlet{
	/*声明类*/
	DbUtil dbUtil=new DbUtil();
	GradeDao gradeDao=new GradeDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*获得request请求参数*/
		String page=request.getParameter("page");	
		String rows=request.getParameter("rows");	
		String gradeName=request.getParameter("gradeName");
		
		/*如果查询值为空*/
		if(gradeName==null){
			gradeName="";
		}
		
		
		/*声明类*/
		Grade grade=new Grade();
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));	//强制转换
		Connection con=null;
		
		grade.setGradeName(gradeName);//设置gradeName为页面请求参数的值
		try{
			/*连接数据库*/
			con=dbUtil.getCon();
			
			JSONObject result=new JSONObject();	//封装一个返回的json
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, pageBean,grade));//得到年级信息并转换成JSONArray
			int total=gradeDao.gradeCount(con,grade);//得到记录数
			result.put("rows", jsonArray);
			result.put("total", total);	//此处引号中的rows和total是由前台pagination（分页，JS的封装类）所得到
			
			/*response:将年级信息和记录数打印输出到页面*/
			ResponseUtil.write(response, result);
			
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
