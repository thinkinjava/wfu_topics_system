<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var teaTopicGrid;
	$(function(){
		teaTopicGrid=$("#topicManager_datagrid");
		teaTopicGrid.datagrid({
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
             toolbar:'#topicManager_toolbar'
                  
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
	//删除课题
	function delTopic(){
		var row = teaTopicGrid.datagrid('getSelected');
		if(row){
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			    	 $.ajax({
				        	url:'<%=path %>/teaTopicController/deletetopic',
				        	data: {
				        		topicId:row.topicId
				        	},
							success: function(rsp){
								teaTopicGrid.datagrid('load');
								var result = $.parseJSON(rsp);
								if(result.status){
									$.messager.show({
										title : "提示",
										msg : "删除成功!",
										timeout : 1000 * 2
									});
								}else{
									$.messager.show({
										title : "提示",
										msg : "删除失败!",
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
	
	function searchtopic(){
		parent.$.modalDialog({
			title:'搜索课题',
			width : 320,
			height : 150,
			href : "pages/topic/topicSearchDlg.jsp",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= teaTopicGrid;
					var f = parent.$.modalDialog.handler.find("#topicSearchDlg_form");
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
<div id="topicManager_toolbar">
	<shiro:hasPermission name="topicAdd">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addTopDlg()">添加课题</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="topicEdit">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTopDlg()">编辑课题</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="topicDelete">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delTopic()">删除课题</a>
	</shiro:hasPermission>
</div>
<table id="topicManager_datagrid"></table>  
<div id="topicEdit"></div>
<div id="addtopic"></div>  
