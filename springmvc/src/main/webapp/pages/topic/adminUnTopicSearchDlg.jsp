<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
.aa{
margin-top: 15px;
margin-left: 110px;
}
</style>
<script type="text/javascript">
	$(function(){
		/*form表单提交*/
		$("#adminUnTopicSearchDlg_form").form({
			url:'<%=path %>/adminTopicController/searchUnTopic',
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
				$("#adminUnTopicManager_datagrid").datagrid('loadData',result);
			}
		});	
	});
</script>
<form id="adminUnTopicSearchDlg_form" method="post">
<table id="searchUnTopicDlg_table" class="aa">
	<tr>
		<td>课题名称</td>
		<td>&nbsp;&nbsp;&nbsp;<input name="topicName" type="text" class="easyui-validatebox" /></td>
	</tr>	
	<tr>
		<td>课题类型</td>
		<td>&nbsp;&nbsp;&nbsp;<input name="topicType" id="topicType" type="text" class="easyui-combobox"    
    			data-options="valueField:'dicId',textField:'dicName',url:'<%=path %>/dataDictionaryController/findByMark?mark=topicType',editable:false" />
			</td>
	</tr>
	<tr>
		<td>发布教师</td>
		<td>&nbsp;&nbsp;&nbsp;<input name="topicPersonName" type="text" class="easyui-validatebox" /></td>
	</tr>
</table>
	
</form>