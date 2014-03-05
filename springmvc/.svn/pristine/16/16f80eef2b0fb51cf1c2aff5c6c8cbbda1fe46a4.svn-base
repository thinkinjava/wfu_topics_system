<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var deptTreeGrid;
	$(function(){
		deptTreeGrid = $("#deptManager_table");
		deptTreeGrid.treegrid({    
		    url:'<%=path%>/deptController/findAllDept',
			idField:'deptId',
			treeField:'deptName',
		 	parentField:'parentId',
			rownumbers:false,
			fitColumns : true,
			border : false,
		    fit:true,
		    columns:[[    
				{title:'deptId',field:'deptId',width:250,align:'left',hidden:true},
		        {title:'部门名称',field:'deptName',width:250,align:'left'},    
		        {title:'排序',field:'theOrder',width:50,align:'center'},    
		        {title:'创建时间',field:'creatTime',width:150,align:'center'},    
		        {title:'备注',field:'description',width:400,align:'center'}    
		    ]],
		    onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.deptId);
				$('#dept').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},toolbar:'#dept_toolbar'

		}); 
	});

	//添加主部门
	function adddept(){
		parent.$.modalDialog({
			title : "添加主菜单",
			width : 440,
			height : 300,
			href : "pages/dept/deptAddDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= deptTreeGrid;
					var f = parent.$.modalDialog.handler.find("#deptAddDlg_form");
					var isValid = f.form('validate');
					if(isValid){  //只有验证通过才能提交
						f.submit();
					}
					
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					parent.$.modalDialog.handler.dialog('destroy');
					parent.$.modalDialog.handler = undefined;
				}
			}
			]
		});

	}
	//编辑部门
	function editdept(){
		var row=deptTreeGrid.treegrid('getSelected');
		
		if(row){
			parent.$.modalDialog({
				title : "编辑菜单",
				width : 440,
				height : 300,
				href : "pages/dept/deptEditDlg.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#deptEditDlg_form");
					f.form("load", {
						deptName:row.deptName,
						theOrder:row.theOrder,
						description:row.description,
						deptId:row.deptId
						});
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= deptTreeGrid;
						var f = parent.$.modalDialog.handler.find("#deptEditDlg_form");
						var isValid = f.form('validate');
						if(isValid){  //只有验证通过才能提交
							f.submit();
						}
						
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				}
				]
			});
		}else{
			$.messager.show({
				title : "提示",
				msg : "请选择一个部门后进行编辑!",
				timeout : 1000 * 2});			
		}
		
	}
	//增加子菜单
	function addChilddept(){
		var row=deptTreeGrid.treegrid('getSelected');
			if(row){  
				parent.$.modalDialog({
					title : "添加子部门",
					width : 440,
					height : 300,
					href : "pages/dept/deptChildAddDlg.jsp",
					onLoad:function(){
						var f = parent.$.modalDialog.handler.find("#deptChildAddDlg_form");
						f.form("load", {pName:row.deptName,parentId:row.deptId});
					},
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= deptTreeGrid;
							var f = parent.$.modalDialog.handler.find("#deptChildAddDlg_form");
							var isValid = f.form('validate');
							if(isValid){  //只有验证通过才能提交
								f.submit();
							}
							
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}
					]
				});
			}else{
				$.messager.show({
					title : "提示",
					msg : "请选择一个部门后添加子部门!",
					timeout : 1000 * 2});
			}
		
	}
	//删除部门
	function deletedept(){
		var row=deptTreeGrid.treegrid('getSelected');
		if(row){
			$.messager.confirm('确认','您确认想要删除该部门及以下子部门吗？',function(r){    
			    if (r){    //确认删除
					$.ajax({
						url:'<%=path%>/deptController/deletedept',
						data:'deptId='+row.deptId,
						dataType:'json',
						success:function(data){
							$.messager.show({
									title : "提示",
									msg : data.message,
									timeout : 1000 * 2});
							$("#deptManager_table").treegrid('reload');	// 重新载入所有行
						},
					});
			    }    
			});
		}else{
			$.messager.show({
				title : "提示",
				msg : "请选择一个部门后删除!",
				timeout : 1000 * 2});
		}
	}
</script>
<table id="deptManager_table"></table>
<div id="dept" class="easyui-menu" style="width: 120px; display: none;">
	<shiro:hasPermission name="deptAdd">	
	<div onclick="adddept();">增加主部门</div>
	<div onclick="addChilddept();">增加子部门</div>
	</shiro:hasPermission>
	<shiro:hasPermission name="deptEdit">	
	<div onclick="editdept();" >编辑部门</div>
	</shiro:hasPermission>
	<shiro:hasPermission name="deptDetele">
	<div onclick="deletedept();">删除部门</div>
	</shiro:hasPermission>
	
</div>
<div id="dept_toolbar">
	<shiro:hasPermission name="deptAdd">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="adddept();">增加主部门</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addChilddept();">增加子部门</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="deptEdit">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editdept();">编辑部门</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="deptDetele">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deletedept();">删除部门</a>
	</shiro:hasPermission>
</div>