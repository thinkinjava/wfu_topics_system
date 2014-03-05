<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var ugrid;
	$(function(){
		ugrid=$("#userManager_datagrid");
		ugrid.datagrid({
			url:'<%=path %>/userController/findAllUsers',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
        	fit:true,
        	pagination:true,
        	pageSize:15,
        	pageList:[15,25,35,45],
        	idField:'userId',
            columns:[[    
                      {field:'userId',width:100,align:'center',hidden:true,title:'id'},    
                      {field:'ck',checkbox:true},    
                      {field:'userName',width:100,align:'center',title:'用户名'},
                      {field:'personName',width:100,align:'center',title:'姓名'},
                      {field:'passWord',width:100,align:'center',title:'密码'},
                      {field:'age',width:50,align:'center',title:'年龄'}, 
                      {field:'sex',width:50,align:'center',title:'性别'},
                      {field:'email',width:100,align:'center',title:'邮箱'},
                      {field:'phone',width:100,align:'center',title:'电话'},
                      {field:'deptId',width:100,hidden:true},
                      {field:'deptName',width:100,align:'center',title:'部门'} ,
                      {field:'roleNames',width:100,align:'center',title:'角色',
                    	  formatter:function(value){
                    	 	 return '<span title="'+value+'">'+value+'<span>';
                    	  }
                      } 
                  ]],
             toolbar:'#userManager_toolbar'
                  
		});


	});
	//弹窗增加
	function addRowsOpenDlg() {
		parent.$.modalDialog({
			title : "添加用户",
			width : 540,
			height : 300,
			href : "pages/user/userAddDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= ugrid;
					var f = parent.$.modalDialog.handler.find("#userAddDlg_form");
					
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
	//删除用户
	function delUser(){
		var row = ugrid.datagrid('getSelections');
		if(row.length!=0){
			var ids=[];
			for(var o in row){
				ids.push(row[o].userId);
			}
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			    	 $.ajax({
				        	url:'<%=path %>/userController/deleteUsers',
				        	data: {
				        		ids:ids.join(",")
				        	},
							success: function(rsp){
								for(var i in row){
								   var rowIndex = ugrid.datagrid('getRowIndex', row[i]);
								   ugrid.datagrid('deleteRow',rowIndex);    // 删除该条记录，最好不要reload  
								   ugrid.datagrid('load');
								}
								$.messager.show({ 
									title : "提示",
									msg : "删除成功!",
									timeout : 1000 * 2
								});
							}
				        }); 
			    }    
			});
		}else{
			$.messager.show({
				title : "提示",
				msg : "请至少选择一条数据删除!",
				timeout : 1000 * 2
			});
		}
	}
	//编辑用户
	function editUser(){
		var row = ugrid.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title:'编辑用户',
				width : 540,
				height : 300,
				href : "pages/user/userEditDlg.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#userEditDlg_form");
					f.form("load", row);
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= ugrid;
						var f = parent.$.modalDialog.handler.find("#userEditDlg_form");
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
		}else{
			$.messager.show({
				title : "提示",
				msg : "请选择一条数据编辑!",
				timeout : 1000 * 2
			});
		}
		
	}
	
	function searchUser(){
		parent.$.modalDialog({
			title:'搜索用户',
			width : 320,
			height : 150,
			href : "pages/user/userSearchDlg.jsp",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= ugrid;
					var f = parent.$.modalDialog.handler.find("#userSearchDlg_form");
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
	function reloadDataGrid(){
		ugrid.datagrid('load');    
	}
	//修改密码
	function editPassWord(){
		var row = ugrid.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title:'用户密码修改',
				width : 290,
				height : 200,
				href : "pages/user/userEditPassWordDlg.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#userEditPassWordDlg_form");
					f.form("load", {
						userId:row.userId,
						userName:row.userName,
						personName:row.personName
					});
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= ugrid;
						var f = parent.$.modalDialog.handler.find("#userEditPassWordDlg_form");
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
		}else{
			$.messager.show({
				title : "提示",
				msg : "请选择一条数据修改密码!",
				timeout : 1000 * 2
			});
		}
	}
</script>
<div id="userManager_toolbar">
	<shiro:hasPermission name="userAdd">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg()">添加用户</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="userEdit">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPassWord()">修改密码</a>
	</shiro:hasPermission>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delUser()">删除用户</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchUser()">搜索用户</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back" plain="true" onclick="reloadDataGrid()">返回列表</a>
</div>
<table id="userManager_datagrid"></table>  
<div id="userEdit"></div>
<div id="addUser"></div>  
<div id="searchUser"></div>
