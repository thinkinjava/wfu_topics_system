<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<style>
#userEditDlg_table{padding-left:20px;font-size: 12px;}
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
		
		$("#roleIds").combobox({
            onLoadSuccess:function(){
          	  var val = $(this).combobox('getData');
	                for (var item in val) {
	             		if($('#roleNames').val().indexOf(val[item].roleName) >= 0){
	             			 $(this).combobox('select', val[item].roleId);
	             		}
	                }   
          }
		});
		
		/*form表单提交*/
		$("#userEditDlg_form").form({
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
	});
 
</script>
<form id="userEditDlg_form" method="post">
<input name="userId" type="hidden" />
<table id="userEditDlg_table">
	<tr>
		<td>用户名</td>
		<td>
			<input name="userName" type="text" id="userEditDlg_userName" readonly="readonly" class="easyui-validatebox" data-options="required:true"/>
			<input id="user_passWord" name="passWord" type="hidden" />
		</td>
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
			 <input  
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
