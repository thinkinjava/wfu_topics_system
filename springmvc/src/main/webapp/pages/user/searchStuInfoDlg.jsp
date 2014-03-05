<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function(){
		/*form表单提交*/
		$("#searchStuInfoDlg_form").form({
			url :'<%=path%>/userController/searchStuInfo',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
			},
			success : function(result) {
				result = $.parseJSON(result);
				parent.$.messager.progress('close');
				parent.$.modalDialog.handler.dialog('close');
				var str="{\"userName\":\"kkk\"}";
				var obj=eval('('+str+')');
				$("#stuInfManager_datagrid").datagrid('loadData',result);


			}
		});	
	});
</script>
<form id="searchStuInfoDlg_form" method="post">
<table id="searchStuInfoDlg_table">
	<tr>
		<td>学号</td>
		<td><input name="userName" type="text" class="easyui-validatebox" /></td>
	</tr>	
	<tr>
		<td>学生姓名</td>
		<td><input name="stuName" type="text" class="easyui-validatebox" /></td>
	</tr>
</table>
	
</form>