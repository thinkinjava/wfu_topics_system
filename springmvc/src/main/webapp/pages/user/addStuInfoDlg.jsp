<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">


$(function(){
	$.ajax({
		url:'<%=path%>/userController/findAcademy',
		success : function(data) {
			var dataObj = eval("(" + data + ")");
			$("input[name='stuAcademy']").val(dataObj.academyName);
			$("input[name='stuAcademyId']").val(dataObj.academyId);
		}
	});
	$('#userName').blur(function(){
	    $.ajax({
	    	url:'<%=path%>/userController/isHasRepeatUserName',
	    	data:'userName='+$(this).val(),
	    	dataType:'json',
	    	success:function(result){    
	    		if(result.theBoolean){
	    			alert("系统中已存在该用户，请使用其他用户名");
	    			 $('#teaAddDlg_userName').val("");
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
});
$("#addStuInfoDlg_form").form({
	url :'<%=path%>/userController/saveStuInfo',
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
				$("#stuInfManager_datagrid").datagrid('reload'); 
				parent.$.modalDialog.handler.dialog('close');
				$.messager.show({
					title : '保存提示',
					msg : '保存成功',
					timeout : 1000 * 2
				});
			} else {
				$.messager.show({
					title : '保存提示',
					msg : '保存失败',
					timeout : 1000 * 2
				});
			}
		}
	});
</script>
<form id="addStuInfoDlg_form" method="post">
	<table id="addStuInfoDlg_table">
		<tr>
			<td>学号</td>
			<td><input name="userName" type="text" id="userName"
				class="easyui-validatebox" data-options="required:true" /></td>
			<td>学生姓名</td>
			<td><input id="stuName" name="stuName" type="text"
				class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input id="user_passWord" name="passWord" type="password"
				class="easyui-validatebox" data-options="required:true" /></td>
			<td>确认密码</td>
			<td><input id="reuser_passWord" name="repassWord"
				type="password" class="easyui-validatebox"
				data-options="required:true,validType:'equals[\'#user_passWord\']' " /></td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input name="stuEmail" type="text"
				class="easyui-validatebox" data-options="required:true" /></td>
			<td>电话</td>
			<td><input id="stuPhone" name="stuPhone"
				class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>权限</td>
			<td><input type="hidden" name="roleNames" id="roleNames" /> <input
				class="easyui-combobox" id="roleIds" name="roleIds"
				data-options="
                    url:'<%=path%>/roleController/findAllRole',
                    method:'get',
                    valueField:'roleId',
                    textField:'roleName',
                    multiple:true,
                    panelHeight:'auto',
                    required:true,editable:false"
				style="width:200px" /></td>
			<td>地址</td>
			<td><input id="address" name="address"
				class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<td>性别</td>
			<td><input value="男" name="stuSex" type="radio">男 <input
				value="女" name="stuSex" type="radio">女</td>
			<td>年龄</td>
			<td><input name="stuAge" type="text" class="easyui-numberbox" />(数字格式)</td>
		</tr>
		<tr>
			<td>年级</td>
			<td><input name="stuGrade" type="text"
				class="easyui-validatebox" /></td>
			<td>班级</td>
			<td><input name="stuClass" class="easyui-validatebox" /></td>
		</tr>
		<tr>
			<td>专业</td>
			<td><input id="stuMajor" name="stuMajor" class="easyui-combobox"
				data-options="valueField:'majorId',textField:'majorName',url:'<%=path%>/majorController/findMajorsByAcademy',required:true,editable:false" /></td>
			<td>院系</td>
			<td>
			<input name="stuAcademyId" id="stuAcademyId" type="hidden"/>
			<input name="stuAcademy" id="stuAcademy" type="text" readOnly="readOnly"/>
			</td>
		</tr>
	</table>
</form>