package com.cqnu5070.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.cqnu5070.dao.TeacherDao;
import com.cqnu5070.model.Student;
import com.cqnu5070.model.Teacher;
import com.cqnu5070.util.DateUtil;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.ResponseUtil;
import com.cqnu5070.util.StringUtil;

public class TeacherSaveServlet extends HttpServlet {

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
		String teaBirthday=request.getParameter("teaBirthday");
		String gradeId=request.getParameter("gradeId");
		String teaEmail=request.getParameter("teaEmail");
		String teaDesc=request.getParameter("teaDesc");
		String teaId=request.getParameter("teaId");
		
		Teacher teacher=null;
		try {
			/*调用Teacher构造方法*/
			teacher = new Teacher(teaNo, teaName, teaSex, DateUtil.formatString(teaBirthday, "yyyy-MM-dd"),
					Integer.parseInt(gradeId), teaEmail, teaDesc);
		}  catch (Exception e1) {
			e1.printStackTrace();
		}
		if(StringUtil.isNotEmpty(teaId)){
			teacher.setTeaId(Integer.parseInt(teaId));
		}
		Connection con=null;
		try{
			/*连接数据库*/
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(teaId)){
				saveNums=teacherDao.teacherModify(con, teacher);
			}else{
				saveNums=teacherDao.teacherAdd(con, teacher);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
				result.put("errorMsg", "删除失败");
			}
			/*response:输出到页面*/
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
