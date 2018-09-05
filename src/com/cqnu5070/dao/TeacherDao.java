package com.cqnu5070.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cqnu5070.model.PageBean;
import com.cqnu5070.model.Teacher;
import com.cqnu5070.util.DateUtil;
import com.cqnu5070.util.StringUtil;

/**
 * 放置教师信息数据处理相关的方法
 * @author Administrator
 *
 */
public class TeacherDao {
	/**
	 * 方法一、把数据库响应的查询的学生数据存放在ResultSet类对象中
	 */
	public ResultSet teacherList(Connection con,PageBean pageBean,Teacher teacher,String bbirthday,String ebirthday)throws Exception{
		/*sql语句：关联查询*/
		StringBuffer sb=new StringBuffer("select * from t_teacher s,t_grade g where s.gradeId=g.id");
		
		/*判断各项数据是否为空*/
		if(StringUtil.isNotEmpty(teacher.getTeaNo())){
			sb.append(" and s.teaNo like '%"+teacher.getTeaNo()+"%'");
		}
		if(StringUtil.isNotEmpty(teacher.getTeaName())){
			sb.append(" and s.teaName like '%"+teacher.getTeaName()+"%'");
		}
		if(StringUtil.isNotEmpty(teacher.getTeaSex())){
			sb.append(" and s.teaSex ='"+teacher.getTeaSex()+"'");
		}
		if(teacher.getGradeId()!=-1){
			sb.append(" and s.gradeId ='"+teacher.getGradeId()+"'");
		}
		if(StringUtil.isNotEmpty(bbirthday)){
			sb.append(" and TO_DAYS(s.teaBirthday)>=TO_DAYS('"+bbirthday+"')");
		}
		if(StringUtil.isNotEmpty(ebirthday)){
			sb.append(" and TO_DAYS(s.teaBirthday)<=TO_DAYS('"+ebirthday+"')");
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
	public int teacherCount(Connection con,Teacher teacher,String bbirthday,String ebirthday)throws Exception{
		/*sql语句：查找记录总数保存为total*/
		StringBuffer sb=new StringBuffer("select count(*) as total from t_teacher s,t_grade g where s.gradeId=g.id");
		if(StringUtil.isNotEmpty(teacher.getTeaNo())){
			sb.append(" and s.teaNo like '%"+teacher.getTeaNo()+"%'");
		}
		if(StringUtil.isNotEmpty(teacher.getTeaName())){
			sb.append(" and s.teaName like '%"+teacher.getTeaName()+"%'");
		}
		if(StringUtil.isNotEmpty(teacher.getTeaSex())){
			sb.append(" and s.teaSex ='"+teacher.getTeaSex()+"'");
		}
		if(teacher.getGradeId()!=-1){
			sb.append(" and s.gradeId ='"+teacher.getGradeId()+"'");
		}
		if(StringUtil.isNotEmpty(bbirthday)){
			sb.append(" and TO_DAYS(s.teaBirthday)>=TO_DAYS('"+bbirthday+"')");
		}
		if(StringUtil.isNotEmpty(ebirthday)){
			sb.append(" and TO_DAYS(s.teaBirthday)<=TO_DAYS('"+ebirthday+"')");
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
	 * 方法三、删除学生信息
	 */
	public int teacherDelete(Connection con,String delIds)throws Exception{
		/*sql语句：关联查询*/
		String sql="delete from t_teacher where teaId in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 方法四、添加学生信息
	 */
	public int teacherAdd(Connection con,Teacher teacher)throws Exception{
		/*sql语句：*/
		String sql="insert into t_teacher values(null,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt=con.prepareStatement(sql);	//实现PreparedStatement接口
		pstmt.setString(1, teacher.getTeaNo());
		pstmt.setString(2, teacher.getTeaName());
		pstmt.setString(3, teacher.getTeaSex());
		pstmt.setString(4, DateUtil.formatDate(teacher.getTeaBirthday(), "yyyy-MM-dd"));
		pstmt.setInt(5, teacher.getGradeId());
		pstmt.setString(6, teacher.getTeaEmail());
		pstmt.setString(7, teacher.getTeaDesc());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 方法五、修改学生信息
	 */
	public int teacherModify(Connection con,Teacher teacher)throws Exception{
		/*sql语句*/
		String sql="update t_teacher set teaNo=?,teaName=?,teaSex=?,teaBirthday=?,gradeId=?,teaEmail=?,teaDesc=? where teaId=?";
		
		PreparedStatement pstmt=con.prepareStatement(sql);	//实现PreparedStatement接口
		pstmt.setString(1, teacher.getTeaNo());
		pstmt.setString(2, teacher.getTeaName());
		pstmt.setString(3, teacher.getTeaSex());
		pstmt.setString(4, DateUtil.formatDate(teacher.getTeaBirthday(), "yyyy-MM-dd"));
		pstmt.setInt(5, teacher.getGradeId());
		pstmt.setString(6, teacher.getTeaEmail());
		pstmt.setString(7, teacher.getTeaDesc());
		pstmt.setInt(8, teacher.getTeaId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 方法六、在删除年级前进行判断
	 */
	public boolean getStudentByGradeId(Connection con,String gradeId)throws Exception{
		/*sql语句*/
		String sql="select * from t_teacher where gradeId=?";
		
		PreparedStatement pstmt=con.prepareStatement(sql);	//实现PreparedStatement接口
		pstmt.setString(1, gradeId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}

}
