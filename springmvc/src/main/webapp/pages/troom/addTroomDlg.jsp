<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
/*form表单提交*/
$("#addTroomDlg_form").form({
	url :'<%=path%>/troomController/addTroom',
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
				$("#troomManage_datagrid").datagrid('reload'); 
				parent.$.modalDialog.handler.dialog('close');
				$.messager.show({
					title : '保存提示',
					msg : '保存成功',
					timeout : 1000 * 2
				});
			} else {
				$.messager.show({
					title : '保存提示',
					msg : '保存失败',
					timeout : 1000 * 2
				});
			}
		}
	});
$(function(){
	$.ajax({
		url:'<%=path%>/userController/findAcademy',
		success : function(data) {
			var dataObj = eval("(" + data + ")");
			$("input[name='academy']").val(dataObj.academyName);
		}
	});
});
</script>
<form id="addTroomDlg_form" method="post">
	<table id="addTroomDlg_table">
		<tr>
			<td>教研室名称</td>
			<td><input name="troomName" type="text" /></td>
		</tr>
		<tr>
			<td>隶属院系</td>
			<td><input name="academy" type="text" readOnly="readOnly" ></input> </td>
		</tr>
	</table>
</form>
