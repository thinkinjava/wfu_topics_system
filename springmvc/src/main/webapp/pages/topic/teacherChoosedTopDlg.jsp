<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var teacherChoosedTopicGrid;
	$(function(){
		teacherChoosedTopicGrid=$("#teacherChoosedTopicManager_datagrid");
		teacherChoosedTopicGrid.datagrid({
			url:'<%=path %>/teaTopicController/findAllChoosedTopic',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
        	fit:true,
        	pagination:true,
        	pageSize:15,
        	pageList:[15,25,35,45],
        	idField:'choosedId',
            columns:[[    
                      {field:'choosedId',width:100,align:'center',hidden:true,title:'id'}, 
                      {field:'ck',checkbox:true},    
                      {field:'topicName',width:150,align:'center',title:'课题名称'},
                      {field:'topicType',width:80,align:'center',title:'课题类型'},
                      {field:'choosedStudentName',width:80,align:'center',title:'选中学生'},
                      {field:'studentMajor',width:100,align:'center',title:'学生专业'},
                      {field:'studentPhone',width:80,align:'center',title:'学生电话'},
                      {field:'studentEmail',width:80,align:'center',title:'学生邮箱'}
                  ]],
                  toolbar:'#teacherChoosedTopDlg_toolbar'
		});
	});
	
	$("#topicNoChoosed").click(function(){
		var row = teacherChoosedTopicGrid.datagrid('getSelected');
		if(row){
			$.messager.confirm('确认','您确认想要退选学生吗？',function(r){    
			    if (r){    
			    	 $.ajax({
				        	url:'<%=path %>/teaTopicController/topicNoChoosed',
				        	data:"choosedId="+row.choosedId,
							dataType:'json',
							success: function(result){
								if (result.status) {
									teacherChoosedTopicGrid.datagrid('reload');    // 重新载入当前页面数据 
									$.messager.show({
										title : '删除提示',
										msg : '删除成功',
										timeout : 1000 * 2
									});
								}else{
									$.messager.show({
										title :  '删除提示',
										msg : '删除失败',
										timeout : 1000 * 2
									});
								}
							}
				        }); 
			    }    
			});
		}else{
			$.messager.show({
				title : "提示",
				msg : "请至少选择一条已选课题数据!",
				timeout : 1000 * 2
			});
		}
	});
	
</script>
<div id="teacherChoosedTopDlg_toolbar">
<shiro:hasPermission name="topicNoChoosed">
<a href="javascript:void(0)" id="topicNoChoosed" class="easyui-linkbutton"  iconCls="icon-add" plain="true">退选学生</a>
</shiro:hasPermission>
</div>
<table id="teacherChoosedTopicManager_datagrid"></table>  
