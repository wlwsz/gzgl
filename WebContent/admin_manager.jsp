<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>

<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/demo/demo.css">
<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	 // 先请求所有数据
	 $(document).ready(function() {
		 $('#dg').datagrid({  
			 //搜索前,触发此action请求所有用户信息
	         url:'<%=path %>/back/adm_findAll.action',   
	         loadMsg:'数据加载中......',  
	         fitColumns:true,//允许表格自动缩放,以适应父容器  
	         sortName:'username',  
	         sortOrder:'asc',  
	         remoteSort:false,  
	         columns : [ [ {  
	             field : 'username',  
	             width : 50,  
	             title : '用户名称'  
	         }, {  
	             field : 'password',  
	             width : 50,  
	             align : 'left',  
	             title : '密码'  
	         }, {  
	             field : 'iconPath',  
	             width : 50,  
	             align : 'left',  
	             title : '头像地址'  
	         } ] ],
	         pagination : true,  
	         rownumbers : true
	     }); 
	 });
	// 搜索
	function searchAdmin(){
		var s_name = $('#s_username').val();
		$('#dg').datagrid({
			url:'<%=path %>/back/adm_searchByName.action?username=' + s_name,
			loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器  
            sortName:'username',  
            sortOrder:'asc',  
            remoteSort:false,
            columns : [ [ {  
                field : 'username',  
                width : 50,  
                title : '用户名称'  
            }, {  
                field : 'password',  
                width : 50,  
                align : 'left',  
                title : '密码'  
            }, {  
                field : 'iconPath',  
                width : 50,  
                align : 'left',  
                title : '头像地址'  
            } ] ] ,
            pagination : true,  
            rownumbers : true
		});				
	}
	
</script>
</head>
<body>
<%-- 	url="<%=path %>/back/adm_findAll.action" --%>
	<table id="dg" title="用户列表" class="easyui-datagrid" style="width: auto; height: 465px;" 
	toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
		&nbsp;&nbsp;&nbsp;用户名:&nbsp;<input type="text" name="s_username" id="s_username" size="10" />
		<a href="javascript:searchAdmin()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a>
	</div>

	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">用户信息</div>
		<form id="fm" method="post" validate>
			<div class="fitem">
				<label>username:</label> 
				<input id="username" name="username" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>password:</label> <!-- class="easyui-textbox" -->
				<input name="password" type="password" class="easyui-textbox" required="true">
			</div>
			 <div class="fitem">
				<!-- <label>iconPath:</label>  -->
				<input name="iconPath" hidden="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width: 90px">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width: 90px">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function newUser() {
			$('#dlg').dialog('open').dialog('center').dialog('setTitle',
					'添加用户');
			$('#fm .fitem #username').removeAttr('disabled');
			$('#fm .fitem #username').attr('required', true);
			$('#fm').form('clear');
			url = '<%=path%>/back/adm_add.action';
		}
		function editUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('center').dialog('setTitle', '编辑用户');
				$('#fm').form('load', row);
				$('#fm .fitem #username').removeAttr('required');
				$('#fm .fitem #username').attr('disabled', 'disabled'); 
				url = '<%=path%>/back/adm_updPassword.action?username='+ row.username; /* ?username= + row.username +&password= + row.password + &iconPath= + row.iconPath */
			}
		}
		function saveUser() {
			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return true;
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyUser() {
			var row = $('#dg').datagrid('getSelected');
			if(row.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
				return;
			}
			if (row) {
				$.messager.confirm('Confirm',
						'确定要删除此用户?',
						function(r) {
							if (r) {
								$.post('<%=path%>/back/adm_delByName.action', {
									username : row.username
								}, function(result, status) {
									if (status) {
										$('#dg').datagrid('reload'); // reload the user data
									} else {
										$.messager.show({ // show error message
											title : 'Error',
											msg : result.errorMsg
										});
									}
								}, 'json');
							}
						});
			}
		}
	</script>
	<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}
</style>
</body>
</html>