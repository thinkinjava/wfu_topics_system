<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<style>
#teaEditDlg_table{padding-left:20px;font-size: 12px;}
</style>
<script type="text/javascript">
	$(function(){
		$.ajax({//得到当前管理员所在院系
			url:'<%=path%>/userController/getAcademy',
			dataType:'json',
			success:function(r){
				$('#teaAcademy').val(r.academyName);
				$('#teaAcademyId').val(r.academyId);
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
		$("#teaTroom").combobox({
            onLoadSuccess:function(){
            	  var val = $(this).combobox('getData');
	                for (var item in val) {
	             		if($("input[name='teaTroom']").val()==(val[item].troomName)){
	             			 $(this).combobox('select', val[item].troomId);
	             		}
  	                }   
            }
		});
		$("#teaTitle").combobox({
            onLoadSuccess:function(){
          	  var val = $(this).combobox('getData');
	                for (var item in val) {
	             		if($("input[name='teaTitle']").val()==(val[item].dicName)){
	             			 $(this).combobox('select', val[item].dicId);
	             		}
	                }   
          }
		});
		$("#deptName").combotree({
			width:171,
			url:"<%=path%>/userController/getAllDepts",
			idField:'id',
		 	textField:'name',
		 	parentField:'pid',
		 	onSelect:function(node){
		 		$("#teaDept").val(node.id);
		 	}
		});

		/*form表单提交*/
		$("#teaEditDlg_form").form({
			url :'<%=path%>/userController/updateTeacher',
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
	$.extend($.fn.validatebox.defaults.rules, {    
	    equals: {    
	        validator: function(value,param){    
	            return value == $(param[0]).val();    
	        },    
	        message: '两次密码不一致'   
	    }
	});  
</script>
<form id="teaEditDlg_form" method="post">
<table id="teaEditDlg_table">
	<tr>
		<td>工号</td>
		<td>
			<input name="userName" type="text" readonly="readonly"/>
			<input name="teaId" type="hidden"/>
		</td>
		<td>电话</td>
		<td><input name="teaPhone" class="easyui-numberbox" data-options="required:true"/></td>
	</tr>
	<tr>
		<td>真实姓名</td>
		<td><input name="teaName" type="text" class="easyui-validatebox" data-options="required:true"/></td>
		<td>邮箱</td>
		<td><input name="teaEmail" class="easyui-validatebox" data-options="required:true,validType: 'email' "/></td>
	</tr>
	<tr>
		<td>性别</td>
		<td><input value="男" name="teaSex" type="radio">男 <input value="女" name="teaSex" type="radio">女</td>
		<td>年龄</td>
		<td><input name="teaAge" type="text" class="easyui-numberbox"/>(数字格式)</td>
	</tr>	
	<tr>
		<td>部门</td>
		<td>
			<input name="teaDept" id="teaDept" type="hidden">
			<input name="deptName" id="deptName" type="text"  />
			
		</td>
		<td>职称</td>
		<td>
			<input name="teaTitle" id="teaTitle" type="text"
    			data-options="valueField:'dicId',textField:'dicName',url:'<%=path %>/dataDictionaryController/findByMark?mark=title',required:true" />
		</td>
	</tr>	
	
	<tr>
		<td>教研室</td>
		<td>
			<input name="teaTroom" id="teaTroom" type="text"     
    			data-options="valueField:'troomId',textField:'troomName',url:'<%=path %>/troomController/findTroomsByAcademy',required:true" />
		</td>
		<td>院系</td>
		<td>
			<input name="teaAcademyId" id="teaAcademyId" type="hidden"/>
			<input name="teaAcademy" id="teaAcademy" type="text" />
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
