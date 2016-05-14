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
  <title>职位管理模块</title>
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/color.css">
  <link rel="stylesheet" type="text/css" href="<%=path %>/easyui/demo/demo.css">
  <script type="text/javascript" src="<%=path %>/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript">
	//导出Excel数据
	function exportExcel(){
			var selectedRows = $("#dg").datagrid('getSelections');
			var json;
		    var param = ["positionId", "positionName", "basicWage", "departmentId", "departmentName", "memo"];
			if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
				return;
			}
			json = "["
			for(var i = 0; i < selectedRows.length; i++){
				json += "{\""+ param[0] + "\":\"" + selectedRows[i].positionId + "\",\"" 
				+ param[1] +"\":\"" + selectedRows[i].positionName + "\",\""
				+ param[2] +"\":\"" + selectedRows[i].basicWage + "\",\""
				+ param[3] +"\":\"" + selectedRows[i].departmentId + "\",\""
				+ param[4] +"\":\"" + selectedRows[i].departmentName + "\",\""
				+ param[5] +"\":\"" + selectedRows[i].memo + "\"},"	;
			}
			json = json.substring(0, json.lastIndexOf(","));
			json += "]";
			$.messager.confirm("系统提示","您确认要导出这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
				if(r){
					$.post("<%=request.getContextPath()%>/back/exportExcel.action", {json:json, code: 1}, function(result){
						if(result.success){
							$.messager.alert("系统提示","<font color='green'>"+ result.msg+"</font>");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示",'<font color=red>'+result.errorMsg+'</font>');
						}
					},"json");
				}
			});
		}
  </script>
</head>
<body>
       <table id="dg" title="职位列表" class="easyui-datagrid" style="width:auto;height:465px;"
        url="<%=path %>/back/pos_findAll.action"
         fitColumns="true" rownumbers="true" fit="true" pagination="true"  toolbar="#toolbar">
        <thead>
            <tr>
            	<th field="cb" checkbox="true" ></th>
                <th field="positionId" width="50">职位编号</th>
                <th field="positionName" width="50">职位名称</th>
                <th field="basicWage" width="50">基本工资</th>
                <th field="departmentId" hidden="true">部门编号</th>
                <th field="departmentName" width="50">所属部门</th>
                <th field="memo" width="50">职位描述</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        <a href="javascript:exportExcel()" class="easyui-linkbutton" data-options="iconCls:'icon-excel'" plain="true">导出</a>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:500px;height:350px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    <div class="ftitle">职位</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>职位编号:</label>
            <input name="positionId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>职位名称:</label>
            <input name="positionName" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>基本工资:</label>
            <input id="basicWage" name="basicWage" type="number" class="easyui-textbox">
        </div>
        <div class="fitem">
            <div style="margin:20px 0"></div><label>请选择部门</label>
            <select id="cc" style="width:165px;" name="departmentId"></select>
            <div id="sp">
                <div style="color:#99BBE8;background:#fafafa;padding:5px;">请选择部门</div>
                <div id="items-input" style="padding:10px">
                   <!-- 动态添加元素 -->
                </div>
            </div>
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>职位描述:</label>
            <input name="memo" type="text" class="easyui-textbox">
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
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','填写职位信息');
            $('#fm').form('clear');
            url = '<%=path %>/back/pos_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改职位');
                $('#fm').form('load',row);
                $('#fm .fitem #departmentId').attr('disabled', 'disabled');
                url = '<%=path %>/back/pos_updById.action?positionId='+row.positionId;
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
                $.messager.confirm('Confirm','你确定要删除此部门?',function(r){
                    if (r){
                        $.post('<%=path %>/back/pos_delById.action', 
                        	{ positionId : row.positionId }, 
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
                url: '<%=path %>/back/dep_findAll.action',  
                cache: false,  
                dataType: 'json',  
                success: function(data){  
                	if(data.length == 0) {
                		alert("先创建部门");
                		return ;
                	}
                    for(var i=0; i < data.length; i++) {
                    	/* alert(data.length); */
                		if(i != data.length-1) {
                 			$('#sp #items-input').append("<input id='departmentId' name='departmentId' type='radio' value='" + data[i].departmentId + "'><span>"+data[i].departmentName+"</span><br/>");
                   			continue;
                		}
             			$('#sp #items-input').append("<input id='departmentId' name='departmentId' type='radio' value='" + data[i].departmentId + "'><span>"+data[i].departmentName+"</span>");
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