<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var stuInfoGrid;
	$(function(){
		stuInfoGrid=$("#stuUnTopicManager_datagrid");
		stuInfoGrid.datagrid({
			url:'<%=path %>/studentTopicController/findUnTopicStuInfs',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
        	fit:true,
        	pagination:true,
        	pageSize:15,
        	pageList:[15,25,35,45],
        	idField:'stutId',
            columns:[[    
                      {field:'stutId',width:50,align:'center',hidden:true,title:'id'},    
                      {field:'ck',checkbox:true},    
                      {field:'userName',width:80,align:'center',title:'学号'},
                      {field:'stuName',width:50,align:'center',title:'学生姓名'},
                      {field:'stuSex',width:50,align:'center',title:'性别'},
                     /*  {field:'stuNation',width:50,align:'center',title:'民族'}, */
                      {field:'stuAcademy',width:100,align:'center',title:'院系'}, 
                      {field:'stuMajor',width:150,align:'center',title:'专业'}, 
                      {field:'stuSubject',width:50,align:'center',title:'本专科'},
                      {field:'stuGrade',width:50,align:'center',title:'入学年级'},
                      {field:'stuClass',width:50,align:'center',title:'班级'},
                    /*   {field:'StuPoliticalLandscape',width:50,align:'center',title:'政治面貌'}, */
                    /*   {field:'stuPost',width:50,align:'center',title:'职务'}, */
                      {field:'stuPhone',width:80,align:'center',title:'手机号码'}
                      /* {field:'stuEmail',width:100,align:'center',title:'电子邮箱'} */
                  ]],
             toolbar:'#stuUnTopicManager_toolbar'
                  
		});
	});
	//导出所有为选课题的学生名单
	function exportStudents(){
		$.messager.confirm('确认','您确认想要导出所有未选课题的学生吗？',function(r){    
		    if (r){    
		    	$.ajax({
					dataType: "json", 
					url:'<%=path%>/studentTopicController/exportStudents',
					success: function(result) { 
						if (result.status) {
							window.location.href=result.message;
							$.messager.show({
								title :  '导出提示',
								msg : '导出成功',
								timeout : 1000 * 2
							});
						}else{
							$.messager.show({
								title :  '导出提示',
								msg : '导出失败',
								timeout : 1000 * 2
							});
						}
						}
				});
		    }    
		});
		
	}
</script>
<div id="stuUnTopicManager_toolbar">
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="exportStudents()">导出学生</a>
</div>
<table id="stuUnTopicManager_datagrid"></table>  
