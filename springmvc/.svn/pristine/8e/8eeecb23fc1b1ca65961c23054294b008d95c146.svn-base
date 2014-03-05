<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>

<script type="text/javascript">
	var adminUnTopicGrid;
	$(function(){
		adminUnTopicGrid=$("#adminUnTopicManager_datagrid");
		adminUnTopicGrid.datagrid({
			url:'<%=path %>/adminTopicController/findUnTopByAdminAdademy',
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
                      {field:'topicType',width:80,align:'center',title:'课题类型'},
                      {field:'topicMajorNames',width:150,align:'center',title:'适合专业',
                    	  formatter:function(value){
                      	 	 return '<span title="'+value+'">'+value+'<span>';
                     	  }
                      },
                      {field:'topicPersonName',width:80,align:'center',title:'发布教师'},
                      {field:'topicCreateTime',width:80,align:'center',title:'发布时间'},
                  ]],
             toolbar:'#adminUnTopicManager_toolbar'
                  
		});
	});
	//搜索课题
	function searchTopDlg(){
		parent.$.modalDialog({
			title:'搜索课题',
			width : 500,
			height : 200,
			href : "pages/topic/adminUnTopicSearchDlg.jsp",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= adminUnTopicManager_datagrid;
					var f = parent.$.modalDialog.handler.find("#adminUnTopicSearchDlg_form");
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
	//返回列表
	function reloadDataGrid()
	{
		adminUnTopicGrid.datagrid("load");
	}
</script>
<div id="adminUnTopicManager_toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchTopDlg()">搜索课题</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back" plain="true" onclick="reloadDataGrid()">返回列表</a>
</div>
<table id="adminUnTopicManager_datagrid"></table>  
<div id="topicEdit"></div>
<div id="addtopic"></div>  
