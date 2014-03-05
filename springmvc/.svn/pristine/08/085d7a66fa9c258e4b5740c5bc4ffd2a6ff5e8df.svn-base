<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
%>

<script type="text/javascript">
	var adminTopicGrid;
	$(function(){
		innitDataGrid();
	});
	
	function innitDataGrid()
	{
		adminTopicGrid=$("#adminTopicManager_datagrid");
		adminTopicGrid.datagrid({
			url:'<%=path%>/adminTopicController/findAllTopByAdminAdademy',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
        	fit:true,
        	pagination:true,
        	pageSize:15,
        	pageList:[15,25,35,45],
        	idField:'topicId',
            columns:[[    
                      {field:'topicId',width:100,align:'center',hidden:true,title:'id'},    
                      {field:'ck',checkbox:true},    
                      {field:'topicName',width:150,align:'center',title:'课题名称',                    	  
                    	  formatter:function(value){
                 	 	 return '<span title="'+value+'">'+value+'<span>';
                	  }},
                      {field:'topicType',width:80,align:'center',title:'课题类别'},
                      {field:'topicMajorNames',width:150,align:'center',title:'适合专业',
                    	  formatter:function(value){
                      	 	 return '<span title="'+value+'">'+value+'<span>';
                     	  }
                      },
                      {field:'topicPersonName',width:80,align:'center',title:'发布教师'},
                      {field:'teacherTitle',width:80,align:'center',title:'教师职称'},
                      {field:'topicCreateTime',width:80,align:'center',title:'发布时间'},
                      
                  ]],
             toolbar:'#adminTopicManager_toolbar'
                  
		});
	
		//搜索框
		$("#searchbox").searchbox({ 
			 menu:"#mm", 
			 prompt :'模糊查询',
		     searcher:function(value,name){   
		    	var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
		    	var obj = eval('('+str+')');
		    	$("#adminTopicManager_datagrid").datagrid('reload',obj); 
		    }
		});
	}
	
	//返回列表
	function reloadDataGrid()
	{
		innitDataGrid();
	}
	//编辑课题
	function editTopDlg(){
		var row = adminTopicGrid.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title:'编辑课题',
				width : 500,
				height : 200,
				href : "pages/topic/admintopicEditDlg.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#topicEditDlg_form");
					f.form("load", row);
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= adminTopicGrid;
						var f = parent.$.modalDialog.handler.find("#topicEditDlg_form");
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
	
	
	//导出所有课题
	function exportTopic(){
		$.messager.confirm('确认','您确认想要导出所有发布课题吗？',function(r){    
		    if (r){    
		    	$.ajax({
					dataType: "json", 
					url:'<%=path%>/adminTopicController/exportTopic',
					success: function(result) { 
						if (result.status) {
							window.location.href=result.message;
							$.messager.show({
								title :  '导出提示',
								msg : '导出成功',
								timeout : 1000 * 2
							});
						}else{
							$.messager.show({
								title :  '导出提示',
								msg : '导出失败',
								timeout : 1000 * 2
							});
						}
						}
				});
		    }    
		});
		
	}
	//查看已选课题
	function searchselectedTopic()
	{
		adminTopicGrid.datagrid({
			url:'<%=path%>/adminTopicController/findSelectedByAdminAdademy',
			rownumbers : true,
			fitColumns : true,
			singleSelect : true,
			fit : true,
			pagination : true,
			pageSize : 15,
			pageList : [ 15, 25, 35, 45 ],
			idField : 'topicId',
			columns : [ [ {
				field : 'topicId',
				width : 100,
				align : 'center',
				hidden : true,
				title : 'id'
			}, {
				field : 'ck',
				checkbox : true
			}, {
				field : 'topicName',
				width : 150,
				align : 'center',
				title : '课题名称',
				formatter : function(value) {
					return '<span title="'+value+'">' + value + '<span>';
				}
			}, {
				field : 'topicType',
				width : 80,
				align : 'center',
				title : '课题类型'
			}, {
				field : 'topicMajorNames',
				width : 150,
				align : 'center',
				title : '适合专业',
				formatter : function(value) {
					return '<span title="'+value+'">' + value + '<span>';
				}
			}, {
				field : 'topicPersonName',
				width : 80,
				align : 'center',
				title : '发布教师'
			}, {
				field : 'choosedStudentName',
				width : 80,
				align : 'center',
				title : '学生姓名'
			}, {
				field : 'studentPhone',
				width : 80,
				align : 'center',
				title : '电话'
			}, {
				field : 'studentEmail',
				width : 80,
				align : 'center',
				title : '邮箱',
				formatter : function(value) {
					return '<span title="'+value+'">' + value + '<span>';
				}
			}, ] ],
		});
	}
	//搜索课题
	function searchTopDlg(){
		parent.$.modalDialog({
			title:'搜索课题',
			width : 500,
			height : 200,
			href : "pages/topic/adminTopicSearchDlg.jsp",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= adminTopicManager_datagrid;
					var f = parent.$.modalDialog.handler.find("#adminTopicSearchDlg_form");
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
<div id="adminTopicManager_toolbar">
<!-- 
<input id="searchbox" type="text"/> -->
	
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchTopDlg()">查询统计</a>
	 <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="exportTopic()">导出课题</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="editTopDlg()">编辑课题</a> <a
		href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-back" plain="true" onclick="reloadDataGrid()">返回列表</a> 
</div>
<!-- 
<div id="mm">
	<div name="topicName">课题名称</div>
	<div name="teacherName">发布教师</div>
</div> -->
<table id="adminTopicManager_datagrid"></table>

<div id="topicEdit"></div>
<div id="addtopic"></div>
