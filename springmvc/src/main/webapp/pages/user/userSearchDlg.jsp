<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<style>
#userAddDlg_table{padding-left:20px;font-size: 12px;}
</style>
<script type="text/javascript">
	$(function(){
		/*form表单提交*/
		$("#userSearchDlg_form").form({
			url :'<%=path%>/userController/searchUser',
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
				$("#userManager_datagrid").datagrid('loadData',result);


			}
		});	
	});
</script>
<form id="userSearchDlg_form" method="post">
<table id="userSearchDlg_table">
	<tr>
		<td>用户名</td>
		<td><input name="userName" type="text" class="easyui-validatebox" data-options="required:true"/></td>
	</tr>	
</table>
	
</form>
