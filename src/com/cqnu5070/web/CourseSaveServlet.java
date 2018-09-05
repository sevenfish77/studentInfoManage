package com.cqnu5070.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import com.cqnu5070.dao.CourseDao;
import com.cqnu5070.model.Course;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.ResponseUtil;
import com.cqnu5070.util.StringUtil;

public class CourseSaveServlet extends HttpServlet{
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
		/*设置request编码格式为utf-8*/
		request.setCharacterEncoding("utf-8");  //处理ajax封装异步提交的乱码问题
		/*获得request请求参数*/
		String couName=request.getParameter("couName");
		String gradeId=request.getParameter("gradeId");
		String couId=request.getParameter("couId");
		/*调用Course构造方法*/
		Course course=null;
		try {
			/*调用Student构造方法*/
		   course=new Course(couName,Integer.parseInt(gradeId)); //转换了int（Integer.parseInt）
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		if(StringUtil.isNotEmpty(couId)){
			course.setCouId(Integer.parseInt(couId));	//强制转换
		}
		Connection con=null;
		try{
			/*连接数据库*/
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();//封装一个返回的json
			
			if(StringUtil.isNotEmpty(couId)){ //判断id是否为空，对应不同操作
				/*修改年级信息*/
				saveNums=courseDao.courseModify(con, course);
			}else{
				/*添加年级信息*/
				saveNums=courseDao.courseAdd(con, course);
			}
			if(saveNums>0){
				result.put("success", "true");	//对应js函数：saveCourse()
			}else{
				result.put("success", "true");	//对应js函数：saveCourse()
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
