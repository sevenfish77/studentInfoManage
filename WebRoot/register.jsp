<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理系统注册</title>
<script type="text/javascript">
</script>
</head>
<body background="images/22.jpg" style="background-repeat:no-repeat;background-size:120% 143%;" >
	<div style="margin-top:125px;margin-left:500px;">
		<form action="register" method="post">
		<table  width="280" height="300"  style="background-color:#008B8B ;opacity:0.7;">
			<tr height="20"></tr>
			<tr height="30"> 
				<td width="6%"></td>
				<td width="30%" style="font-size:12px;font-family:"温软雅黑";color:white;font-">用户名</td>
				<td><input type="text" value="${userName }" name="userName" id="userName"/></td>
			</tr>
			<tr height="30">
			<td width="6%"></td>
				<td width="30%" style="font-size:12px;font-family:"微软雅黑";">输入密码</td>
				<td><input type="password" value="${password }" name="password" id="password"/></td>
			</tr>
			<tr height="30">
				<td width="6%"></td>
				<td width="30%" style="font-size:12px;font-family:"微软雅黑";">再次确认</td>
				<td><input type="password" value="${repassword }" name="repassword" id="repassword"/></td>
			</tr>
			
			<tr height="30">
				<td width="6%"></td>
				<td	width="30%">
					<input type="radio" name="type" checked value="2" />
					<label for="radio-1" style="font-size:12px;font-family:"微软雅黑";">学生</label>
				</td>
				<td	width="25%">
					<input type="radio" name="type" value="3" />
					<label for="radio-2" style="font-size:12px;font-family:"微软雅黑";">老师</label>
				</td>
			</tr>
			
			<tr height="30">
				<td width="6%"></td>
				<td width="30%"></td>
				<td><input type="submit" value="完成注册"></td>
				
			</tr>
			
			<tr height="5">
				<td colspan="3">
					<!--利用正则表达式用于输出异常信息的,提交到Servlet中 -->
					<font color="red" style="font-size:12px;font-family:"微软雅黑";">${error }</font>
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>