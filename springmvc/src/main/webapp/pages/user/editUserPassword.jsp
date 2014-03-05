<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script type="text/javascript">
/*form表单提交*/
$("#editUserPassword_form").form({
	url :'<%=path%>/userController/editUserPassword',
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
		url:'<%=path%>/userController/editUserPasswordInit',
		dataType:'json',
		success : function(data) {
			
			$("input[name='userName']").val(data.userName);
			$("input[name='personName']").val(data.personName);
		}
	});
});
$('#user_oldPassWord').blur(function(){
    $.ajax({
    	url:'<%=path%>/userController/isTheTrueOldPassword',
    	data:'oldPassWord='+$(this).val(),
    	dataType:'json',
    	success:function(result){    
    		if(result.theBoolean){
    			alert("旧密码输入错误!!!");
    			 $('#user_oldPassWord').val("");
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
<form id="editUserPassword_form" method="post">
	<input name="userId" type="hidden" />
	<table id="editUserPassword_table">
		<tr>
			<td>用户名</td>
			<td><input name="userName" type="text"
				id="editUserPassword_userName" readOnly="readOnly"  /></td>
		</tr>
		<tr>
			<td>真实姓名</td>
			<td><input name="personName" type="text"  readOnly="readOnly"></td>
		</tr>
		<tr>
			<td>旧密码</td>
			<td><input id="user_oldPassWord" name="oldPassWord" type="password" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input id="user_passWord" name="passWord" type="password" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>确认密码</td>
			<td><input id="user_rePassWord" name="rePassWord" type="password" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#user_passWord\']' " /></td>
		</tr>

	</table>
</form>

