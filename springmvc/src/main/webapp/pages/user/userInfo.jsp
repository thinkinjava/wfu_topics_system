<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
%>
<style>
#container {
	margin-left: auto;
	margin-right: auto;
	border: 1pxsolidred;
	width: 300px;
	text-align: left
}
</style>
<script type="text/javascript">
//修改个人密码
function editUserPassword(){
	parent.$.modalDialog({
		title : "修改个人信息",
		width : 540,
		height : 300,
		href : "pages/user/editUserPassword.jsp",
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				var f = parent.$.modalDialog.handler.find("#editUserPassword_form");
					f.submit();
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
//编辑个人信息
	function editUser(){
		parent.$.modalDialog({
			title : "修改个人信息",
			width : 540,
			height : 300,
			href : "pages/user/userInfoEditDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					var f = parent.$.modalDialog.handler.find("#userInfoEditDlg_form");
						f.submit();
					
					
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
	$(function(){
		$.ajax({
			url:'<%=path%>/userController/userInfo',
			success : function(data) {
				var dataObj = eval("(" + data + ")");
				$("#userName").html(dataObj.userName);
				$("#phone").html(dataObj.phone);
				$("#personName").html(dataObj.personName);
				$("#email").html(dataObj.email);
				$("#sex").html(dataObj.sex);
				$("#age").html(dataObj.age);
				$("#deptName").html(dataObj.dept.deptName);

			}
		});
	});
</script>
<div id="container">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		onclick="editUser()" iconCls="icon-edit">编辑个人信息</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton"
		onclick="editUserPassword()" iconCls="icon-edit">修改个人密码</a>
	<table id="userInfo_table">
		<tr>
			<td>用户名</td>
			<td id="userName" />
			<td>电话</td>
			<td id="phone" />
		</tr>
		<tr>
			<td>真实姓名</td>
			<td id="personName" />
			<td>邮箱</td>
			<td id="email" />
		</tr>
		<tr>
			<td>性别</td>
			<td id="sex" />
			<td>年龄</td>
			<td id="age" />
		</tr>
		<tr>
			<td>部门</td>
			<td id="deptName" />
		</tr>
	</table>
</div>
