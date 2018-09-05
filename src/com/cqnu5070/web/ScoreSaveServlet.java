package com.cqnu5070.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cqnu5070.dao.ScoreDao;
import com.cqnu5070.dao.StudentDao;
import com.cqnu5070.model.Score;
import com.cqnu5070.model.Student;
import com.cqnu5070.model.PageBean;
import com.cqnu5070.util.DateUtil;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.JsonUtil;
import com.cqnu5070.util.ResponseUtil;
import com.cqnu5070.util.StringUtil;

public class ScoreSaveServlet extends HttpServlet{
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
		/*设置request编码格式为utf-8*/
		request.setCharacterEncoding("utf-8");
		/*获得request请求参数*/
		String id=request.getParameter("id");
		String stuId=request.getParameter("stuId");
		String math=request.getParameter("math");
		String english=request.getParameter("english");
		String it=request.getParameter("it");
		String mao=request.getParameter("mao");
		String ma=request.getParameter("ma");
		String sport=request.getParameter("sport");
		
		Score score = null;
		try {
			/*调用Score构造方法*/
			score = new Score(Integer.parseInt(stuId), Integer.parseInt(math), Integer.parseInt(english), 
							  Integer.parseInt(it), Integer.parseInt(mao), Integer.parseInt(ma), Integer.parseInt(sport));
		}  catch (Exception e1) {
			e1.printStackTrace();
		}
		if(StringUtil.isNotEmpty(id)){
			score.setId(Integer.parseInt(id));
		}
		Connection con=null;
		try{
			/*连接数据库*/
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
				saveNums = scoreDao.scoreModify(con, score);
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
