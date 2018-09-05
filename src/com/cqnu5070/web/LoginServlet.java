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

public class LoginServlet extends HttpServlet{
	
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
		String userName=request.getParameter("userName");	//jsp中通过request.getParameter("username")方法来获得请求参数username	
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		String piccode=(String) request.getSession().getAttribute("piccode");
        String checkCode=request.getParameter("checkCode");  //取值
        //checkCode=checkCode.toUpperCase();  //把字符全部转换为大写的（此语句可以用于验证码不区分大小写）
        response.setContentType("text/html;charset=utf-8");//解决乱码问题
        PrintWriter out=response.getWriter();
		
		/*设置request对象属性的值，达到点击登陆按钮之后账号和密码的值依旧存在的效果*/
		request.setAttribute("userName", userName);   //设置request对象中的一个属性名为"前者"的值(如JSP对应代码：value="${userName })为后者变量的值
		request.setAttribute("password", password);
		
		/*服务器端跳转:当输入为空时，弹出提示信息！*/
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)||StringUtil.isEmpty(checkCode)){
			request.setAttribute("error", "用户名或密码或验证码为空！");	//此处与的error与JSP代码相对应
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		/*服务器端跳转:当输入密码和账户错误时，弹出提示信息！*/
		User user=new User(userName, password, type);
		Connection con=null;
		try {
			con=dbUtil.getCon();	//获取数据库连接
			User currentUser=userDao.login(con, user);
			if(checkCode.equals(piccode)){
	             if(currentUser==null){
	 				request.setAttribute("error", "用户名或密码错误！");
	 				// 服务器跳转
	 				request.getRequestDispatcher("index.jsp").forward(request, response);
	 			}else{
	 				// 获取Session
	 				HttpSession session=request.getSession();
	 				session.setAttribute("currentUser", currentUser);
	 				// 客户端跳转
	 				if(type.equals("1")){
		 				response.sendRedirect("main.jsp");
	 				}if(type.equals("2")){
	 					response.sendRedirect("student.jsp");
	 				}if(type.equals("3")){
	 					response.sendRedirect("teacher.jsp");
	 				}
	 			}
	             
	         }
	         else
	         {		request.setAttribute("error", "验证码输入错误！");
	 				// 服务器跳转
	 				request.getRequestDispatcher("index.jsp").forward(request, response);
	         }
	         out.flush();//将流刷新
	         out.close();//将流关闭
			
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

