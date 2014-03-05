<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<style>
#userEditPassWordDlg_table{padding-left:20px;font-size: 12px;}
</style>
<script type="text/javascript">
	$(function(){
		/*form表单提交*/
		$("#editStudentPassWordDlg_form").form({
			url :'<%=path%>/userController/editStudentPassWord',
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
	});
	$.extend($.fn.validatebox.defaults.rules, {    
	    equals: {    
	        validator: function(value,param){    
	            return value == $(param[0]).val();    
	        },    
	        message: '两次密码不一致'   
	    }
	});  
</script>
<form id="editStudentPassWordDlg_form" method="post">
<input name="userName" type="hidden" />
<table id="editStudentPassWordDlg_table">
	<tr>
		<td>新密码</td>
		<td><input id="user_passWord" name="passWord" type="password" class="easyui-validatebox" data-options="required:true"/></td>
	</tr>	
	<tr>
		<td>确认密码</td>
		<td><input id="reuser_passWord" name="repassWord" type="password" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#user_passWord\']' "/></td>
	</tr>	
	
</table>
</form>
