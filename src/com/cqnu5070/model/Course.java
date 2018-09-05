package com.cqnu5070.model;
/**
 * student模型类
 * @author Administrator
 *
 */
public class Course {
	private int couId;
	private String couName;
	private int gradeId=-1;
	
	private String gradeName;
	
	public Course() {
		super();
	}
	
	/*构造方法*/
	public Course(String couName, int gradeId) {
		super();
		this.couName = couName;
		this.gradeId = gradeId;
	}

	/*get和set方法*/
	public int getCouId() {
		return couId;
	}

	public void setCouId(int couId) {
		this.couId = couId;
	}

	public String getCouName() {
		return couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	

	

	

	
	


	
}
