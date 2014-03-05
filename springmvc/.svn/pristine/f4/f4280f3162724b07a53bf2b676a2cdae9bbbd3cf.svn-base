<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
/*form表单提交*/
$("#chooseTopDlg_form").form({
	url :'<%=path%>/studentTopicController/saveChooseTopic',
	onSubmit : function() {
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
	},
	success : function(result) {
		parent.$.messager.progress('close');
		result = $.parseJSON(result);
		if (result.status=='true') {
			parent.reload;
			parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为页面预定义好了
			parent.$.modalDialog.handler.dialog('close');
			
			$.messager.show({
				title : '保存提示',
				msg : result.message,
				timeout : 1000 * 2
			});
		}else{
			$.messager.show({
				title :  '保存提示',
				msg : result.message,
				timeout : 1000 * 2
			});
		}
	}
});	
</script>
<form id="chooseTopDlg_form" method="post">
	<table id="chooseTopDlg_table">
		<tr>
			<td>课题名称</td>
			<td><input type="hidden" name="topicId"> <input
				type="text" name="topicName" style="width:250px" readOnly="readOnly" /></td>
		</tr>
		<tr>
			<td>课题类型</td>
			<td><input name="topicType" id="topicType" type="text" readOnly="readOnly" /></td>
		</tr>
		<tr>
			<td>适合专业</td>
			<td><input id="topicMajorNames" name="topicMajorNames" style="width:250px" readOnly="readOnly" /></td>
		</tr>
		<tr>
			<td>发布教师</td>
			<td>
				<input type="hidden" name="topicPersonId"/> 
			    <input id=topicPersonName name="topicPersonName" style="width:150px" readOnly="readOnly" />
			    <input name="topicPersonTitle" type="hidden"/>
			 </td>
		</tr>
	</table>
</form>