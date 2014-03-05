<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
%>

<script type="text/javascript">
	var viewSelectedTopicsGrid;
	//查看已选课题
	$(function(){
		searchselectedTopic();
	});
	function searchselectedTopic()
	{
		viewSelectedTopicsGrid=$("#viewSelectedTopics_datagrid");
		viewSelectedTopicsGrid.datagrid({
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
			}
			] ],
		});
	}
</script>
<table id="viewSelectedTopics_datagrid"></table>
<div id="topicEdit"></div>
<div id="addtopic"></div>

