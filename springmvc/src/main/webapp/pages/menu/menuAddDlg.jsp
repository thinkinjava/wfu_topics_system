<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function(){
		$("#menuAddDlg_form").form({
			url :'<%=path%>/menuController/saveOrUpdateMenu',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});

			},
			success : function(result) {
				parent.$.messager.progress('close');
				$.messager.show({
					title : '提示信息',
					msg : '添加主菜单成功',
					timeout : 1000 * 2
				});
				parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为页面预定义好了
				parent.$.modalDialog.handler.dialog('close');
				$("#menuManager_table").treegrid('reload');	// 重新载入所有行
			}
		});
	});
</script>

<form id="menuAddDlg_form" method="post">
	<table>
		<tr>
			<td>菜单名称</td>
			<td>
				<input name="menuName" type="text" class="easyui-validatebox" data-options="required:true"/>
				<input name="type" type="hidden" value="菜单">
			</td>
		</tr>
		<tr>
			<td>排序</td>
			<td><input name="num" type="text"></td>
		</tr>
		<tr>
			<td>描述</td>
			<td><textarea name="description"></textarea></td>
		</tr>
	</table>
</form>