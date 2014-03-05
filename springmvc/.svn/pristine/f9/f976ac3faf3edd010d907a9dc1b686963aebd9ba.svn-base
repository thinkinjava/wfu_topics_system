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
			url:'<%=path%>/userController/viewStuInfo',
			success : function(data) {
				var dataObj = eval("(" + data + ")");
				$("input[name='userName']").val(dataObj.userName);
				$("input[name='stuName']").val(dataObj.stuName);
				$("input[name='stuEmail']").val(dataObj.stuEmail);
				$("input[name='stuPhone']").val(dataObj.stuPhone);
				$("input[name='stuGrade']").val(dataObj.stuGrade);
				$("input[name='stuClass']").val(dataObj.stuClass);
				$("input[name='stuAcademy']").val(dataObj.stuAcademy);
				$("input[name='stuMajor']").val(dataObj.stuMajor);
			}
		});
	});
</script>
<form id="viewStuInfoDlg_form" method="post">
	<table id="viewStuInfoDlg_table" class="aa">
		<tr>
			<td>学号</td>
			<td><input name="userName" type="text" id="userEditDlg_userName"
				readonly="readonly" /></td>
			<td>姓名</td>
			<td><input name="stuName" readOnly="readOnly"/></td>
		</tr>
		<tr>
			<td>院系</td>
			<td><input name="stuAcademy" type="text" readonly="readonly" /></td>
			<td>专业</td>
			<td><input name="stuMajor" type="text" readOnly="readOnly" /></td>
		</tr>
		<tr>
			<td>年级</td>
			<td><input name="stuGrade" type="text" readonly="readonly" /></td>
			<td>班级</td>
			<td><input name="stuClass" type="text" readOnly="readOnly" /></td>
		</tr>
		<tr>
			<td>电话</td>
			<td><input name="stuPhone" type="text" readOnly="readOnly" /></td>
			<td>邮箱</td>
			<td><input name="stuEmail" type="text" readonly="readonly" /></td>
		</tr>
		
		
	</table>
</form>
