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
			}
		});
	});
</script>
<form id="viewTeaDlg_form" method="post">
	<table id="viewTeaDlg_table" class="aa">
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
			<td><input name="teaTroom" type="text" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>职称/学位</td>
			<td><input name="teaTitle" type="text" readonly="readonly" /></td>
			<td>电话</td>
			<td><input name="teaPhone" type="text" readonly="readonly"/></td>
		</tr>
		
		<tr >
			<td colspan="1">邮箱</td>
			<td colspan="3"><input name="teaEmail" type="text" readonly="readonly" style="width:350px"/></td>
		</tr>
	</table>
</form>
