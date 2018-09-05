<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>年级课程信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;
	
	/*根据填写的信息搜索年级课程信息*/
	function searchCourse(){
		//向后台发送数据
		$('#dg').datagrid('load',{	//调用load方法
			//couName:$('#c_couName').val(),
			//返回xxxx的值
			gradeId:$('#c_gradeId').combobox("getValue") //其中select属性的应该用combobox属性
		});
	}
	
	/*删除年级课程信息*/
	function deleteCourse(){
		var selectedRows=$("#dg").datagrid('getSelections');	//getSelections： 获取选中多行的数据
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].couId);	//push() 方法可向数组的末尾添加一个或多个元素，并返回新的长度。
		}
		var ids=strIds.join(",");	//join() 方法用于把数组中的所有元素放入一个字符串，元素是通过指定的分隔符进行分隔的。
		
		//利用AJAX技术更新网页的一部分
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){	//如果点击确定的话
			
				//jQuery.post( url, [data], [callback], [type] ) 
				//callback (Function) : (可选) 载入成功时回调函数(只有当Response的返回状态是success才是调用该方法)
				
				$.post("courseDelete",{delIds:ids},function(result){	//"gradeDelete",{delIds:ids}
																		//  请求地址		     请求参数
					//如果删除成功的话
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");	//reload：实现刷新当前页的功能
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	/*打开添加年级课程信息对话框*/
	function openCourseAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加年级课程信息");
		url="courseSave";

	}
	
	
	/*打开修改年级信息对话框*/
	function openCourseModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');	//getSelections： 获取选中多行的数据
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑学生信息");
		$("#fm").form("load",row);
		url="courseSave?couId="+row.couId;
		
	
	}
	
	/*保存学生信息*/
	function saveCourse(){
		$("#fm").form("submit",{	//调用submit方法
			url:url,
			onSubmit:function(){
				if($('#gradeId').combobox("getValue")==""){  //验证
					$.messager.alert("系统提示","请选择所属班级");
					return false;
				}
				return $(this).form("validate");	//按提交按钮前调用表单验证方法
			},
			success:function(result){
				if(result.errorMsg){	//如果添加失败
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{	//如果添加成功
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");	//重新加载grid//reload：实现刷新当前页的功能
				}
			}
		});
		resetValue();
	}
	
	/*重置对话框内容*/
	function resetValue(){
		$("#couName").val("");
		$("#gradeId").combobox("setValue","");
	}
	
	/*关闭年级课程信息对话框*/
	function closeCourseDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
</script>
</head>
<body style="margin: 5px;">
	<!-- 		表头 -->
	<table id="dg" title="年级课程信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="courseList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="couId" width="50" align="center" hidden="true" >编号</th>
				<th field="gradeId" width="100" align="center" hidden="true" >班级ID</th>
				<th field="gradeName" width="100">班级名称</th>
				<th field="couName" width="250" >班级课程</th>
				
			</tr>
		</thead>
	</table>
	
	<!-- 	增删改查工具栏按钮 -->
	<div id="tb">
		<div>
			<a href="javascript:openCourseAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openCourseModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteCourse()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			<!-- &nbsp;课程：&nbsp; -->
			<!-- <input type="text" name="c_couName" id="c_couName" size="10"/> -->
			&nbsp;所属班级：&nbsp;
			
			<!-- 			关联搜索 -->
			<input class="easyui-combobox" id="c_gradeId" name="c_gradeId" size="10" 
				
			data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'gradeName',url:'gradeComboList'"/>
			<!-- 异步取值 -->
			
			<a href="javascript:searchCourse()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<!-- 对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 350px;height: 200px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="5px;">
			
				<tr>
					<td>班级名称：</td>
					<td><input class="easyui-combobox" id="gradeId" name="gradeId"  data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'gradeName',url:'gradeComboList'"/></td>
			
				</tr>
				<tr>
					<td>年级课程：</td>
					<td><input type="text" name="couName" id="couName" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 对话框按钮 -->
	<div id="dlg-buttons">
		<a href="javascript:saveCourse()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeCourseDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>

</body>
</html>