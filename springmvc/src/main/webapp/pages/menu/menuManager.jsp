<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var myTGrid;
	$(function(){
		myTGrid = $("#menuManager_table");
		myTGrid.treegrid({    
		    url:'<%=path%>/menuController/findAllMenus',
			idField:'id',
			treeField:'name',
		 	parentField:'pid',
			rownumbers:false,
			fitColumns : true,
			border : false,
		    fit:true,
		    columns:[[    
				{title:'id',field:'id',width:250,align:'left',hidden:true},
		        {title:'名称',field:'name',width:250,align:'left'},    
		        {title:'链接(参数)',field:'url',width:250,align:'left'},    
		        {title:'类型',field:'type',width:150,align:'center'},    
		        {title:'备注',field:'desc',width:400,align:'center'}    
		    ]],
		    onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},toolbar:'#menu_toolbar'

		}); 
	});

	//添加主菜单
	function addMenu(){
		parent.$.modalDialog({
			title : "添加主菜单",
			width : 440,
			height : 300,
			href : "pages/menu/menuAddDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= myTGrid;
					var f = parent.$.modalDialog.handler.find("#menuAddDlg_form");
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
	function editMenu(){
		var row=myTGrid.treegrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title : "编辑菜单",
				width : 440,
				height : 300,
				href : "pages/menu/menuEditDlg.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#menuEditDlg_form");
					f.form("load", {
						menuId:row.id,
						menuPid:row.pid,
						menuName:row.name,
						url:row.url,
						type:row.type,
						description:row.desc
						});
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= myTGrid;
						var f = parent.$.modalDialog.handler.find("#menuEditDlg_form");
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
				msg : "请选择一个菜单后进行编辑!",
				timeout : 1000 * 2});
		}
		
	}
	//增加子菜单
	function addChildMenu(){
		var row=myTGrid.treegrid('getSelected');
			if(row){
				if(row.type=='功能'){  //功能菜单不能添加子菜单
					$.messager.show({
						title : '提示信息',
						msg : '功能菜单不能添加子菜单',
						timeout : 1000 * 2
					});
				}else{
					parent.$.modalDialog({
						title : "添加子菜单",
						width : 440,
						height : 300,
						href : "pages/menu/menuChildAddDlg.jsp",
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#menuChildAddDlg_form");
							f.form("load", {name:row.name,menuPid:row.id});
						},
						buttons : [ {
							text : '保存',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= myTGrid;
								var f = parent.$.modalDialog.handler.find("#menuChildAddDlg_form");
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
			}else{
				$.messager.show({
					title : "提示",
					msg : "请选择一个菜单后添加子菜单!",
					timeout : 1000 * 2});
			}
			
		
	}
	//删除菜单
	function deleteMenu(){
		var row=myTGrid.treegrid('getSelected');

		if(row){
			$.messager.confirm('确认','您确认想要删除该菜单及以下子菜单吗？',function(r){    
			    if (r){    //确认删除
					$.ajax({
						url:'<%=path%>/menuController/deleteMenu',
						data:'menuId='+row.id+'&menuPid='+row.pid,
						success:function(){
							$.messager.show({
									title : "提示",
									msg : "删除成功!",
									timeout : 1000 * 2});
							myTGrid.treegrid('reload');	// 重新载入所有行
						},
					});
			    }    
			});
		}else{
			$.messager.show({
				title : "提示",
				msg : "请选择一个菜单后删除!",
				timeout : 1000 * 2});
		}
	}
</script>
<table id="menuManager_table"></table>
<div id="menu" class="easyui-menu" style="width: 120px; display: none;">	
	<div onclick="addChildMenu();" data-options="iconCls:'pencil_add'">增加子菜单</div>
	<div onclick="editMenu();" data-options="iconCls:'pencil'">编辑菜单</div>
	<div onclick="deleteMenu();" data-options="iconCls:'pencil_delete'">删除菜单</div>
	<div onclick="addMenu();" data-options="iconCls:'pencil_add'">增加主菜单</div>
</div>
<div id="menu_toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addMenu();">增加主菜单</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addChildMenu();">增加子菜单</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMenu();">编辑菜单</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMenu();">删除菜单</a>
</div>