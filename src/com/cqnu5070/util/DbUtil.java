package com.cqnu5070.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	//JDBC相关的配置
	private String dbUrl="jdbc:mysql://localhost:3306/db_studentinfo";
	private String dbUserName="root";
	private String dbPassword="123";
	private String jdbcName="com.mysql.jdbc.Driver";	//导入MySql的jar包中的一个类
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);	//导入MySql的jar包中的一个类
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("连接数据库成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
