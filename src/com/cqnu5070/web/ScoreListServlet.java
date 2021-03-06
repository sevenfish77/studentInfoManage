package com.cqnu5070.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cqnu5070.dao.ScoreDao;
import com.cqnu5070.dao.StudentDao;
import com.cqnu5070.model.PageBean;
import com.cqnu5070.model.Score;
import com.cqnu5070.model.Student;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.JsonUtil;
import com.cqnu5070.util.ResponseUtil;
import com.cqnu5070.util.StringUtil;

public class ScoreListServlet extends HttpServlet {
	/*声明类*/
	DbUtil dbUtil=new DbUtil();
	ScoreDao scoreDao = new ScoreDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Score score = new Score();
		Student student = new Student();
		/*获得request请求参数*/
		String stuNo=request.getParameter("stuNo");
		String stuName=request.getParameter("stuName");
		String gradeId=request.getParameter("gradeId");
		
		if(stuNo!=null){
			student.setStuNo(stuNo);
			student.setStuName(stuName);
			if(StringUtil.isNotEmpty(gradeId)){
				student.setGradeId(Integer.parseInt(gradeId));
			}
		}
		
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));//强制转换
		Connection con=null;
		
		try{
			/*连接数据库*/
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();	//封装一个返回的json
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(scoreDao.scoreList(con, pageBean,student, score));
			int total = scoreDao.scoreCount(con, student, score);
			result.put("rows", jsonArray);
			result.put("total", total);	//此处引号中的rows和total是由前台pagination（分页，JS的封装类）所得到
			
			/*response:将分数信息和记录数打印输出到页面*/
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
