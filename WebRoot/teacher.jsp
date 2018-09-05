<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理系统主界面</title>
<%
	// 权限验证
	if(session.getAttribute("currentUser")==null){
		System.out.println("请回到登陆界面");
		response.sendRedirect("index.jsp");
		return;
	}
%>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function(){
		// 数据
		var treeData=[{
			text:"教师信息",
			children:[{
				text:"教师个人信息",
				attributes:{
					url:"teacherNote.jsp"
				}
			}]
				
			},{
				text:"成绩信息管理",
				children:[{
				text:"成绩列表",
				attributes:{
					url:"scoreInfoManage.jsp"
				}
			}]
		
				
		
				
		}];
		
		// 实例化树菜单
		$("#tree").tree({
			data:treeData,
			lines:true,  //树形虚线
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);	//增加导航菜单点击事件动作
				}
			}
		});
		
		// 新增Tab
		function openTab(text,url){
			//判断打开是的窗口是否有重复的
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs('select',text);	//如果存在，自动选中现已经存在的窗口
			}else{
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
													//auto 在需要的情况下出现滚动条(默认值)。
				$("#tabs").tabs('add',{
					title:text,
					closable:true,    //窗口可关闭选项
					content:content
				});
			}
		}
	});
</script>
<style type="text/css">
	.north01{	float:left;
				width:100px;
				height:80px;
	}
</style>
</head>
<body class="easyui-layout">

	<div region="north" style="height: 80px;background-color: #E0EDFF;border: 0px;">
		<div align="left" style="width: 80%;float: left"><img src="images/main.png"></div>
		<div style="padding-top: 50px;padding-right: 20px;">当前用户：&nbsp;<font color="red" >${currentUser.userName }</font></div>
	</div>
	
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页">
				<div align="center" style="padding-top: 100px;">
					<font color="blue" size="10">欢迎使用</font>
				</div>
			</div>
		</div>
	</div>
		
	<div region="west" style="width: 150px;" title="导航菜单" split="true">   
		<ul id="tree"></ul>
	</div>
	
	<div region="south" style="height: 25px;border: 0px;" align="center">@版权所有www.cqnu5070.com</div>
</body>
</html>