<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
/*form表单提交*/
$("#userInfoEditDlg_form").form({
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
			parent.reload;
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
	$(function(){
		$.ajax({
			url:'<%=path%>/userController/editUserInfoInit',
			success : function(data) {
				var dataObj = eval("(" + data + ")");
				/* $("input[name='userName']").val(dataObj.userName); */
				$("input[name='phone']").val(dataObj.phone);
				/* $("input[name='personName']").val(dataObj.personName); */
				$("input[name='email']").val(dataObj.email);
				/* $("input[name='age']").val(dataObj.age);
				$("input[name='deptName']").val(dataObj.dept.deptName);
				if (dataObj.sex = "男") {
					$("input[value='男']").attr("checked", true);
				} else {
					$("input[value='女']").attr("checked", true);
				} */
			}
		});
	});
</script>
<form id="userInfoEditDlg_form" method="post">
	<input name="userId" type="hidden" />
	<input name="userType" type="hidden">
	<table id="userInfoEditDlg_table">
		<tr>
			<td>电话</td>
			<td><input name="phone" /></td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input name="email" class="easyui-validatebox" data-options="required:true,validType: 'email' " /></td>
		</tr>
	</table>
</form>
