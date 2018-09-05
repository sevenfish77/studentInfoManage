<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理系统登录</title>
<script type="text/javascript">
	function resetValue(){
		document.getElementById("userName").value="";
		document.getElementById("password").value="";
		document.getElementById("checkCode").value="";
	}
	
	/*当验证码看不清楚时重新加载验证码图片*/
	function reloadCode()
     {
         var time=new Date().getTime();
         document.getElementById("imagecode").src="<%= request.getContextPath()%>/image?d="+time;
     }
</script>
</head>
<body background="images/22.jpg" style="background-repeat:no-repeat;background-size:120% 143%;" >
	<div style="margin-top:125px;margin-left:900px;">
<!-- 此处的login和配置文件中的url-pattern相对应 -->
		<form action="login" method="post">
		<table  width="280" height="300"  style="background-color:#008B8B;opacity:0.7;">
			<tr height="20"></tr>
			<tr height="30"> 
				<td width="6%"></td>
				<td width="10%" style="font-size:12px;font-family:"微软雅黑";color:white;font-">用户名：</td>
				<td><input type="text" value="${userName }" name="userName" id="userName"/></td>
			</tr>
			<tr height="30">
			<td width="6%"></td>
				<td width="10%" style="font-size:12px;font-family:"微软雅黑";">密  码：</td>
				<td><input type="password" value="${password }" name="password" id="password"/></td>
			</tr>
			<tr height="30">
				<td width="6%"></td>
				<td width="10%" style="font-size:12px;font-family:"微软雅黑";">验证码：</td>
				<td><input type="text" name="checkCode" id="checkCode"/></td>
			</tr>
			
			<tr height="30">
				<td width="6%"></td>
				<td width="10%"></td>
				<td>
					<img alt="验证码" id="imagecode" src="<%= request.getContextPath()%>/image"/>
					<a href="javascript:reloadCode();" style="font-size:12px;font-family:"微软雅黑";">看不清楚</a><br>
				</td>
			
			</tr>
			<tr height="30">
				<td width="6%"></td>
				<td	width="10%">
					<input type="radio" name="type" checked value="2" />
					<label for="radio-1" style="font-size:12px;font-family:"微软雅黑";">学生</label>
				</td>
				<td	width="25%">
					<input type="radio" name="type" value="3" />
					<label for="radio-2" style="font-size:12px;font-family:"微软雅黑";">老师</label>
					<input type="radio" name="type" value="1" />
					<label for="radio-3" style="font-size:12px;font-family:"微软雅黑";">管理员</label>
				</td>
			</tr>
			
			<tr height="10">
				<td width="6%"></td>
				<td width="10%" style="font-size:12px;font-family:"微软雅黑";"><input type="submit" value="登录"/></td>
				<td style="font-size:12px;font-family:"微软雅黑";"><input type="button" value="重置" onclick="resetValue()"/></td>
			</tr>
			
			<tr height="5">
				<td width="6%"></td>
				<td width="10%"></td>
				<td style="font-size:12px;font-family:"微软雅黑";"><a href="javascript:void(0);" onclick="window.location.href='/StudentInfoManage/register.jsp'">还未注册，点击这里</a></td>
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