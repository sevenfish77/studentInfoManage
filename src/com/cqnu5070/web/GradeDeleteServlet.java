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
import com.cqnu5070.dao.StudentDao;
import com.cqnu5070.model.Grade;
import com.cqnu5070.model.PageBean;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.JsonUtil;
import com.cqnu5070.util.ResponseUtil;
/**
 * 根据年级名字删除年级信息
 * @author Administrator
 *
 */
public class GradeDeleteServlet extends HttpServlet{
	/*声明类*/
	DbUtil dbUtil=new DbUtil();
	GradeDao gradeDao=new GradeDao();
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
			JSONObject result=new JSONObject();//封装一个返回的json
			
			/*在删除年级前进行判断*/
			String str[]=delIds.split(",");
			for(int i=0;i<str.length;i++){
				boolean f=studentDao.getStudentByGradeId(con, str[i]);
				if(f){
					result.put("errorIndex", i);
					result.put("errorMsg", "班级下面有学生，不能删除！");
					ResponseUtil.write(response, result);
					return;
				}
			}
			int delNums=gradeDao.gradeDelete(con, delIds);	//删除数据，并得到删除的总量
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败");
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
