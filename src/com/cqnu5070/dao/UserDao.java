package com.cqnu5070.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cqnu5070.model.User;

/**
 * 实现登陆验证和注册功能
 * @author Administrator
 *
 */
public class UserDao {

	//登陆验证
	public User login(Connection con,User user) throws Exception{
		User resultUser=null;
		
		/*sql语句*/
		String sql="select * from t_user where userName=? and password=? and type=?";
		PreparedStatement pstmt=con.prepareStatement(sql);	//实现PreparedStatement接口，才能调用下列方法
		pstmt.setString(1, user.getUserName());		//数字代表第几个？的值
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getType());
		ResultSet rs=pstmt.executeQuery();	//executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserName(rs.getString("userName"));	//将数据库里表中所对应字段""的值赋给User
			resultUser.setPassword(rs.getString("password"));
			resultUser.setType(rs.getString("type"));
		}
		return resultUser;
	}
	
	
	//注册功能
	public int register(Connection con,User user) throws Exception{
		User resultUser=null;
		
		/*sql语句*/
		String sql="INSERT INTO t_user(userName,password,type)VALUES(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);//实现PreparedStatement接口
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getType());
		return pstmt.executeUpdate();
	}
	
}
