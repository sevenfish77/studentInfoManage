<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;
	
	/*根据填写的信息搜索学生信息*/
	function searchStudent(){
		//向后台传入数据
		$('#dg').datagrid('load',{	//调用load方法
			stuNo:$('#s_stuNo').val(),	//返回xxxx的值
			stuName:$('#s_stuName').val(),
			sex:$('#s_sex').combobox("getValue"),
			bbirthday:$('#s_bbirthday').datebox("getValue"),
			ebirthday:$('#s_ebirthday').datebox("getValue"),
			gradeId:$('#s_gradeId').combobox("getValue")
		});
	}
	
	
</script>
</head>
<body style="margin: 5px;">
	<!-- 		表头 -->
	<table id="dg" title="学生信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="studentList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				
				<th field="stuId" width="50" align="center" hidden="true">编号</th>
				<th field="stuNo" width="100" align="center" sortable="true">学号</th>
				<th field="stuName" width="100" align="center">姓名</th>
				<th field="sex" width="100" align="center">性别</th>
				<th field="birthday" width="100" align="center">出生日期</th>
				<th field="gradeId" width="100" align="center" hidden="true">班级ID</th>
				<th field="gradeName" width="100" align="center">班级名称</th>
				<th field="email" width="150" align="center">Email</th>
				<th field="stuDesc" width="250" align="center">学生备注</th>
				
			</tr>
		</thead>
	</table>
	
	<!-- 	增删改查工具栏按钮 -->
	<div id="tb">
		<div>
			&nbsp;学号：&nbsp;
			<input type="text" name="s_stuNo" id="s_stuNo" size="10"/>
			&nbsp;姓名：&nbsp;
			<input type="text" name="s_stuName" id="s_stuName" size="10"/>
			&nbsp;性别：&nbsp;
			<select class="easyui-combobox" id="s_sex" name="s_sex" editable="false" panelHeight="auto">
			    <option value="">请选择...</option>
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
			&nbsp;出生日期：&nbsp;
			<input class="easyui-datebox" name="s_bbirthday" id="s_bbirthday" editable="false" size="10"/>->
			<input class="easyui-datebox" name="s_ebirthday" id="s_ebirthday" editable="false" size="10"/>
			&nbsp;所属班级：&nbsp;
			
			<!-- 			关联搜索 -->
			<input class="easyui-combobox" id="s_gradeId" name="s_gradeId" size="10" 
			data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'gradeName',url:'gradeComboList'"/>
			
			<a href="javascript:searchStudent()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<!-- 		对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>学号：</td>
					<td><input type="text" name="stuNo" id="stuNo" class="easyui-validatebox" required="true"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>姓名：</td>
					<td><input type="text" name="stuName" id="stuName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><select class="easyui-combobox" id="sex" name="sex" editable="false" panelHeight="auto" style="width: 155px">
					    <option value="">请选择...</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select></td>
					<td></td>
					<td>出生日期：</td>
					<td><input class="easyui-datebox" name="birthday" id="birthday" required="true" editable="false" /></td>
				</tr>
				<tr>
					<td>班级名称：</td>
					<td><input class="easyui-combobox" id="gradeId" name="gradeId"  data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'gradeName',url:'gradeComboList'"/></td>
					<td></td>
					<td>Email：</td>
					<td><input type="text" name="email" id="email" class="easyui-validatebox" required="true" validType="email"/></td>
				</tr>
				<tr>
					<td valign="top">学生备注：</td>
					<td colspan="4"><textarea rows="7" cols="50" name="stuDesc" id="stuDesc"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>