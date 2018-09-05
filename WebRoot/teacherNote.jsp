<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教师信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;
	
	/*根据填写的信息搜索教师信息*/
	function searchTeacher(){
		//向后台传入数据
		$('#dg').datagrid('load',{	//调用load方法
			teaNo:$('#s_teaNo').val(),	//返回xxxx的值
			teaName:$('#s_teaName').val(),
			teaSex:$('#s_teaSex').combobox("getValue"),
			bbirthday:$('#s_bbirthday').datebox("getValue"),
			ebirthday:$('#s_ebirthday').datebox("getValue")
		});
	}
	
	
</script>
</head>
  
   
  
  <body style="margin: 5px;">
  <!-- 		表头 -->
	<table id="dg" title="教师信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="teacherList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="teaId" width="50" align="center" hidden="true">编号</th>
				<th field="teaNo" width="100" align="center" sortable="true">工号</th>
				<th field="teaName" width="100" align="center">姓名</th>
				<th field="teaSex" width="100" align="center">性别</th>
				<th field="teaBirthday" width="100" align="center">出生日期</th>
				<th field="gradeId" width="100" align="center" hidden="true" >班级ID</th>
				<th field="gradeName" width="100" align="center" hidden="true">班级名称</th>
				<th field="teaEmail" width="150" align="center">Email</th>
				<th field="teaDesc" width="250" align="center">课程</th>
				
			</tr>
		</thead>
	</table>
	
	<!-- 	增删改查工具栏按钮 -->
	<div id="tb">
		<div>
			&nbsp;工号：&nbsp;
			<input type="text" name="s_teaNo" id="s_teaNo" size="10"/>
			&nbsp;姓名：&nbsp;
			<input type="text" name="s_teaName" id="s_teaName" size="10"/>
			&nbsp;性别：&nbsp;
			<select class="easyui-combobox" id="s_teaSex" name="s_teaSex" editable="false" panelHeight="auto">
			    <option value="">请选择...</option>
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
			&nbsp;出生日期：&nbsp;
			<input class="easyui-datebox" name="s_bbirthday" id="s_bbirthday" editable="false" size="10"/>
			<input class="easyui-datebox" name="s_ebirthday" id="s_ebirthday" editable="false" size="10"/>
			
			<!--关联搜索 -->
			<a href="javascript:searchTeacher()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<!-- 对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>工号：</td>
					<td><input type="text" name="teaNo" id="teaNo" class="easyui-validatebox" required="true"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>姓名：</td>
					<td><input type="text" name="teaName" id="teaName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><select class="easyui-combobox" id="teaSex" name="teaSex" editable="false" panelHeight="auto" style="width: 155px">
					    <option value="">请选择...</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select></td>
					<td></td>
					<td>出生日期：</td>
					<td><input class="easyui-datebox" name="teaBirthday" id="teaBirthday" required="true" editable="false" /></td>
				</tr>
				<tr>
					<td>班级名称：</td>
					<td><input class="easyui-combobox" id="gradeId" name="gradeId"  data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'gradeName',url:'gradeComboList'"/></td>
					<td></td>
					<td>Email：</td>
					<td><input type="text" name="teaEmail" id="teaEmail" class="easyui-validatebox" required="true" validType="teaEmail"/></td>
				</tr>
				<tr>
					<td valign="top">课程：</td>
					<td colspan="4"><textarea rows="7" cols="50" name="teaDesc" id="teaDesc"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>
