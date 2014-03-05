<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var studentTopicGrid;
	$(function(){
		studentTopicGrid=$("#studentTopicManager_datagrid");
		studentTopicGrid.datagrid({
			url:'<%=path %>/studentTopicController/findAllTopByStuAdademy',
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
                      {field:'topicPersonId',width:80,align:'center',title:'教师id',hidden:true},
                      {field:'topicMajorNames',width:150,align:'center',title:'适合专业',
                    	  formatter:function(value){
                      	 	 return '<span title="'+value+'">'+value+'<span>';
                     	  }
                      },
                      {field:'topicPersonName',width:80,align:'center',title:'发布教师'},
                      {field:'topicPersonTitle',width:80,align:'center',title:'教师职称'}
                      
                      
                  ]],
             toolbar:'#studentTopicManager_toolbar'
                  
		});
		//搜索框
		$("#searchbox").searchbox({ 
			 menu:"#mm", 
			 prompt :'模糊查询',
		    searcher:function(value,name){   
		    	var str="{\"searchName\":\""+name+"\",\"searchValue\":\""+value+"\"}";
	            var obj = eval('('+str+')');
	            studentTopicGrid.datagrid('reload',obj); 
		    }
		   
		});
	});
	function searchTopDlg(){
		parent.$.modalDialog({
			title:'搜索课题',
			width : 320,
			height : 150,
			href : "pages/topic/searchTopicDlg.jsp",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= studentTopicGrid;
					var f = parent.$.modalDialog.handler.find("#searchTopicDlg_form");
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
	function chooseTopDlg(){
		var row = studentTopicGrid.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title:'确认选题',
				width : 400,
				height : 200,
				href : "pages/topic/chooseTopDlg.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#chooseTopDlg_form");
					f.form("load", row);
				},
				buttons : [ {
					text : '确认选题',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= studentTopicGrid;
						var f = parent.$.modalDialog.handler.find("#chooseTopDlg_form");
						f.submit();
					}
				}, {
					text : '取消选题',
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
				msg : "请选择一条题目信息!",
				timeout : 1000 * 2
			});
		}
		
	}
</script>
<div id="studentTopicManager_toolbar">
	<shiro:hasPermission name="searchTop">
		<input id="searchbox" type="text"/>
	</shiro:hasPermission>
	<shiro:hasPermission name="chooseTop">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="chooseTopDlg()">确认课题</a>
	</shiro:hasPermission>
</div>
<table id="studentTopicManager_datagrid"></table>  
<div id="mm">
	<div name="topicName">课题名称</div>
	<div name="teacherName">发布教师</div>
</div>
<div id="topicEdit"></div>
<div id="addtopic"></div>  
