<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
$.ajax({//得到当前管理员所在院系
	url:'<%=path%>/userController/getAcademy',
	dataType:'json',
	success:function(r){
		$('#stuAcademy').val(r.academyName);
		$('#stuAcademyId').val(r.academyId);
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
$("#stuMajor").combobox({
    onLoadSuccess:function(){
    	  var val = $(this).combobox('getData');
            for (var item in val) {
         		if($("input[name='stuMajor']").val()==(val[item].majorName)){
         			 $(this).combobox('select', val[item].majorId);
         		}
              }   
    }
});
/*form表单提交*/
$("#editStuInfoDlg_form").form({
	url :'<%=path%>/userController/updateStuInfo',
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
<form id="editStuInfoDlg_form" method="post">
<input name="stutId" type="hidden" />
	<table id="editStuInfoDlg_table">
		<tr>
			<td>学号</td>
			<td><input name="userName" type="text" id="userName"
				class="easyui-validatebox" data-options="required:true" readOnly="readOnly" /></td>
			<td>学生姓名</td>
			<td><input id="stuName" name="stuName" type="text"
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
                    required:true"
				style="width:150px" /></td>
				<td>邮箱</td>
			<td><input name="stuEmail" type="text"
				class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			
			<td>电话</td>
			<td><input id="stuPhone" name="stuPhone"
				class="easyui-validatebox" data-options="required:true" /></td>
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
				data-options="valueField:'majorId',textField:'majorName',url:'<%=path%>/majorController/findMajorsByAcademy',required:true" /></td>
			<td>院系</td>
			<td>
			<input name="stuAcademyId" id="stuAcademyId" type="hidden"/>
			<input name="stuAcademy" id="stuAcademy" type="text" readOnly="readOnly"/>
			</td>
		</tr>
	</table>
</form>
