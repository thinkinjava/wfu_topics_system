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
	//专业默认选中
	$("#topicMajorIds").combobox({
        onLoadSuccess:function(){
      	  var v = $(this).combobox('getData');
                for (var item in v) {             	
             		if($('#topicMajorNames').val().indexOf(v[item].majorName) >= 0){
             			 $(this).combobox('select', v[item].majorId);
             			 
             		}
                }   
      }
	});
	//课题类型默认选中
	$("#topicType").combobox({
        onLoadSuccess:function(){
        	  var val = $(this).combobox('getData');
                for (var item in val) {
             		if($("input[name='topicType']").val()==(val[item].dicName)){
             			 $(this).combobox('select', val[item].dicId);
             		}
	                }   
        }
	});
});
/*form表单提交*/
$("#topicEditDlg_form").form({
	url :'<%=path%>/teaTopicController/updateTopic',
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
				msg : '修改成功',
				timeout : 1000 * 2
			});
		}else{
			$.messager.show({
				title :  '保存提示',
				msg : '修改失败',
				timeout : 1000 * 2
			});
		}
	}
});	
</script>

<form id="topicEditDlg_form" method="post">
	<table id="topicEditDlg_table" class="aa">
		<tr>
			<td>课题名称</td>
			<td>
				<input type="hidden" name="topicId" />
				<input type="text" name="topicName" class="easyui-validatebox" data-options="required:true" style="width:300px"/>
			</td>
		</tr>
		<tr>
			<td>课题类型</td>
			<td>
				<input name="topicType" id="topicType" type="text"  
    			data-options="valueField:'dicId',textField:'dicName',url:'<%=path %>/dataDictionaryController/findByMark?mark=topicType',required:true,editable:false" />
			</td>
		</tr>
		<tr>
			<td>适合专业（可多选）</td>
			<td>
				<input type="hidden" name="topicMajorNames" id="topicMajorNames"/>
			    <input  
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