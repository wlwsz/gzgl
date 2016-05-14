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
  <title>工资管理模块</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/color.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/demo/demo.css">
  <script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
</head>
<body>
        
        <table id="dg" title="工资列表" class="easyui-datagrid" style="width:auto;height:465px;"
        url="<%=path %>/back/sal_findAll.action"
        toolbar="#toolbar" pagination="true"
        rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="salaryId" hidden="true">编号</th>
                <th field="employeeId" width="40">员工编号</th>
                <th field="employeeName" width="40">姓名</th>
                <th field=basicWage width="40">基本工资</th>
                <th field="overtimeWage" width="40">加班工资</th>
                <th field="sellmoneyGet" width="40">提成</th>
                <th field="totalWage" width="40">总工资</th>
                <th field="totalReduce" width="40">扣税</th>
                <th field="realWage" width="40">实际工资</th>
                <th field="month" width="40">月份</th>
                <th field="editTime" width="40">编辑时间</th>
                <th field="memo" width="40">说明</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:500px;height:350px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    <div class="ftitle">工资表</div>
    <form id="fm" method="post" novalidate>
        <input name="salaryId" style="display:none;" hidden="true">
        <div class="fitem">
            <div style="margin:3px 0"></div><label>选择员工:</label>
            <select id="cc" style="width:165px;" name="employeeId"></select>
            <div id="sp">
                <div style="color:#99BBE8;background:#fafafa;padding:5px;">选择员工</div>
                <div id="items-input" style="padding:10px">
                   <!-- 动态添加元素 -->
                </div>
            </div>
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>基本工资:</label>
            <input name="basicWage" type="number"  class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>加班费:</label>
            <input name="overtimeWage" type="number" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>提成:</label>
            <input name="sellmoneyGet" type="number" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>说明:</label>
            <input name="memo" class="easyui-textbox">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>月份:</label>
            <input class="easyui-datebox" name="month" required="true" />
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
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','填写信息');
            $('#fm').form('clear');
            url = '<%=path %>/back/sal_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改信息');
                $('#fm').form('load',row);
                $('#fm .fitem #departmentId').attr('disabled', 'disabled');
                url = '<%=path %>/back/sal_updById?salaryId=' + row.salaryId;
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
                $.messager.confirm('Confirm','你确定要删除这条工资记录?',function(r){
                    if (r){
                        $.post('<%=path %>/back/sal_delById.action', 
                        	{ salaryId : row.salaryId }, 
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
        
        $(document).ready(function(){
            $.ajax({
                type: 'post',      
                url: '<%=path %>/back/emp_findAll.action',  
                cache: false,  
                dataType: 'json',  
                success: function(data){  
                	if(data.length == 0) {
                		alert("没有员工！");
                		return ;
                	}
                    for(var i=0; i < data.length; i++) {
                    	/* alert(data.length); */
                		if(i != data.length-1) {
                 			$('#sp #items-input').append("<input id='employeeId' name='employeeId' type='radio' value='" + data[i].employeeId + "'><span>"+data[i].name+"</span><br/>");
                   			continue;
                		}
             			$('#sp #items-input').append("<input id='employeeId' name='employeeId' type='radio' value='" + data[i].employeeId + "'><span>"+data[i].name+"</span>");
            		}
                    $('#cc').combo({
                        required:true,
                        editable:false
                    });
                    $('#sp').appendTo($('#cc').combo('panel'));
                    $('#sp input').click(function(){
                        var v = $(this).val();
                        var s = $(this).next('span').text();
                        $('#cc').combo('setValue', v).combo('setText', s).combo('hidePanel');
                    });
                } 
            });
           
        });
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