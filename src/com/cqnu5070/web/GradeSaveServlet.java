package com.cqnu5070.web;

import java.io.IOException;
import java.sql.Connection;

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
import com.cqnu5070.util.StringUtil;

public class GradeSaveServlet extends HttpServlet{
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
		/*设置request编码格式为utf-8*/
		request.setCharacterEncoding("utf-8");
		/*获得request请求参数*/
		String gradeName=request.getParameter("gradeName");
		String gradeDesc=request.getParameter("gradeDesc");
		String id=request.getParameter("id");
		
		/*调用Grade构造方法*/
		Grade grade=new Grade(gradeName,gradeDesc);
		
		if(StringUtil.isNotEmpty(id)){
			grade.setId(Integer.parseInt(id));	//强制转换
		}
		Connection con=null;
		try{
			/*连接数据库*/
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();//封装一个返回的json
			if(StringUtil.isNotEmpty(id)){
				/*修改年级信息*/
				saveNums=gradeDao.gradeModify(con, grade);
			}else{
				/*添加年级信息*/
				saveNums=gradeDao.gradeAdd(con, grade);
			}
			if(saveNums>0){
				result.put("success", "true");	//对应js函数：saveGrade()
			}else{
				result.put("success", "true");	//对应js函数：saveGrade()
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
