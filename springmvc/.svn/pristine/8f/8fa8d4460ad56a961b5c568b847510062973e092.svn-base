<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
.aa{
margin-top: 10px;
margin-left: 10px;
}
</style>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:'<%=path%>/userController/viewTeaInfo',
			success : function(data) {
				var dataObj = eval("(" + data + ")");
				$("input[name='userName']").val(dataObj.userName);
				$("input[name='teaName']").val(dataObj.teaName);
				$("input[name='teaEmail']").val(dataObj.teaEmail);
				$("input[name='teaPhone']").val(dataObj.teaPhone);
				$("input[name='teaAcademy']").val(dataObj.teaAcademy);
				$("input[name='teaTroom']").val(dataObj.teaTroom);
				$("input[name='deptName']").val(dataObj.deptName);
				$("input[name='teaTitle']").val(dataObj.teaTitle);
				$("#teaTroom").combobox({
					
			        onLoadSuccess:function(){
			        	
			        	  var val = $(this).combobox('getData');
			                for (var item in val) {
			             		if($("input[name='teaTroom']").val()==(val[item].troomName)){
			             			 $(this).combobox('select', val[item].troomId);
			             		}
				                }   
			        }
				});
				$("#teaTitle").combobox({
		            onLoadSuccess:function(){
		          	  var val = $(this).combobox('getData');
			                for (var item in val) {
			             		if($("input[name='teaTitle']").val()==(val[item].dicName)){
			             			 $(this).combobox('select', val[item].dicId);
			             		}
			                }   
		          }
				});
			
			
			}
		});
	
		
		
	
	}
	);
	$("#teaInfoEditDlg_form").form({
		
		url :'<%=path%>/userController/editUserInfo',
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
<form id="teaInfoEditDlg_form" method="post">
	<table id="teaInfoEditDlg_table" class="aa">
		<tr>
			<td>工号</td>
			<td><input name="userName" type="text" readonly="readonly" /></td>
			<td>姓名</td>
			<td><input name="teaName" type="text" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>院系</td>
			<td><input name="teaAcademy" type="text" readonly="readonly" /></td>
			<td>教研室</td>
			<td>
			<input name="teaTroom" id="teaTroom" type="text"     
    			data-options="valueField:'troomId',textField:'troomName',url:'<%=path %>/troomController/findTroomsByAcademy',required:true" />
		</td>
		</tr>
		<tr>
			<td>职称/学位</td>
			<td><input name="teaTitle" id="teaTitle" type="text"
    			data-options="valueField:'dicId',textField:'dicName',url:'<%=path %>/dataDictionaryController/findByMark?mark=title',required:true" />
		    </td>
			<td>电话</td>
			<td><input name="teaPhone" /></td>
		</tr>
		
		<tr >
			<td colspan="1">邮箱</td>
			<td colspan="3"><input name="teaEmail" type="text" class="easyui-validatebox" data-options="validType: 'email' "  style="width:350px"/></td>
		</tr>
	</table>
</form>
