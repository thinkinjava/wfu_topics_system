<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
/*form表单提交*/
$("#editTroomDlg_form").form({
	url :'<%=path%>/troomController/editTroomInfo',
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
</script>
<form id="editTroomDlg_form" method="post">
<input name="troomId" type="hidden" />
	<table id="editTroomDlg_table">
		<tr>
			<td>教研室名称</td>
			<td><input name="troomName" type="text" /></td>
		</tr>
		<tr>
			<td>隶属院系</td>
			<td><input name="academy" type="text" readOnly="readOnly" /></td>
		</tr>
	</table>
</form>
