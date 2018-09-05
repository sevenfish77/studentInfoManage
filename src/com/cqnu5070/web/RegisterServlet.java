package com.cqnu5070.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cqnu5070.dao.UserDao;
import com.cqnu5070.model.User;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.StringUtil;

public class RegisterServlet extends HttpServlet{
	
	/*声明需要用到的类*/
	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * 为了安全性，一般使用post进行提交
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*获得请求参数*/
		String userName=request.getParameter("userName");		
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		String type=request.getParameter("type");
		
        response.setContentType("text/html;charset=utf-8");//解决乱码问题
        PrintWriter out=response.getWriter();
		
		
		/*服务器端跳转:当输入为空时，弹出提示信息！*/
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
			request.setAttribute("error", "用户名或密码为空！");	//此处与的error与JSP代码相对应
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		int password1 = Integer.parseInt(password);
		int password2 = Integer.parseInt(repassword);
		if(password1 != password2){
			request.setAttribute("error", "两次输入的密码不一致！");	//此处与的error与JSP代码相对应
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		
		
		/*服务器端跳转:当输入密码和账户错误时，弹出提示信息！*/
		User user=new User(userName, password, type);
		Connection con=null;
		try {
				con=dbUtil.getCon();	//获取数据库连接
				userDao.register(con, user);
				//response.sendRedirect("index.jsp");

				 response.setCharacterEncoding("utf-8");


				 out.print("<script>alert('注册成功...');window.location='/StudentInfoManage/index.jsp' </script>");

				 out.flush();

				 out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
}

