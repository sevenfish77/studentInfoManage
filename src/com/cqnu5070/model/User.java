package com.cqnu5070.model;

/**
 * 用户模型类
 *
 */
public class User {
	
	public static final String USER_ADMIN = "1";		//管理员类型用户
	
	public static final String USER_STUDENT = "2";	//学生类型用户
	
	public static final String USER_TEACHER = "3";	//教师类型用户

	private int id;
	private String userName;
	private String password;
	private String type = USER_STUDENT; // 账户类型：默认2为学生；1为管理员，2为学生，3为教师
	
	public User() {
		super();
	}

	public User(String userName, String password,String type) {
		super();
		this.userName = userName;
		this.password = password;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
