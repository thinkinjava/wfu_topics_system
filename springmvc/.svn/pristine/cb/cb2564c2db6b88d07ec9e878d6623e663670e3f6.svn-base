<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">

var troomgrid;
$(function(){
	troomgrid=$("#troomManage_datagrid");
	troomgrid.datagrid({
		url:'<%=path %>/troomController/findAlltrooms',
		rownumbers:true,
		fitColumns:true,
		singleSelect:true,
    	fit:true,
    	pagination:true,
    	pageSize:15,
    	pageList:[15,25,35,45],
    	idField:'troomId',
        columns:[[    
				  {field:'troomId',width:100,align:'center',hidden:true,title:'id'},    
				  {field:'ck',checkbox:true},   
                  {field:'troomName',width:100,align:'center',title:'教研室名称'},
                  {field:'academy',width:100,align:'center',title:'隶属院系'},
                  {field:'createTime',width:100,align:'center',title:'创建时间'}
              ]],
         toolbar:'#troomManage_toolbar'
	});
});
	function delTroom(){
	var row = troomgrid.datagrid('getSelected');
	if(row){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	 $.ajax({
			        	url:'<%=path %>/troomController/deleteTroom',
						data:"troomId="+row.troomId,
						dataType:'json',
						success: function(result){
							if (result.status=='true') {
								troomgrid.datagrid('reload');    // 重新载入当前页面数据 
								$.messager.show({
									title : '删除提示',
									msg : result.message,
									timeout : 1000 * 2
								});
							}else{
								$.messager.show({
									title :  '删除提示',
									msg : result.message,
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
//编辑教研室
	function editTroom(){
	var row = troomgrid.datagrid('getSelected');
	if(row){
		parent.$.modalDialog({
			title:'编辑教研室',
			width : 540,
			height : 300,
			href : "pages/troom/editTroomDlg.jsp",
			onLoad:function(){
				var f = parent.$.modalDialog.handler.find("#editTroomDlg_form");
				f.form("load", row);
			},
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= troomgrid;
					var f = parent.$.modalDialog.handler.find("#editTroomDlg_form");
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
//添加教研室
	function addTroom(){
	parent.$.modalDialog({
		title : "添加教研室",
		width : 300,
		height : 150,
		href : "pages/troom/addTroomDlg.jsp",
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				parent.$.modalDialog.openner= troomgrid;
				var f = parent.$.modalDialog.handler.find("#addTroomDlg_form");
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

<div id="troomManage_toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addTroom()">添加教研室</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTroom()">编辑教研室</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delTroom()">删除教研室</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back" plain="true" onclick="reloadDataGrid()">返回列表</a>
</div>
<table id="troomManage_datagrid"></table>  
