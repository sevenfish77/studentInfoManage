package com.cqnu5070.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cqnu5070.model.Grade;
import com.cqnu5070.model.PageBean;
import com.cqnu5070.model.Score;
import com.cqnu5070.model.Student;
import com.cqnu5070.util.StringUtil;

/**
 * 放置学生成绩处理相关的方法
 * @author Administrator
 *
 */
public class ScoreDao {
	/**
	 * 方法一、把数据库响应的查询的学生成绩存放在ResultSet类对象中
	 */
	public ResultSet scoreList(Connection con,PageBean pageBean,Student student,Score score)throws Exception{
		/*sql语句：关联查询*/
		StringBuffer sb=new StringBuffer("SELECT * FROM t_student t,t_score s,t_grade g WHERE t.stuId=s.stuId AND t.gradeId=g.id");
		
		/*判断各项数据是否为空*/
		if(StringUtil.isNotEmpty(student.getStuNo())){
			sb.append(" and t.stuNo like '%"+student.getStuNo()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getStuName())){
			sb.append(" and t.stuName like '%"+student.getStuName()+"%'");
		}
		if(student.getGradeId()!=-1){
			sb.append(" and t.gradeId ='"+student.getGradeId()+"'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	
	
	/**
	 * 方法二、得到记录总数
	 */
	public int scoreCount(Connection con,Student student,Score score)throws Exception{
		/*sql语句：查找记录总数保存为total*/
		StringBuffer sb=new StringBuffer("SELECT COUNT(*) AS total FROM t_student t,t_score s,t_grade g WHERE t.stuId=s.stuId AND t.gradeId=g.id");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		
		/*判断各项数据是否为空*/
		if(StringUtil.isNotEmpty(student.getStuNo())){
			sb.append(" and t.stuNo like '%"+student.getStuNo()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getStuName())){
			sb.append(" and t.stuName like '%"+student.getStuName()+"%'");
		}
		if(student.getGradeId()!=-1){
			sb.append(" and t.gradeId ='"+student.getGradeId()+"'");
		}
		
		
		
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 方法三、修改
	 */
	public int scoreModify(Connection con,Score score)throws Exception{
		/*sql语句：*/
		String sql="UPDATE t_score SET math=?,english=?,it=?,mao=?,ma=?,sport=? WHERE stuId=?";
		
		PreparedStatement pstmt=con.prepareStatement(sql);//实现PreparedStatement接口
		pstmt.setInt(1, score.getMath());
		pstmt.setInt(2, score.getEnglish());
		pstmt.setInt(3, score.getIt());
		pstmt.setInt(4, score.getMao());
		pstmt.setInt(5, score.getMa());
		pstmt.setInt(6, score.getSport());
		pstmt.setInt(7, score.getStuId());
		return pstmt.executeUpdate();	//executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用
	}

}
