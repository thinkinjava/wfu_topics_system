<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<style>
#roleAddDlg_table{padding-left:20px;font-size: 12px;}
</style>
<script type="text/javascript">
	$(function(){
		/*form表单提交*/
		$("#roleAddDlg_form").form({
			url :'<%=path%>/roleController/saveOrUpdaterole',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});

			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.status) {
					parent.reload;
					parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					$.messager.show({
						title : '保存提示',
						msg : '保存成功',
						timeout : 1000 * 2
					});
				}else{
					$.messager.show({
						title :  '保存提示',
						msg : '保存失败',
						timeout : 1000 * 2
					});
				}
			}
		});	
		
	});
</script>
<form id="roleAddDlg_form" method="post">
<table id="roleAddDlg_table">
	<tr>
		<td>角色名</td>
		<td><input name="roleName" type="text" class="easyui-validatebox" data-options="required:true"/></td>
	</tr>
	<tr>
		<td>排序</td>
		<td><input name="theorder" type="number" class="easyui-numberbox" data-options="required:true"/></td>
	</tr>
	<tr>
		<td>描述</td>
		<td><textarea name="description"></textarea></td>
	</tr>
</table>
</form>
