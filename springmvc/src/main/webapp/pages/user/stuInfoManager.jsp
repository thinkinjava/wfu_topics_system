<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	var stuInfoGrid;
	$(function(){
		stuInfoGrid=$("#stuInfManager_datagrid");
		stuInfoGrid.datagrid({
			url:'<%=path%>/userController/findAllStuInfs',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
        	fit:true,
        	pagination:true,
        	pageSize:15,
        	pageList:[15,25,35,45],
        	idField:'stutId',
            columns:[[    
                      {field:'stutId',width:50,align:'center',hidden:true,title:'id'},    
                      {field:'ck',checkbox:true},    
                      {field:'userName',width:80,align:'center',title:'学号'},
                      {field:'stuName',width:50,align:'center',title:'学生姓名'},
                      {field:'stuSex',width:50,align:'center',title:'性别'},
                    /*   {field:'stuNation',width:50,align:'center',title:'民族'}, */
                      {field:'stuAcademy',width:100,align:'center',title:'院系'}, 
                      {field:'stuMajor',width:150,align:'center',title:'专业'}, 
                      {field:'stuSubject',width:50,align:'center',title:'本专科'},
                      {field:'stuGrade',width:50,align:'center',title:'入学年级'},
                      {field:'stuClass',width:50,align:'center',title:'班级'},
                    /*   {field:'StuPoliticalLandscape',width:50,align:'center',title:'政治面貌'}, */
                    /*  {field:'stuPost',width:50,align:'center',title:'职务'}, */
                      {field:'stuPhone',width:80,align:'center',title:'手机号码'}
                     /*  {field:'stuEmail',width:100,align:'center',title:'电子邮箱'} */
                  ]],
             toolbar:'#stuInfManager_toolbar'
                  
		});


	});
	//添加学生信息
	function addStuInfo() {
		parent.$.modalDialog({
			title : "添加学生信息",
			width : 540,
			height : 300,
			href : "pages/user/addStuInfoDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= stuInfoGrid;
					var f = parent.$.modalDialog.handler.find("#addStuInfoDlg_form");
					
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
	//删除学生信息
	function delStuInfo(){
	var row = stuInfoGrid.datagrid('getSelected');
	if(row){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	 $.ajax({
			        	url:'<%=path%>/userController/deleteStutInfo',
						data : "stutId=" + row.stutId,
						dataType : 'json',
						success : function(result) {
							if (result.status) {
								majorgrid.datagrid('reload'); // 重新载入当前页面数据 
								$.messager.show({
									title : '删除提示',
									msg : '删除成功',
									timeout : 1000 * 2
								});
							} else {
								$.messager.show({
									title : '删除提示',
									msg : '删除失败',
									timeout : 1000 * 2
								});
							}
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : "提示",
				msg : "请至少选择一条数据删除!",
				timeout : 1000 * 2
			});
		}
	}
	//编辑学生信息
	function editStuInfo() {
		var row = stuInfoGrid.datagrid('getSelected');
		if (row) {
			parent.$.modalDialog({
				title : '编辑学生信息',
				width : 540,
				height : 300,
				href : "pages/user/editStuInfoDlg.jsp",
				onLoad : function() {
					var f = parent.$.modalDialog.handler
							.find("#editStuInfoDlg_form");
					f.form("load", row);
				},
				buttons : [
						{
							text : '保存',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner = stuInfoGrid;
								var f = parent.$.modalDialog.handler
										.find("#editStuInfoDlg_form");
								f.submit();
							}
						}, {
							text : '取消',
							iconCls : 'icon-cancel',
							handler : function() {
								parent.$.modalDialog.handler.dialog('destroy');
								parent.$.modalDialog.handler = undefined;
							}
						} ]
			});
		} else {
			$.messager.show({
				title : "提示",
				msg : "请选择一条数据编辑!",
				timeout : 1000 * 2
			});
		}
	}
	//搜索用户
	function searchStuInfo() {
		parent.$.modalDialog({
			title : '搜索用户',
			width : 320,
			height : 150,
			href : "pages/user/searchStuInfoDlg.jsp",
			buttons : [
					{
						text : '确定',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner = stuInfoGrid;
							var f = parent.$.modalDialog.handler
									.find("#searchStuInfoDlg_form");
							var isValid = f.form('validate');
							if (isValid) { //只有验证通过才能提交
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
					} ]
		});
	}
	function reloadDataGrid() {
		stuInfoGrid.datagrid('load');
	}
	function addPoiStudent() {
		parent.$.modalDialog({
			title : "批量导入学生",
			width : 640,
			height : 400,
			href : "pages/user/studentPoiAddDlg.jsp",
			buttons : [
					{
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner = stuInfoGrid;
							var f = parent.$.modalDialog.handler
									.find("#studentPoiAddDlg_form");
							var file = f.find("input[name='file']");
							if (file.val() == '') {//标示没有选中上传文件
								$.messager.show({
									title : "提示",
									msg : "请选择上传文件!",
									timeout : 1000 * 2
								});
							} else {
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
					} ]
		});
	}
	//修改密码
	function editPassWord(){
		var row = stuInfoGrid.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title : "修改个人密码",
				width : 450,
				height : 200,
				href : "pages/user/editStudentPassword.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#editStudentPassWordDlg_form");
					f.form("load", {userName:row.userName});
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= stuInfoGrid;
						var f = parent.$.modalDialog.handler.find("#editStudentPassWordDlg_form");
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
<div id="stuInfManager_toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-add" plain="true" onclick="addStuInfo()">添加学生信息</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="editStuInfo()">编辑学生信息</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-remove" plain="true" onclick="delStuInfo()">删除学生信息</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="editPassWord()">修改密码</a> <a
		href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"
		plain="true" onclick="addPoiStudent()">批量导入</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-search" plain="true" onclick="searchStuInfo()">搜索用户</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-back" plain="true" onclick="reloadDataGrid()">返回列表</a>
</div>
<table id="stuInfManager_datagrid"></table>
