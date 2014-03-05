<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<style>
.aa{
margin-top: 10px;
margin-left: 10px;
}
</style>
<script type="text/javascript">
$(function(){
	$('#topicName').blur(function(){
		$.ajax({
			url:'<%=path%>/teaTopicController/similarityTopicName',
			data:'topicName='+$(this).val(),
			dataType:'json',
			success:function(r){
				if(r.status=='true'){
					alert("系统中存在与该课题相似度超过90%的课题，请选择其他课题");
					$('#topicName').val("");
				}
			}
		});
	});
});
/*form表单提交*/
$("#topicAddDlg_form").form({
	url :'<%=path%>/teaTopicController/saveTopic',
	onSubmit : function() {
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		var aa=$("#topicMajorIds").combobox("getText");
		$("#topicMajorNames").val(aa);
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

<form id="topicAddDlg_form" method="post">
	 <table id="topicAddDlg_tablse" class="aa">
		<tr >
			<td >课题名称</td>
			<td><input type="text" name="topicName" id="topicName" class="easyui-validatebox" data-options="required:true" style="width:250px"/></td>
		</tr>
		<tr>
			<td>课题类型</td>
			<td>
				<input name="topicType" id="topicType" type="text" class="easyui-combobox"    
    			data-options="valueField:'dicId',textField:'dicName',url:'<%=path %>/dataDictionaryController/findByMark?mark=topicType',required:true,editable:false" />
			</td>
		</tr>
		<tr>
			<td>适合专业（可多选）</td>
			<td>
				<input type="hidden" name="topicMajorNames" id="topicMajorNames"/>
			    <input  class="easyui-combobox" 
					 id="topicMajorIds"
		            name="topicMajorIds"
		            data-options="
                    url:'<%=path%>/majorController/findMajorsByAcademy',
                    method:'get',
                    valueField:'majorId',
                    textField:'majorName',
                    multiple:true,
                    panelHeight:'auto',
                    required:true,editable:false" style="width:200px"/>
			</td>
		</tr>
	</table>
</form>