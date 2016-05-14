<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
<link rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/demo/demo.css">
<script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	// 先请求所有数据
	$(document).ready(function() {
		 $('#dg').datagrid({ 
			nowrap:true,  
            striped:true,  
            collapsible:true,
			//搜索前,触发此action请求所有用户信息
	        url:'<%=path %>/back/dep_findAll.action',   
	        loadMsg:'数据加载中......', 
	        fitColumns:true,//允许表格自动缩放,以适应父容器  
	        sortName:'departmentId',  
	        sortOrder:'asc',  
	        remoteSort:false,  
	        columns : [ [ {  
	            field : 'departmentId',  
	            width : 50,  
	            title : '部门编号'  
	        }, {  
	            field : 'departmentName',  
	            width : 50,  
	            align : 'left',  
	            title : '部门名称'  
	        }, {  
	            field : 'memo',  
	            width : 50,  
	            align : 'left',  
	            title : '部门描述'  
	        } ] ],
	        pagination : true,  
	        rownumbers : true
	    }); 
	});
	// 搜索
	function searchDepartment(){
		var s_id = $('#s_departmentId').val();
		var s_name = $('#s_departmentName').val();
		$('#dg').datagrid({
			url:'<%=path %>/back/dep_searchByKey.action?departmentId=' + s_id + "&departmentName=" + s_name,
			loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器  
            sortName:'departmentId',  
            sortOrder:'asc',  
            remoteSort:false,
            columns : [ [ {  
	            field : 'departmentId',  
	            width : 50,  
	            title : '部门编号'  
	        }, {  
	            field : 'departmentName',  
	            width : 50,  
	            align : 'left',  
	            title : '部门名称'  
	        }, {  
	            field : 'memo',  
	            width : 50,  
	            align : 'left',  
	            title : '部门描述'  
	        } ] ],
            pagination : true,  
            rownumbers : true
		});				
	}
</script>
</head>
<body>
<%--     url="<%=path %>/back/dep_findAll.action" --%>
    <table id="dg" title="部门列表" class="easyui-datagrid" style="width: auto; height: 465px;"
   	toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        &nbsp;部门编号:&nbsp;<input type="text" name="s_departmentId" id="s_departmentId" size="10" />
        &nbsp;部门名称:&nbsp;<input type="text" name="s_departmentName" id="s_departmentName" size="10" />
		<a href="javascript:searchDepartment()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:400px;height:285px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    <div class="ftitle">部门信息</div>
    <form id="fm" method="post" validate>
        <div class="fitem">
            <label>部门编号:</label><input id="departmentId" name="departmentId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>部门名称:</label><input name="departmentName" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>部门描述:</label><input name="memo" class="easyui-textbox">
        </div>
    </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','填写部门信息');
            $('#dg').datagrid('reload');
            $('#fm .fitem #departmentId').attr('disabled', false);
            $('#fm').form('clear');
            url = '<%=path %>/back/dep_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改部门信息');
                $('#fm').form('load',row);
                $('#fm .fitem #departmentId').attr('disabled', true);
                url = '<%=path %>/back/dep_updById.action?departmentId='+row.departmentId;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return true;
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                            $('#dlg').dialog('close');        // close the dialog
                            $('#dg').datagrid('reload');    // reload the user data
                        }
                    }
                });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','你确定要删除此部门?',
                  function(r){
                    if (r){
                        $.post('<%=path %>/back/dep_delById.action', 
                        		{departmentId : row.departmentId},
                        function(result, status){
                            if (status){
                                    $('#dg').datagrid('reload');    // reload the user data
                                } else {
                                    $.messager.show({    // show error message
                                        title: 'Error',
                                        msg: result.errorMsg
                                    });
                                }
                            },'json');
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
</body>
</html>