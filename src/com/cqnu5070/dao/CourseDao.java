package com.cqnu5070.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cqnu5070.model.Course;
import com.cqnu5070.model.PageBean;
import com.cqnu5070.model.Student;
import com.cqnu5070.util.DateUtil;
import com.cqnu5070.util.StringUtil;


/**
 * 放置年级课程信息数据处理相关的方法
 * @author Administrator
 *
 */
public class CourseDao {

	/**
	 * 方法一、把数据库响应的查询的年级课程数据存放在ResultSet类对象中
	 */
	public ResultSet courseList(Connection con,PageBean pageBean,Course course)throws Exception{
		/*sql语句：关联查询*/
		StringBuffer sb=new StringBuffer("select * from t_course c,t_grade g where c.gradeId=g.id");
		
		if(StringUtil.isNotEmpty(course.getCouName())){
			sb.append("and c.couName like'%"+course.getCouName()+"%'");
		}
		if(course.getGradeId()!=-1){
			sb.append(" and c.gradeId ='"+course.getGradeId()+"'");
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
	public int courseCount(Connection con,Course course)throws Exception{
		/*sql语句：查找记录总数保存为total*/
		StringBuffer sb=new StringBuffer("select count(*) as total from t_course c,t_grade g where c.gradeId=g.id");
		
		if(StringUtil.isNotEmpty(course.getCouName())){
			sb.append("and c.couName like'%"+course.getCouName()+"%'");
		}
		if(course.getGradeId()!=-1){
			sb.append(" and c.gradeId ='"+course.getGradeId()+"'");
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * 方法三、删除年级课程信息
	 */
	public int courseDelete(Connection con,String delIds)throws Exception{
		/*sql语句：关联查询*/
		String sql="delete from t_course where couId in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 方法四、添加年级课程信息
	 */
	public int courseAdd(Connection con,Course course)throws Exception{
		/*sql语句：*/
		String sql="insert into t_course values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);	//实现PreparedStatement接口
		pstmt.setString(2, course.getCouName());
		pstmt.setInt(1, course.getGradeId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 方法五、修改年级课程信息
	 */
	public int courseModify(Connection con,Course course)throws Exception{
		/*sql语句*/
		String sql="update t_course set couName=?,gradeId=? where couId=?";
		
		PreparedStatement pstmt=con.prepareStatement(sql);	//实现PreparedStatement接口
		pstmt.setString(1, course.getCouName());
		pstmt.setInt(2, course.getGradeId());
		pstmt.setInt(3, course.getCouId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 方法六、在删除年级前进行判断
	 */
	public boolean getCourseByGradeId(Connection con,String gradeId)throws Exception{
		/*sql语句*/
		String sql="select * from t_course where gradeId=?";
		
		PreparedStatement pstmt=con.prepareStatement(sql);	//实现PreparedStatement接口
		pstmt.setString(2, gradeId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	
}
