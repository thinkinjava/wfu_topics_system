<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎使用计算机工程学院管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path%>/style/common.css">   
	<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/default/easyui.css">   
	<link rel="stylesheet" type="text/css" href="<%=path%>/js/easyui/themes/icon.css">   
	<script type="text/javascript" src="<%=path %>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/easyui/jquery.easyui.min.js"></script> 
	<script type="text/javascript" src="<%=path%>/js/jqueryUtil.js"></script>
	<script type="text/javascript" src="<%=path%>/js/easyui/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" >
	$(function(){
		
		//初始化专业下拉列表
		
		
		var type;
		initMenu();
		//获取登录的用户信息
		$.ajax({
			url:'<%=path%>/userController/userInfo',
			dataType:'json',
			success:function(data){
				if(data.userType=='1'){
					type="教师";
				}else if(data.userType=='2'){
					type="同学";
				}
				$('#index_current').html("欢迎您："+data.userName+data.personName+type);
			}
		});
			/*if (jqueryUtil.isLessThanIe8()) {
				$.messager.show({
					title : '警告',
					msg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',
					timeout : 1000 * 30
				});
			}*/
		$('#logout').click(function(){
			$.ajax({
				url:'<%=path%>/loginController/logout',
				success:function(){
					window.location.href='login.jsp';
				}
			});
		});
		$("#viewUserInfo").click(function(){
				if(type=="教师"){
					parent.$.modalDialog({
						title : "教师信息详情",
						width : 450,
						height : 200,
						href : "pages/user/viewTeaInfoDlg.jsp",
					});
				}else if(type=="同学"){
					parent.$.modalDialog({
						title : "学生信息详情",
						width : 450,
						height : 200,
						href : "pages/user/viewStuInfoDlg.jsp",
					});
				}
			});
		$("#modifyPassword").click(function(){
			parent.$.modalDialog({
				title : "修改个人密码",
				width : 280,
				height : 250,
				href : "pages/user/editUserPassword.jsp",
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						var f = parent.$.modalDialog.handler.find("#editUserPassword_form");
						var isValid = f.form('validate');
						if(isValid){  //只有验证通过才能提交
							f.submit();
						}
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				}
				]
			});
		});
		
		$("#editUserInfo").click(function(){
			if(type=="教师"){
				parent.$.modalDialog({
					title : "修改个人信息",
					width : 450,
					height :200,
					href : "pages/user/teaInfoEditDlg.jsp",
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							var f = parent.$.modalDialog.handler.find("#teaInfoEditDlg_form");
							var isValid = f.form('validate');
							if(isValid){  //只有验证通过才能提交
								f.submit();
							}
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}
					]
				});
			}else
			{
				parent.$.modalDialog({
					title : "修改个人信息",
					width : 450,
					height : 200,
					href : "pages/user/stuInfoEditDlg.jsp",
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							var f = parent.$.modalDialog.handler.find("#stuInfoEditDlg_form");
							var isValid = f.form('validate');
							if(isValid){  //只有验证通过才能提交
								f.submit();
							}
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}
					]
				});
			}
			
		});
		});	
	
	
		function initMenu(){
			var $ma=$("#menuAccordion");
			$ma.accordion({animate:true,fit:true,border:false});
			$.post('<%=path%>/userController/findAllMenu', function(rsp) {
				$.each(rsp,function(i,e){
					var menulist ="<div class=\"well well-small\">";
					if(e.child && e.child.length>0){
						$.each(e.child,function(ci,ce){
							var effort=ce.name+"||"+ce.iconCls+"||"+"<%=path%>"+ce.url;
							menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"+ce.iconCls+"'\" onclick=\"addTab('"+effort+"');\">"+ce.name+"</a><br/>";
						});
					}
					menulist+="</div>";
					$ma.accordion('add', {
			            title: e.name,
			            content: menulist,
						border:false,
			            iconCls: e.iconCls,
			            selected: false
			        });
					$ma.accordion('select', 0);//选择第一个
				});
			}, "JSON").error(function() {
				$.messager.alert("提示", "获取菜单出错,请重新登陆!");
			});
		}
		
		function addTab(a){
			//str[0]为标题，str[1]为iconCls，str[2]为url
			var str=a.split("||");
			var t=$("#index_contentTab");
			if(t.tabs('exists',str[0])){
				t.tabs('close',str[0]);
				t.tabs('add',{
					title:str[0],
					closable:true,
					href:str[2]
				});
			}else{
				t.tabs('add',{
					title:str[0],
					closable:true,
					href:str[2]
				});
			}

		}
	</script>
  </head>
  
  <body>
	 <div id="cc" class="easyui-layout" data-options="fit:true">   
		    <div data-options="region:'north',split:true" style="height:66px; ">
		    	<div id="index_top">
		    		<div id="index_top_logo">
		    			<img alt="潍坊学院" src="<%=path %>/images/wfu_logo.png" id="index_logo_wfu">
		    			<span id="index_logo_zi_wfu">计算机工程学院管理系统</span>
		    		</div>
		    		<div id="index_top_msg">
		    			<span id="index_current"></span>
		    			<a href="javascript:void(0)" id="viewUserInfo" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看个人信息</a>
						<a href="javascript:void(0)" id="editUserInfo" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改个人信息</a>
						<a href="javascript:void(0)" id="modifyPassword" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>
						<a href="javascript:void(0)" id="logout" class="easyui-linkbutton" data-options="iconCls:'icon-no'">注销登录</a>

				    </div>
			    </div>
		    	<!--  -->
		    </div>   
			<div data-options="region:'west',title:'菜单管理',split:true" style="width:200px;">
				<div id="menuAccordion"></div>
		    </div>   
		    <div data-options="region:'center',title:'内容主体'" style="padding:5px;background:#eee;" >
		    	<div id="index_contentTab" class="easyui-tabs" data-options="fit:true">    
				    <div title="首页">
				    	<span>欢迎使用计算机工程学院管理系统</span>
				    </div>
				</div>  
		    </div>   
		</div> 
  </body>
</html>
