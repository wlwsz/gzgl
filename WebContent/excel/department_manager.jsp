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
</head>
<body>
        
    <table id="dg" title="部门列表" class="easyui-datagrid" style="width: auto; height: 465px;"
    url="<%=path %>/back/dep_findAll.action"
   	toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
       <thead>
           <tr>
           	   <th field="cb" checkbox="true" ></th>
               <th field="departmentId" width="50">部门编号</th>
               <th field="departmentName" width="50">部门名称</th>
               <th field="memo">部门描述</th>
           </tr>
       </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:400px;height:285px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    <div class="ftitle">部门信息</div>
    <form id="fm" method="post" novalidate>
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
            $('#fm').form('clear');
            url = '<%=path %>/back/dep_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改部门信息');
                $('#fm').form('load',row);
                $('#fm .fitem #departmentId').attr('disabled', true);
                url = '<%=path %>/back/dep_updById?departmentId='+row.departmentId;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
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