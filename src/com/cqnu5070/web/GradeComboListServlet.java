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
/**
 * 关联查询
 * @author Administrator
 *
 */
public class GradeComboListServlet extends HttpServlet{
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
		Connection con=null;
		try{
			/*连接数据库*/
			con=dbUtil.getCon();
			
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			
			jsonObject.put("id", "");
			jsonObject.put("gradeName", "请选择...");
			
			jsonArray.add(jsonObject);
			jsonArray.addAll(JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, null,null)));
			
			ResponseUtil.write(response, jsonArray);
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
