package com.cqnu5070.model;

import java.util.Date;

public class Teacher {
	private int teaId;//id
	private String teaNo;//工号
	private String teaName;//姓名
	private String teaSex;//性别
	private Date teaBirthday;//生日
	private int gradeId = -1;
	private String teaEmail;//Email
	private String teaDesc;//课程
	
	private String gradeName;
	
	public Teacher() {
		super();
	}
	
	/*构造方法*/
	public Teacher(String teaNo, String teaName, String teaSex, Date teaBirthday,int gradeId, String teaEmail, String teaDesc) {
		super();
		this.teaNo = teaNo;
		this.teaName = teaName;
		this.teaSex = teaSex;
		this.teaBirthday = teaBirthday;
		this.gradeId = gradeId;
		this.teaEmail = teaEmail;
		this.teaDesc = teaDesc;
	}
	
	/*get和set方法*/
	public int getTeaId() {
		return teaId;
	}
	public void setTeaId(int teaId) {
		this.teaId = teaId;
	}
	public String getTeaNo() {
		return teaNo;
	}
	public void setTeaNo(String teaNo) {
		this.teaNo = teaNo;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getTeaSex() {
		return teaSex;
	}
	public void setTeaSex(String teaSex) {
		this.teaSex = teaSex;
	}
	public Date getTeaBirthday() {
		return teaBirthday;
	}
	public void setTeaBirthday(Date teaBirthday) {
		this.teaBirthday = teaBirthday;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getTeaEmail() {
		return teaEmail;
	}
	public void setTeaEmail(String teaEmail) {
		this.teaEmail = teaEmail;
	}
	public String getTeaDesc() {
		return teaDesc;
	}
	public void setTeaDesc(String teaDesc) {
		this.teaDesc = teaDesc;
	}
	
}
