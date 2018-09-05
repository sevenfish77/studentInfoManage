package com.cqnu5070.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cqnu5070.dao.TeacherDao;
import com.cqnu5070.model.PageBean;
import com.cqnu5070.model.Teacher;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.JsonUtil;
import com.cqnu5070.util.ResponseUtil;
import com.cqnu5070.util.StringUtil;

public class TeacherListServlet extends HttpServlet {
	/*声明类*/
	DbUtil dbUtil=new DbUtil();
	TeacherDao teacherDao=new TeacherDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*设置request编码格式为utf-8*/
		request.setCharacterEncoding("utf-8");
		/*获得request请求参数*/
		String teaNo=request.getParameter("teaNo");
		String teaName=request.getParameter("teaName");
		String teaSex=request.getParameter("teaSex");
		String bbirthday=request.getParameter("bbirthday");
		String ebirthday=request.getParameter("ebirthday");
		String gradeId=request.getParameter("gradeId");
	
		Teacher teacher=new Teacher();
		if(teaNo!=null){
			teacher.setTeaNo(teaNo);
			teacher.setTeaName(teaName);
			teacher.setTeaSex(teaSex);
			if(StringUtil.isNotEmpty(gradeId)){
				teacher.setGradeId(Integer.parseInt(gradeId));
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
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(teacherDao.teacherList(con, pageBean,teacher,bbirthday,ebirthday));	//得到学生信息并转换成JSONArray
			int total=teacherDao.teacherCount(con,teacher,bbirthday,ebirthday);//得到记录数
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
