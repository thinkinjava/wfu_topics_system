<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
	$(function(){
		var stuMajor;
		$.ajax({
			url:'<%=path%>/userController/viewStuInfo',
			success : function(data) {
				var dataObj = eval("(" + data + ")");
				$("#userName").val(dataObj.userName);
				$("input[name='stuName']").val(dataObj.stuName);
				$("input[name='stuEmail']").val(dataObj.stuEmail);
				$("input[name='stuPhone']").val(dataObj.stuPhone);
				$("input[name='stuGrade']").val(dataObj.stuGrade);
				$("input[name='stuClass']").val(dataObj.stuClass);
				$("input[name='stuAcademy']").val(dataObj.stuAcademy);
				$("input[name='stuMajor']").val(dataObj.stuMajor);			
				
				$("#stuMajor").combobox({
				    onLoadSuccess:function(){ 
				    	
				    	  var val = $(this).combobox('getData');
				    	  for (var item in val) {
				         		if($("#stuMajor").val()==(val[item].majorName)){
				         			 $(this).combobox('select', val[item].majorId);
				         		
				         		}
				              }   
				    }
				});
			
			}
		});
		
		
	
	
	
	
	});
	
	$("#stuInfoEditDlg_form").form({
		
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
<form id="stuInfoEditDlg_form" method="post">
	<table id="stuInfoEditDlg_table">
		<tr>
			<td>学号</td>
			<td><input id="userName" name="userName" type="text" id="userEditDlg_userName"
				readonly="readonly" /></td>
			<td>姓名</td>
			<td><input name="stuName" readOnly="readOnly"/></td>
		</tr>
		<tr>
			<td>院系</td>
			<td><input name="stuAcademy" type="text" readonly="readonly" /></td>
			<td>专业</td>
			<td><input id="stuMajor"  name="stuMajor" type="text"
				data-options="valueField:'majorId',textField:'majorName',url:'<%=path%>/majorController/findMajorsByAcademy',required:true,editable:false" /></td>
		</tr>
		<tr>
			<td>年级</td>
			<td><input name="stuGrade" type="text" readonly="readonly" /></td>
			<td>班级</td>
			<td><input name="stuClass" type="text" readOnly="readOnly" /></td>
		</tr>
		<tr>
			<td>电话</td>
			<td><input name="stuPhone" type="text"/></td>
			<td >邮箱</td>
			<td ><input name="stuEmail" type="text" class="easyui-validatebox" data-options="validType: 'email' "  /></td>
		</tr>
		
	</table>
</form>
