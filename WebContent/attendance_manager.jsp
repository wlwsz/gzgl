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
  <title>考勤管理模块</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/color.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/demo/demo.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/WdatePicker-res/skin/WdatePicker.css">
  <script type="text/javascript" src="<%=path %>/WdatePicker-res/WdatePicker.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript">
	//先请求所有数据
	$(document).ready(function() {
	    $('#dg').datagrid({  
	           //搜索前,触发此action请求所有用户信息
	           url:'<%=path %>/back/att_findAll.action',   
	           loadMsg:'数据加载中......',  
	           fitColumns:true,//允许表格自动缩放,以适应父容器  
	           sortName:'employeeId',  
	           sortOrder:'asc',  
	           remoteSort:false,  
	           columns : [ [ { field : 'attendanceId', hidden : true, title : '编号' }, { field : 'employeeId', width : 50, align : 'left', title : '员工编号'  
	           }, { field : 'employeeName', width : 50, align : 'left', title : '姓名' }, { field : 'year', width : 37, align : 'left', title : '考勤年份'  
	           }, { field : 'month', width : 37, align : 'left', title : '考勤月份' }, { field : 'overHour', width : 35, align : 'left', title : '加班'  
	           }, { field : 'chidao', width : 35, align : 'left', title : '迟到' }, { field : 'zaotui', width : 35, align : 'left', title : '早退'  
	           }, { field : 'kuangGong', width : 35, align : 'left', title : '旷工' }, { field : 'memo', width : 50, align : 'left', title : '说明'  
	           } ] ],
	           pagination : true,  
	           rownumbers : true
	       }); 
	});
	// 搜索
    function searchAttendance(){
        var s_id = $('#s_employeeId').val();
 		var s_year = $('#s_year').val();
 		var s_month = $('#s_month').val();
         $('#dg').datagrid({
              url:'<%=path %>/back/att_searchByKey.action?employeeId=' + s_id + "&year=" + s_year + "&month=" + s_month,
              loadMsg:'数据加载中......',
              fitColumns:true,//允许表格自动缩放,以适应父容器  
              sortName:'employeeId',  
              sortOrder:'asc',  
              remoteSort:false,
              columns : [ [ { field : 'attendanceId', hidden : true, title : '编号' }, { field : 'employeeId', width : 50, align : 'left', title : '员工编号'  
	           }, { field : 'employeeName', width : 50, align : 'left', title : '姓名' }, { field : 'year', width : 37, align : 'left', title : '考勤年份'  
	           }, { field : 'month', width : 37, align : 'left', title : '考勤月份' }, { field : 'overHour', width : 35, align : 'left', title : '加班'  
	           }, { field : 'chidao', width : 35, align : 'left', title : '迟到' }, { field : 'zaotui', width : 35, align : 'left', title : '早退'  
	           }, { field : 'kuangGong', width : 35, align : 'left', title : '旷工' }, { field : 'memo', width : 50, align : 'left', title : '说明'  
	           } ] ],
             pagination : true,  
             rownumbers : true
        });                 
    }
  </script>
</head>
<body>
   <table id="dg" title="考情列表" class="easyui-datagrid" style="width:auto;height:465px;"
        toolbar="#toolbar" pagination="true"
        rownumbers="true" fitColumns="true" singleSelect="true">
   </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        &nbsp;&nbsp;&nbsp;员工编号:&nbsp;<input id="s_employeeId" name="s_employeeId" type="text" size="10" />
        &nbsp;年份:&nbsp;<input id="s_year" name="s_year" class="Wdate" style="width:80px;height:15px" type="text" onClick="WdatePicker({dateFmt:'yyyy', maxDate:'%y'})">
        &nbsp;月份:&nbsp;<input id="s_month" name="s_month" class="Wdate" style="width:80px;height:15px" type="text" onClick="WdatePicker({dateFmt:'MM', maxDate:'%y-%M'})">
		<a href="javascript:searchAttendance()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:500px;height:350px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    <div class="ftitle">考勤表</div>
    <form id="fm" method="post" validate>
        <input name="attendanceId" style="display:none;" hidden="true">
        <div id="name_input" class="fitem">
            <div style="margin:3px 0"></div><label>员工姓名:</label>
            <input name="employeeName" class="easyui-textbox" disabled="disabled">
        </div>
        <div id="select_option" class="fitem">
            <div style="margin:3px 0"></div><label>选择员工:</label>
            <select id="cc" style="width:165px;" name="employeeId" required="required"></select>
            <div id="sp">
                <div style="color:#99BBE8;background:#fafafa;padding:5px;">选择员工</div>
                <div id="items-input" style="padding:10px">
                   <!-- 动态添加元素 -->
                </div>
            </div>
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>加班:</label>
            <input name="overHour" type="number" value="0" class="easyui-textbox">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>迟到:</label>
            <input name="chidao" type="number" value="0" class="easyui-textbox">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>早退:</label>
            <input name="zaotui" type="number" value="0" class="easyui-textbox">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>旷工:</label>
            <input name="kuangGong" type="number" value="0" class="easyui-textbox">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>说明:</label>
            <input name="memo" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>年份:</label>
            <input name="year" class="Wdate" style="width:80px;height:15px" type="text" onClick="WdatePicker({dateFmt:'yyyy', maxDate:'%y'})" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>月份:</label>
            <input name="month" class="Wdate" style="width:80px;height:15px" type="text" onClick="WdatePicker({dateFmt:'MM', maxDate:'%y-%M'})" required="required">
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
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','填写销售信息');
            $('#fm').form('clear');
            $('#fm #name_input').attr('hidden', true);
            $('#fm #select_option').attr('hidden', false);
            url = '<%=path %>/back/att_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改销售信息');
                $('#fm #name_input').attr('hidden', false);
                $('#fm #select_option').attr('hidden', true);
                $('#fm .fitem #employeeId').attr('hidden', false);
                $('#fm').form('load',row);
                url = '<%=path %>/back/att_updById.action?attendanceId=' + row.attendanceId;
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
                $.messager.confirm('Confirm','你确定要删除这条考勤记录?',function(r){
                    if (r){
                        $.post('<%=path %>/back/att_delById.action', 
                        	{ attendanceId : row.attendanceId }, 
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