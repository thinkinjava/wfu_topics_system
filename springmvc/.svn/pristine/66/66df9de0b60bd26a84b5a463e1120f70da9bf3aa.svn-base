<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
	$(function(){
		/*form表单提交*/
		$("#searchTopicDlg_form").form({
			url:'<%=path %>/studentTopicController/searchTopic',
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
				$("#studentTopicManager_datagrid").datagrid('loadData',result);
			}
		});	
	});
</script>
<form id="searchTopicDlg_form" method="post">
<table id="searchTopicDlg_table">
	<tr>
		<td>课题名称</td>
		<td><input name="topicName" type="text" class="easyui-validatebox" /></td>
	</tr>	
	<tr>
		<td>课题类型</td>
		<td>
				<input name="topicType" id="topicType" type="text" class="easyui-combobox"    
    			data-options="valueField:'dicId',textField:'dicName',url:'<%=path %>/dataDictionaryController/findByMark?mark=topicType',editable:false" />
			</td>
	</tr>
	<tr>
		<td>发布教师</td>
		
		<td>
		
		<input name="topicPersonName" type="text" class="easyui-validatebox" /></td>
	</tr>
</table>
	
</form>