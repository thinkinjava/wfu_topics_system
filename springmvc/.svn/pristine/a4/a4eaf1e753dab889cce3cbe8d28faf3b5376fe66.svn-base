<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var rolegrid;
	var menugrid;
	$(function(){
		rolegrid=$('#roleManager_roleTable');
		menugrid=$('#roleManager_menuTable');
		rolegrid.datagrid({
			url:'<%=path %>/roleController/findAllRole',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
			title:'角色列表',
        	fit:true,
        	border:true,
        	pagination:false,
        	idField:'roleId',
            columns:[[    
                      {field:'roleId',width:100,align:'center',hidden:true,title:'id'},    
                      {field:'roleName',width:100,align:'center',title:'角色名'},
                      {field:'theorder',width:100,align:'center',title:'排序'},
                      {field:'description',width:100,align:'center',title:'描述'}
                  ]],
             toolbar:'#rolegrid_toolbar',
             onClickRow:getPermission
		});
		
		menugrid.treegrid({    
		    url:'<%=path%>/menuController/findAllMenus',
		    rownumbers:true,
			animate: true,
			title:'菜单列表',
			fitColumns: true,
			fit:true,
			border:true,
			striped:true,
			singleSelect:false,
			idField: 'id',
			treeField: 'name',
			parentField : 'pid',
		    columns:[[ 
				{field:'ck',checkbox:true,title:'ck'},
				{title:'id',field:'id',width:250,align:'left',hidden:true},  
		        {title:'名称',field:'name',width:250,align:'left'},    
		        {title:'链接(参数)',field:'url',width:250,align:'left'},    
		        {title:'类型',field:'type',width:150,align:'center'}    
		    ]],
		    toolbar:'#menugrid_toolbar',
		    onLoadSuccess : function(){

		    }
		   
		}); 
	});
	//添加权限
	function addRole(){
		parent.$.modalDialog({
			title : "添加角色",
			width : 340,
			height : 300,
			href : "pages/role/roleAddDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= rolegrid;
					var f = parent.$.modalDialog.handler.find("#roleAddDlg_form");
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
	//删除权限
	function delRole(){
		var row = rolegrid.datagrid('getSelected');
		if(row.length!=0){
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){    
			    	 $.ajax({
				        	url:'<%=path %>/roleController/deleteRole',
				        	data:'roleId='+row.roleId,
							success: function(){
								rolegrid.datagrid('load');
								$.messager.show({
									title : "提示",
									msg : "删除成功!",
									timeout : 1000 * 2
								});
							}
				        }); 
			    }    
			});
		}else{
			$.messager.show({
				title : "提示",
				msg : "请选择一条记录删除!",
				timeout : 1000 * 2
			});
		}
	}
	
	//编辑角色
	function editRole(){
		var row = rolegrid.datagrid('getSelected');
		if(row){
			parent.$.modalDialog({
				title : "编辑角色",
				width : 340,
				height : 300,
				href : "pages/role/roleEditDlg.jsp",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#roleEditDlg_form");
					f.form("load", row);
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= rolegrid;
						var f = parent.$.modalDialog.handler.find("#roleEditDlg_form");
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
				msg : "请选择一条记录编辑!",
				timeout : 1000 * 2
			});
		}
	}
	
	//单击角色列表时触发
	function getPermission(rowIndex, rowData){
		$.post('<%=path %>/menuController/findAllMenuByRoleId', {roleId:rowData.roleId}, function(rsp) {
			menugrid.treegrid('unselectAll');
				if(rsp.length!=0){
		    	  $.each(rsp,function(i,e){
		    		  menugrid.treegrid('select',e.menuId);
		    	  });
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"该角色暂无权限,请分配权限!",
						timeout : 1000 * 2
					});
				}
			}, "JSON").error(function() {
				parent.$.messager.show({
					title :"提示",
					msg :"获取权限失败!",
					timeout : 1000 * 2
				});
			});
	}
	//保存角色权限
	function addRoleMenu(){
		var menu = menugrid.treegrid('getSelections');
		var role = rolegrid.datagrid('getSelected');
		if(menu.length!=0&&role){
			var menuIds=[];
			for(var o in menu){
				menuIds.push(menu[o].id);
			}
	    	 $.ajax({
		        	url:'<%=path %>/menuController/saveRoleMenu',
		        	data: {
		        		menuIds:menuIds.join(","),
		        		roleId:role.roleId
		        	},
					success: function(rsp){
						$.messager.show({
							title : "提示",
							msg : "保存权限成功!",
							timeout : 1000 * 2
						});
					}
		        }); 
		}else{
			$.messager.show({
				title : "提示",
				msg : "请选择角色和菜单后保存!",
				timeout : 1000 * 2
			});
		}
	}
</script>

<div class="body_left">
	<table id="roleManager_roleTable"></table>
</div>
<div class="body_right">
	<table id="roleManager_menuTable" ></table>
</div>

<div id="rolegrid_toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRole()">添加角色</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRole()">编辑角色</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRole()">删除角色</a>
</div>
<div id="menugrid_toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addRoleMenu()">保存选项</a>
	
</div>
