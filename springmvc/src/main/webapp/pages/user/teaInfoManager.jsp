<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var teaInfoGrid;
	$(function(){
		teaInfoGrid=$("#teaInfoManager_datagrid");
		teaInfoGrid.datagrid({
			url:'<%=path %>/userController/findAllTeaInfos',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
        	fit:true,
        	pagination:true,
        	pageSize:15,
        	pageList:[15,25,35,45],
        	idField:'teaId',
            columns:[[    
                      {field:'teaId',width:100,align:'center',hidden:true,title:'id'},    
                      {field:'ck',checkbox:true},    
                      {field:'userName',width:100,align:'center',title:'工号'},
                      {field:'teaName',width:80,align:'center',title:'姓名'},
                      {field:'teaTitle',width:80,align:'center',title:'职称'},
                      {field:'teaTroom',width:100,align:'center',title:'教研室',
                    	  formatter:function(value){
                     	 	 return '<span title="'+value+'">'+value+'<span>';
                     	  }
                      },
                      {field:'teaAcademy',width:100,align:'center',title:'院系'}, 
                      {field:'teaEmail',width:100,align:'center',title:'邮箱'},
                      {field:'teaPhone',width:100,align:'center',title:'手机'},
                      {field:'teaSex',width:40,align:'center',title:'性别'},
                      {field:'deptId',width:100,hidden:true},
                      {field:'deptName',width:100,align:'center',title:'部门',
                    	  formatter:function(value){
                    		  if(value==null){
                    			 return '无';
                    		  }else{
                    			  return '<span title="'+value+'">'+value+'<span>';
                    		  }
                      	 	 
                      	  }
                      } ,
                      {field:'roleNames',width:100,align:'center',title:'角色',
                    	  formatter:function(value){
                    	 	 return '<span title="'+value+'">'+value+'<span>';
                    	  }
                      } 
                  ]],
             toolbar:'#teaInfoManager_toolbar'
                  
		});


	});
	//弹窗增加
	function addTeacher() {
		
		parent.$.modalDialog({
			title : "添加教师",
			width : 640,
			height : 400,
			href : "pages/user/teaAddDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= teaInfoGrid;
					var f = parent.$.modalDialog.handler.find("#teaAddDlg_form");
					
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
	
	//poi方式批量导入教师
	function addPoiTeacher(){
		parent.$.modalDialog({
			title : "批量导入教师",
			width : 640,
			height : 400,
			href : "pages/user/teaPoiAddDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= teaInfoGrid;
					var f = parent.$.modalDialog.handler.find("#teaPoiAddDlg_form");
					var file=f.find("input[name='file']");
					if(file.val()==''){//标示没有选中上传文件
						$.messager.show({ 
							title : "提示",
							msg : "请选择上传文件!",
							timeout : 1000 * 2
						});
					}else{
						var isValid = f.form('validate');
						if(isValid){  //只有验证通过才能提交
							f.submit();
						}
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
	//删除教师
	function delTeacher(){
		var row = teaInfoGrid.datagrid('getSelected');
		if(row){
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			    	 $.ajax({
				        	url:'<%=path %>/userController/deleteTeacher',
				        	data: 'teaId='+row.teaId+'&userName='+row.userName,
							success: function(rsp){
								teaInfoGrid.datagrid('load');
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
	//编辑教师
	function editTeacher(){
		var row = teaInfoGrid.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title:'编辑教师',
				width : 540,
				height : 300,
				href : "pages/user/teaEditDlg.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#teaEditDlg_form");
					f.form("load", row);
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= teaInfoGrid;
						var f = parent.$.modalDialog.handler.find("#teaEditDlg_form");
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
	
	function searchTeacher(){
		parent.$.modalDialog({
			title:'搜索教师',
			width : 320,
			height : 150,
			href : "pages/user/searchTeaInfoDlg.jsp",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= teaInfoGrid;
					var f = parent.$.modalDialog.handler.find("#searchTeaInfoDlg_form");
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
	function reloadDataGrid(){
		teaInfoGrid.datagrid('load');    
	}
	//修改密码
	function editPassWord(){
		var row = teaInfoGrid.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title : "修改个人密码",
				width : 450,
				height : 200,
				href : "pages/user/editTeacherPassword.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#editTeacherPassWordDlg_form");
					f.form("load", {userName:row.userName});
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= teaInfoGrid;
						var f = parent.$.modalDialog.handler.find("#editTeacherPassWordDlg_form");
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
<div id="teaInfoManager_toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addTeacher()">添加教师</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addPoiTeacher()">批量导入</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTeacher()">编辑教师</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPassWord()">修改密码</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delTeacher()">删除教师</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchTeacher()">搜索教师</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back" plain="true" onclick="reloadDataGrid()">返回列表</a>
</div>
<table id="teaInfoManager_datagrid"></table>  
<div id="TeacherEdit"></div>
<div id="addTeacher"></div>  
<div id="searchTeacher"></div>
