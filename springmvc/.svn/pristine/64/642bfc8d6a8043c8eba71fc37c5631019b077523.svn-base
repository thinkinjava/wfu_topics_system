<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var adminTopicGrid;
	$(function(){
		adminTopicGrid=$("#adminTopicManager_datagrid");
		adminTopicGrid.datagrid({
			url:'<%=path %>/teaTopicController/findAllTopByOwn',
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
                      {field:'isSelect',width:30,align:'center',title:'是否已选',
                    	  formatter:function(value){
                       	 	// return '<span title="'+value+'">'+value+'<span>';
                       	 	if(value=='0'){
                       	 		return '未选';
                       	 	}else if(value=='1'){
                       	 		return '已选';
                       	 	}else if(value=='2'){
                       	 		return '不可见';
                       	 	}
                      	  }
                      }
                  ]],
             toolbar:'#admintopicManager_toolbar'
                  
		});


	});
	//弹窗增加
	function addTopDlg() {
		parent.$.modalDialog({
			title : "添加课题",
			width : 450,
			height : 200,
			href : "pages/topic/topicAddDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= teaTopicGrid;
					var f = parent.$.modalDialog.handler.find("#topicAddDlg_form");
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

	//编辑课题
	function editTopDlg(){
		var row = teaTopicGrid.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title:'编辑课题',
				width : 450,
				height : 200,
				href : "pages/topic/topicEditDlg.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#topicEditDlg_form");
					f.form("load", row);
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= teaTopicGrid;
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
	
	function searchTopDlg(){
		parent.$.modalDialog({
			title:'搜索课题',
			width : 320,
			height : 150,
			href : "pages/topic/adminTopicSearchDlg.jsp",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= teaTopicGrid;
					var f = parent.$.modalDialog.handler.find("#adminTopicSearchDlg_form");
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
		teaTopicGrid.datagrid('load');    
	}

</script>
<div id="admintopicManager_toolbar">
	<shiro:hasPermission name="topicsearch">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchTopDlg()">搜索课题</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="topicEdit">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTopDlg()">编辑课题</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="topicsearch">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchTopiced()">已选课题</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchUnTopic()">已选课题</a>
	</shiro:hasPermission>
</div>
<table id="adminTopicManager_datagrid"></table>  
<div id="topicEdit"></div>
<div id="addtopic"></div>  
