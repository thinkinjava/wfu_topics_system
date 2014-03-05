<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
.aa {
	margin-top: 15px;
	margin-left: 110px;
}
</style>
<script type="text/javascript">
	$(function(){
		/*form表单提交*/
		$("#adminTopicSearchDlg_form").form({
			url:'<%=path%>/adminTopicController/searchTopic',
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
				$("#adminTopicManager_datagrid").datagrid('loadData', result);
			}
		});
	});
</script>
<form id="adminTopicSearchDlg_form" method="post">
	<table id="searchTopicDlg_table" class="aa">
		<tr>
			<td>教师职称</td>
			<td>&nbsp;&nbsp;&nbsp;<input name="teacherTitle" id="teacherTitle" type="text"
				class="easyui-combobox"
				data-options="valueField:'dicId',textField:'dicName',url:'<%=path%>/dataDictionaryController/findByMark?mark=title',editable:true" /></td>
		</tr>
		<tr>
			<td>课题名称</td>
			<td>&nbsp;&nbsp;&nbsp;<input name="topicName" type="text"
				class="easyui-validatebox" /></td>
		</tr>
		<tr>
			<td>课题类型</td>
			<td>&nbsp;&nbsp;&nbsp;<input name="topicType" id="topicType"
				type="text" class="easyui-combobox"
				data-options="valueField:'dicId',textField:'dicName',url:'<%=path%>/dataDictionaryController/findByMark?mark=topicType',editable:true" />
			</td>
		</tr>
		<tr>
			<td>发布教师</td>
			<td>&nbsp;&nbsp;&nbsp;<input name="topicPersonName" type="text"
				class="easyui-validatebox" /></td>
		</tr>
	</table>

</form>