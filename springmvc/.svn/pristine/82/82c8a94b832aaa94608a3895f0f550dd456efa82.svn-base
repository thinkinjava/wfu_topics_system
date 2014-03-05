<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function(){
		$("#menuEditDlg_form").form({
			url :'<%=path%>/menuController/saveOrUpdateMenu',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});

			},
			success : function(result) {
				parent.$.messager.progress('close');
				if(result){
					$.messager.show({
						title : '提示信息',
						msg : '修改菜单成功',
						timeout : 1000 * 2
					});
				}else{
					$.messager.show({
						title : '提示信息',
						msg : '修改菜单失败',
						timeout : 1000 * 2
					});
				}

				parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为页面预定义好了
				parent.$.modalDialog.handler.dialog('close');
				$("#menuManager_table").treegrid('reload');	// 重新载入所有行
			}
		});
	});
</script>

<form id="menuEditDlg_form" method="post">
	<table>
		<tr>
			<td>菜单名称</td>
			<td>
				<input name="menuName" type="text"/>
				<!-- 隐藏id，用于传值 -->
				<input name="menuId" type="hidden"/> 
				<input name="menuPid" type="hidden"/>
			</td>
		</tr>
		<tr>
			<td>链接（参数）</td>
			<td><input name="url" type="text"/></td>
		</tr>
		<tr>
			<td>类型</td>
			<td>
				<select name="type">
					  <option value="菜单">菜单</option>
  					  <option value="功能">功能</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>排序</td>
			<td><input name="num" type="text"></td>
		</tr>
		<tr>
			<td>描述</td>
			<td><textarea name="description"></textarea></td>
		</tr>
	</table>
</form>