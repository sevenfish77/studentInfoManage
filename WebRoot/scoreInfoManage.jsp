<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>成绩信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;
	
	/*根据填写的信息搜索成绩信息*/
	function searchScore(){
		//向后台传入数据
		$('#dg').datagrid('load',{	//调用load方法
			stuNo:$('#s_stuNo').val(),	//返回xxxx的值
			stuName:$('#s_stuName').val(),
			gradeId:$('#s_gradeId').combobox("getValue")
		});
	}

	/*打开修改年级信息对话框*/
	function openStudentModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');	//getSelections： 获取选中多行的数据
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑成绩信息");
		$("#fm").form("load",row);
		url="scoreSave?stuId="+row.stuId;
	}
	
	/*保存学生信息*/
	function saveScore(){
		$("#fm").form("submit",{	//调用submit方法
			url:url,
			success:function(result){
				if(result.errorMsg){	//如果添加失败
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{	//如果添加成功
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");	//重新加载grid
				}
			}
		});
	}
	
	/*重置对话框内容*/
	function resetValue(){
		$("#stuNo").val("");
		$("#stuName").val("");
		$("#math").val("");
		$("#english").val("");
		$("#it").val("");
		$("#mao").val("");
		$("#ma").val("");
		$("#sport").val("");
		$("#gradeId").combobox("setValue","");
	}
	
	/*关闭成绩信息对话框*/
	function closeScoreDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	/*导出*/
	function dao(){
	
// 			var rows = $("#dg").datagrid("getRows");	//获取页面上所有行的数据
// 	    	var datas = [];
// 	    	for(var i=0;i<rows.length;i++){
// 	    		datas.push(rows[i].itmid);
// 	    	}
// 	    	alert(datas.join(","));

	    	
        	//var url = "ScoreExportServlet?method=ExportScore&id="+exam.id;
	  		//window.open(url, "_blank");
	  	}
	
	
	
</script>
</head>
<body style="margin: 5px;">
	<!-- 表头 -->
	<table id="dg" title="成绩信息" class="easyui-datagrid" fitColumns="true" singleSelect="true" 
	 pagination="true" rownumbers="true" url="scoreList" fit="true" toolbar="#tb" sortOrder="DESC" remoteSort="false">
		<thead>
			<tr>
				<th field="stuId" width="50" align="center" hidden="true">编号</th>
				<th field="stuNo" width="100" align="center" sortable="true">学号</th>
				<th field="stuName" width="100" align="center">姓名</th>
<!-- 				<th field="gradeId" width="100" align="center" hidden="true">班级ID</th> -->
				<th field="gradeName" width="100" align="center">班级名称</th>
				<th field="math" width="100" align="center" sortable="true">数学</th>
				<th field="english" width="100" align="center" sortable="true">英语</th>
				<th field="it" width="100" align="center" sortable="true">计算机</th>
				<th field="mao" width="100" align="center" sortable="true">毛概</th>
				<th field="ma" width="100" align="center" sortable="true">马克思</th>
				<th field="sport" width="100" align="center" sortable="true">体育</th>
				
			</tr>
		</thead>
	</table>
	
	<!-- 	增删改查工具栏按钮 -->
	<div id="tb">
		<div>
			<a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		
		<!-- 关联搜索 -->
		<div>
			&nbsp;学号：&nbsp;
			<input type="text" name="s_stuNo" id="s_stuNo" size="10"/>
			&nbsp;姓名：&nbsp;
			<input type="text" name="s_stuName" id="s_stuName" size="10"/>
			&nbsp;所属班级：&nbsp;
			<input class="easyui-combobox" id="s_gradeId" name="s_gradeId" size="10" 
			data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'gradeName',url:'gradeComboList'"/>
			
			<a href="javascript:searchScore()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
			<a id="redo" href="javascript:dao()" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true">导出</a>
		</div>
	</div>
	
	<!--对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
				<tr>
					<td>学号：</td>
					<td><input type="text" name="stuNo" id="stuNo" class="easyui-validatebox" disabled="true"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>姓名：</td>
					<td><input type="text" name="stuName" id="stuName" class="easyui-validatebox" disabled="true"/></td>
				</tr>
					<td>数学：</td>
					<td><input type="text" name="math" id="math" class="easyui-validatebox"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>英语：</td>
					<td><input type="text" name="english" id="english" class="easyui-validatebox"/></td>
				<tr>
					<td>计算机：</td>
					<td><input type="text" name="it" id="it" class="easyui-validatebox"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>毛概：</td>
					<td><input type="text" name="mao" id="mao" class="easyui-validatebox"/></td>
				</tr>
				</tr>
					<td>马克思：</td>
					<td><input type="text" name="ma" id="ma" class="easyui-validatebox"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>体育：</td>
					<td><input type="text" name="sport" id="sport" class="easyui-validatebox"/></td>
				<tr>
				<tr>
					<td>班级名称：</td>
					<td><input class="easyui-combobox" id="gradeId" name="gradeId"  data-options="panelHeight:'auto',editable:false,disabled:true,valueField:'id',textField:'gradeName',url:'gradeComboList'"/></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 		对话框按钮 -->
	<div id="dlg-buttons">
		<a href="javascript:saveScore()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeScoreDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>