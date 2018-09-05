package com.cqnu5070.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.cqnu5070.dao.StudentDao;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.ResponseUtil;

public class StudentDeleteServlet extends HttpServlet{
	/*声明类*/
	DbUtil dbUtil=new DbUtil();
	StudentDao studentDao=new StudentDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*获得request请求参数*/
		String delIds=request.getParameter("delIds");
		Connection con=null;
		try{
			/*连接数据库*/
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();	//封装一个返回的json
			int delNums=studentDao.studentDelete(con, delIds);	//得到删除的数量
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败！");
			}
			/*response:将删除结果输出到页面*/
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
