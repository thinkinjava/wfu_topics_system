<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
/*form表单提交*/
$("#addMajorDlg_form").form({
	url :'<%=path%>/majorController/addMajor',
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
				$("#studentMajorManage_datagrid").datagrid('reload'); 
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
<form id="addMajorDlg_form" method="post">
	<table id="addMajorDlg_table">
		<tr>
			<td>专业名称</td>
			<td><input name="majorName" type="text" /></td>
		</tr>
		<tr>
			<td>隶属院系</td>
			<td><input name="academy" type="text" readOnly="readOnly" ></input> </td>
		</tr>
	</table>
</form>
