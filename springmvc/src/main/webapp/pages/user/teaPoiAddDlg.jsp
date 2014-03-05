<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<style>
#teaPoiAddDlg_table{padding-left:20px;font-size: 12px;}
</style>
<script type="text/javascript">
		/*form表单提交*/
		$("#teaPoiAddDlg_form").form({
			url :'<%=path%>/userController/importTeacherInfos',
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
</script>
<form id="teaPoiAddDlg_form" method="post" enctype="multipart/form-data">
<table id="teaPoiAddDlg_table">
	<tr>
		<td>模板下载</td>
		<td><a href="<%=path%>/file/teacherInfo.xls">下载</a></td>
	</tr>
	<tr>
		<td>请选择需导入的excel文件</td>
		<td><input type="file" name="file" /></td>
	</tr>
</table>
</form>
