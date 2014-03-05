<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<style>
#userAddDlg_table{padding-left:20px;font-size: 12px;}
</style>
<script type="text/javascript">
	$(function(){
		$("#deptName").combotree({
			width:171,
			url:"<%=path%>/userController/getAllDepts",
			idField:'id',
		 	textField:'name',
		 	parentField:'pid',
		 	onSelect:function(node){
		 		$("#deptId").val(node.id);
		 	}
		});

		/*form表单提交*/
		$("#userAddDlg_form").form({
			url :'<%=path%>/userController/saveOrUpdateUser',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var aa=$("#roleIds").combobox("getText");
				$("#roleNames").val(aa);
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
		$('#userAddDlg_userName').blur(function(){
            $.ajax({
            	url:'<%=path%>/userController/isHasRepeatUserName',
            	data:'userName='+$(this).val(),
            	dataType:'json',
            	success:function(result){    
            		if(result.theBoolean){
            			alert("系统中已存在该用户，请使用其他用户名");
            			 $('#userAddDlg_userName').val("");
            		}
            	}
            });

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
<form id="userAddDlg_form" method="post">
<table id="userAddDlg_table">
	<tr>
		<td>用户名</td>
		<td><input name="userName" type="text" id="userAddDlg_userName" class="easyui-validatebox" data-options="required:true"/></td>
		<td>密码</td>
		<td><input id="user_passWord" name="passWord" type="password" class="easyui-validatebox" data-options="required:true"/></td>
	</tr>
	<tr>
		<td>确认密码</td>
		<td><input id="reuser_passWord" name="repassWord" type="password" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#user_passWord\']' "/></td>
		<td>电话</td>
		<td><input name="phone" class="easyui-numberbox" data-options="required:true"/></td>

	</tr>
	<tr>
		<td>真实姓名</td>
		<td><input name="personName" type="text" class="easyui-validatebox" data-options="required:true"/></td>
		<td>邮箱</td>
		<td><input name="email" class="easyui-validatebox" data-options="required:true,validType: 'email' "/></td>
	</tr>
	<tr>
		<td>性别</td>
		<td><input value="男" name="sex" type="radio">男 <input value="女" name="sex" type="radio">女</td>
		<td>年龄</td>
		<td><input name="age" type="text" class="easyui-numberbox"/>(数字格式)</td>
	</tr>	
	<tr>
		<td>部门</td>
		<td>
			<input name="deptId" id="deptId" type="hidden">
			<input name="deptName" id="deptName" type="text" data-options="required:true"/>
			
		</td>
	</tr>	
	<tr>
		<td>权限</td>
		<td>
			<input type="hidden" name="roleNames" id="roleNames"/>
			 <input  class="easyui-combobox" 
			 id="roleIds"
            name="roleIds"
            data-options="
                    url:'<%=path%>/roleController/findAllRole',
                    method:'get',
                    valueField:'roleId',
                    textField:'roleName',
                    multiple:true,
                    panelHeight:'auto',
                    required:true" style="width:200px"/>
                    
		</td>
	</tr>
</table>
</form>
