<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var studentChoosedTopicGrid;
	$(function(){
		studentChoosedTopicGrid=$("#studentChoosedTopicManager_datagrid");
		studentChoosedTopicGrid.datagrid({
			url:'<%=path %>/studentTopicController/findAllChoosedTopic',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
        	fit:true,
        	pagination:true,
        	pageSize:15,
        	pageList:[15,25,35,45],
        	idField:'choosedId',
            columns:[[    
                      {field:'choosedId',width:100,align:'center',hidden:true,title:'id'},    
                      {field:'topicName',width:150,align:'center',title:'课题名称'},
                      {field:'topicType',width:80,align:'center',title:'课题类型'},
                      {field:'topicPersonName',width:80,align:'center',title:'发布教师'},
                      {field:'teaPhone',width:80,align:'center',title:'教师电话'},
                      {field:'teaEmail',width:80,align:'center',title:'教师邮箱'},
                  ]],
		});
	});
</script>
<table id="studentChoosedTopicManager_datagrid"></table>  
