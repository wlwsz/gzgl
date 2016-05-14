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
  <title>扣税管理模块</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/color.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/demo/demo.css">
  <script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
</head>
<body>
        
        <table id="dg" title="全部列表" class="easyui-datagrid" style="width:auto;height:465px;"
        url="<%=path %>/back/ded_findAll.action"
        toolbar="#toolbar" pagination="true"
        rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
            	<th field="cb" checkbox="true" ></th>
                <th field="deductionId" hidden="true">扣税编号</th>
                <th field="positionId" hidden="true">职位编号</th>
                <th field="positionName" width="50">职位名称</th>
                <th field="kaoqinReduce" width="50">考勤扣除</th>
                <th field="secureReduce" width="50">五险一金</th>
                <!-- <th field="trafficWage" width="50">交通补助</th> -->
                <th field="taxReduce" width="50">其他扣除</th>
                <th field="totalReduce" width="50">扣除总额</th>
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
    <div class="ftitle">扣税</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <div style="margin:10px 0"></div><label>请选择职位</label>
            <select id="cc" style="width:165px;" name="positionId"></select>
            <div id="sp">
                <div style="color:#99BBE8;background:#fafafa;padding:5px;">请选择职位</div>
                <div id="items-input" style="padding:10px">
                   <!-- 动态添加元素 -->
                </div>
            </div>
        </div>
        <div class="fitem">
            <label>考勤扣除:</label>
            <input name="kaoqinReduce" type="number" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>五险一金:</label>
            <input name="secureReduce" type="number" class="easyui-textbox" required="true">
        </div>
        <!-- <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>交通补助:</label>
            <input name="trafficWage" type="number" required="true" class="easyui-textbox">
        </div> -->
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>税收扣除:</label>
            <input name="taxReduce" type="number" class="easyui-textbox" required="true">
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
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','填写扣税信息');
            $('#fm').form('clear');
            url = '<%=path %>/back/ded_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改扣税信息');
                $('#fm').form('load',row);
                url = '<%=path %>/back/ded_updById.action?deductionId=' + row.deductionId;
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
                $.messager.confirm('Confirm','你确定要删除此扣税记录?',function(r){
                    if (r){
                        $.post('<%=path %>/back/ded_delById.action', 
                        	{ deductionId : row.deductionId }, 
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
                url: '<%=path %>/back/pos_findAll.action',  
                cache: false,  
                dataType: 'json',  
                success: function(data){  
                	if(data.length == 0) {
                		alert("没有职位");
                		return ;
                	}
                    for(var i=0; i < data.length; i++) {
                    	/* alert(data.length); */
                		if(i != data.length-1) {
                 			$('#sp #items-input').append("<input id='positionId' name='positionId' type='radio' value='" + data[i].positionId + "'><span>"+data[i].positionName+"</span><br/>");
                   			continue;
                		}
             			$('#sp #items-input').append("<input id='positionId' name='positionId' type='radio' value='" + data[i].positionId + "'><span>"+data[i].positionName+"</span>");
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