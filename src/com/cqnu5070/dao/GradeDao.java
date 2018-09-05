package com.cqnu5070.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cqnu5070.model.Grade;
import com.cqnu5070.model.PageBean;
import com.cqnu5070.util.DbUtil;
import com.cqnu5070.util.StringUtil;
/**
 * 放置对年级信息操作的方法
 * @author Administrator
 *
 */
public class GradeDao {

	/**
	 * 方法一、把数据库响应的查询的年级数据存放在ResultSet类对象中
	 */
	public ResultSet gradeList(Connection con,PageBean pageBean,Grade grade)throws Exception{
		
		/*sql语句：查找整个表*/
		StringBuffer sb=new StringBuffer("select * from t_grade");
		
		/*如果年级名字不为空*/
		if(grade!=null && StringUtil.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}
		/*如果分页数不为空*/
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		
		/*前面两个if成立则替换and*/
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		
		return pstmt.executeQuery();	//executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用
	}
	
	/**
	 * 方法二、得到记录总数
	 */
	public int gradeCount(Connection con,Grade grade)throws Exception{
		
		/*sql语句：查找记录总数保存为total*/
		StringBuffer sb=new StringBuffer("select count(*) as total from t_grade");
		
		/*如果年级名字不为空*/
		if(StringUtil.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}
		/*前面if成立则替换and*/
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));//实现PreparedStatement接口
		ResultSet rs=pstmt.executeQuery();//executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 方法三、删除年级信息
	 */
	public int gradeDelete(Connection con,String delIds)throws Exception{
		/*sql语句：*/
		String sql="delete from t_grade where id in("+delIds+")";	//delete from tableName where field in (1,3,5)←ID值
		
		PreparedStatement pstmt=con.prepareStatement(sql);	//实现PreparedStatement接口
		return pstmt.executeUpdate();	//executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用
	}
	
	/**
	 * 方法四、添加年级信息
	 */
	public int gradeAdd(Connection con,Grade grade)throws Exception{
		/*sql语句：*/
		String sql="insert into t_grade values(null,?,?)";
		
		PreparedStatement pstmt=con.prepareStatement(sql);//实现PreparedStatement接口
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		return pstmt.executeUpdate();	//executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用
	}
	
	
	/**
	 * 方法四、修改
	 */
	public int gradeModify(Connection con,Grade grade)throws Exception{
		/*sql语句：*/
		String sql="update t_grade set gradeName=?,gradeDesc=? where id=?";
		
		PreparedStatement pstmt=con.prepareStatement(sql);//实现PreparedStatement接口
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		pstmt.setInt(3, grade.getId());
		return pstmt.executeUpdate();	//executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用
	}
}
