<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function(){
		$("#deptChildAddDlg_form").form({
			url :'<%=path%>/deptController/saveOrUpdateDept',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});

			},
			success : function(result) {
				parent.$.messager.progress('close');
				if(result.status){
					$.messager.show({
						title : '提示信息',
						msg : '添加主菜单成功',
						timeout : 1000 * 2
					});
				}
				
				parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为页面预定义好了
				parent.$.modalDialog.handler.dialog('close');
				$("#deptManager_table").treegrid('reload');	// 重新载入所有行
			}
		});
	});
</script>

<form id="deptChildAddDlg_form" method="post">
	<table>
	   <tr>
			<td>父级部门</td>
			<td>
				<input name="pName" type="text" readonly="readonly"/>
				<input name="parentId" type="hidden"/>
			</td>
		</tr>
		<tr>
			<td>部门名称</td>
			<td>
				<input name="deptName" type="text" class="easyui-validatebox" data-options="required:true"/>
			</td>
		</tr>
		<tr>
			<td>排序</td>
			<td>
				<input name="theOrder" type="number" class="easyui-numberbox" data-options="required:true"/>(数字格式)
			</td>
		</tr>
		<tr>
			<td>备注</td>
			<td><textarea name="description"></textarea></td>
		</tr>
	</table>
</form>