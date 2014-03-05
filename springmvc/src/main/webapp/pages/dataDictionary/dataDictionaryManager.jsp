<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
	var dataDicGrid;
	$(function(){
		dataDicGrid = $("#dataDictionaryManager_table");
		dataDicGrid.treegrid({    
		    url:'<%=path%>/dataDictionaryController/findAllDataDictionary',
			idField:'dicId',
			treeField:'dicName',
		 	parentField:'dicPid',
			rownumbers:false,
			fitColumns : true,
			border : false,
			collapseAll:true,
		    fit:true,
		    columns:[[    
				{title:'id',field:'dicId',width:250,align:'left',hidden:true},
		        {title:'名称',field:'dicName',width:250,align:'left'},    
		        {title:'标示',field:'dicMark',width:250,align:'left'},    
		        {title:'备注',field:'dicDesc',width:250,align:'center'}
		    ]],
		    onContextDataDictionary : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#DataDictionary').DataDictionary('show', {
					left : e.pageX,
					top : e.pageY
				});
			},toolbar:'#DataDictionary_toolbar'

		}); 
	});

	//添加主节点
	function addDataDictionary(){
		parent.$.modalDialog({
			title : "添加主节点",
			width : 440,
			height : 300,
			href : "pages/dataDictionary/dataDictionaryAddDlg.jsp",
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$.modalDialog.openner= dataDicGrid;
					var f = parent.$.modalDialog.handler.find("#dataDictionaryAddDlg_form");
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
	//编辑数据字典
	function editDataDictionary(){
		var row=dataDicGrid.treegrid('getSelected');
		if(row){
			if(row.dicMark!=null){
				//选择主菜单不操作
			}else{
				parent.$.modalDialog({
					title : "编辑节点",
					width : 440,
					height : 300,
					href : "pages/dataDictionary/dataDictionaryEditDlg.jsp",
					onLoad:function(){
						var f = parent.$.modalDialog.handler.find("#dataDictionaryEditDlg_form");
						f.form("load", row);
					},
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= dataDicGrid;
							var f = parent.$.modalDialog.handler.find("#dataDictionaryEditDlg_form");
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
				msg : "请选择一个节点后进行编辑!",
				timeout : 1000 * 2});
		}
		
	}
	//增加子节点
	function addChildDataDictionary(){
		var row=dataDicGrid.treegrid('getSelected');
		if(row){
			if(row.dicMark!=null){
					parent.$.modalDialog({
						title : "添加子节点",
						width : 440,
						height : 300,
						href : "pages/dataDictionary/dataDictionaryChildAddDlg.jsp",
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#dataDictionaryChildAddDlg_form");
							f.form("load", {dicPid:row.dicId});
						},
						buttons : [ {
							text : '保存',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= dataDicGrid;
								var f = parent.$.modalDialog.handler.find("#dataDictionaryChildAddDlg_form");
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
					msg : "请选择一个节点后添加子节点!",
					timeout : 1000 * 2});
			}
			
		
	}
	//删除节点
	function deleteDataDictionary(){
		var row=dataDicGrid.treegrid('getSelected');
		if(row){
			if(row.dicMark!=null){
				//不操作
			}else{
				$.messager.confirm('确认','您确认想要删除该节点吗？',function(r){    
				    if (r){    //确认删除
						$.ajax({
							url:'<%=path%>/dataDictionaryController/deleteDataDictionary',
							data:'dicId='+row.dicId,
							dataType:'json',
							success:function(r){
								if(r.status=='true'){
									$.messager.show({
										title : "提示",
										msg : "删除成功!",
										timeout : 1000 * 2});
								}
								dataDicGrid.treegrid('reload');	// 重新载入所有行
							},
						});
				    }    
				});
			}
		}else{
			$.messager.show({
				title : "提示",
				msg : "请选择一个节点后删除!",
				timeout : 1000 * 2});
		}
	}
</script>
<table id="dataDictionaryManager_table"></table>
<div id="DataDictionary" class="easyui-DataDictionary" style="width: 120px; display: none;">	
	<div onclick="addChildDataDictionary();" data-options="iconCls:'pencil_add'">增加子节点</div>
	<div onclick="editDataDictionary();" data-options="iconCls:'pencil'">编辑节点</div>
	<div onclick="deleteDataDictionary();" data-options="iconCls:'pencil_delete'">删除节点</div>
	<div onclick="addDataDictionary();" data-options="iconCls:'pencil_add'">增加主节点</div>
</div>
<div id="DataDictionary_toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addDataDictionary();">增加主节点</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addChildDataDictionary();">增加子节点</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editDataDictionary();">编辑节点</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteDataDictionary();">删除节点</a>
</div>