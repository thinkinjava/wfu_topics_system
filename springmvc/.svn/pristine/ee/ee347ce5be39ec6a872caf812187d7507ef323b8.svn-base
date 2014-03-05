<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">

var majorgrid;
$(function(){
	majorgrid=$("#studentMajorManage_datagrid");
	majorgrid.datagrid({
		url:'<%=path %>/majorController/findAllMajors',
		rownumbers:true,
		fitColumns:true,
		singleSelect:true,
    	fit:true,
    	pagination:true,
    	pageSize:15,
    	pageList:[15,25,35,45],
    	idField:'majorId',
        columns:[[    
				  {field:'majorId',width:100,align:'center',hidden:true,title:'id'},    
				  {field:'ck',checkbox:true},   
                  {field:'majorName',width:100,align:'center',title:'专业名'},
                  {field:'academy',width:100,align:'center',title:'隶属院系'},
                  {field:'createTime',width:100,align:'center',title:'创建时间'}
              ]],
         toolbar:'#studentMajorManage_toolbar'
	});
});
function delUser(){
	var row = majorgrid.datagrid('getSelected');
	if(row){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	 $.ajax({
			        	url:'<%=path %>/majorController/deleteMajor',
						data:"majorId="+row.majorId,
						dataType:'json',
						success: function(result){
							if (result.status) {
								majorgrid.datagrid('reload');    // 重新载入当前页面数据 
								$.messager.show({
									title : '删除提示',
									msg : '删除成功',
									timeout : 1000 * 2
								});
							}else{
								$.messager.show({
									title :  '删除提示',
									msg : '删除失败',
									timeout : 1000 * 2
								});
							}
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
//编辑专业
function editUser(){
	var row = majorgrid.datagrid('getSelected');
	if(row){
		parent.$.modalDialog({
			title:'编辑专业',
			width : 540,
			height : 300,
			href : "pages/major/editMajorDlg.jsp",
			onLoad:function(){
				var f = parent.$.modalDialog.handler.find("#editMajorDlg_form");
				f.form("load", row);
			},
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= majorgrid;
					var f = parent.$.modalDialog.handler.find("#editMajorDlg_form");
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
	}else{
		$.messager.show({
			title : "提示",
			msg : "请选择一条数据编辑!",
			timeout : 1000 * 2
		});
	}
	
}
function addMajor(){
	parent.$.modalDialog({
		title : "添加专业",
		width : 300,
		height : 150,
		href : "pages/major/addMajorDlg.jsp",
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				parent.$.modalDialog.openner= majorgrid;
				var f = parent.$.modalDialog.handler.find("#addMajorDlg_form");
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


</script>

<div id="studentMajorManage_toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addMajor()">添加专业</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑专业</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delUser()">删除专业</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back" plain="true" onclick="reloadDataGrid()">返回列表</a>
</div>
<table id="studentMajorManage_datagrid"></table>  
<div id="studentMajorEdit"></div>
<div id="addstudentMajor"></div>  
<div id="searchstudentMajor"></div>
