<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<style>
.aa {
	margin-top: 50px;
}
</style>

</head>

<body>
	<table id="topicAddDlg_tablse" class="aa">
		<tr>
			<td>课题名称</td>
			<td><input type="text" name="topicName" id="topicName"
				class="easyui-validatebox" data-options="required:true"
				style="width:300px" /></td>
		</tr>
		<tr>
			<td>课题类型</td>
			<td><input name="topicType" id="topicType" type="text"
				class="easyui-combobox"
				data-options="valueField:'dicId',textField:'dicName',url:'<%=path%>/dataDictionaryController/findByMark?mark=topicType',required:true,editable:false" />
			</td>
		</tr>
		<tr>
			<td>适合专业（可多选）</td>
			<td><input type="hidden" name="topicMajorNames"
				id="topicMajorNames" /> <input class="easyui-combobox"
				id="topicMajorIds" name="topicMajorIds"
				data-options="
                    url:'<%=path%>/majorController/findMajorsByAcademy',
                    method:'get',
                    valueField:'majorId',
                    textField:'majorName',
                    multiple:true,
                    panelHeight:'auto',
                    required:true,editable:false"
				style="width:200px" /></td>
		</tr>
	</table>
</body>
</html>
