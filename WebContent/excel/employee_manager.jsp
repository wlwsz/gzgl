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
<title>员工管理</title>
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
	    var param = ["employeeId", "name", "sex", "telephone", "email", "cardNumber", "birthday", "positionId", "positionName", "nation", "schoolRecord", "graduateSchool", "editTime", "memo"];
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		json = "["
		for(var i = 0; i < selectedRows.length; i++){
			json += "{\""+ param[0] + "\":\"" + selectedRows[i].employeeId + "\",\"" 
			+ param[1] +"\":\"" + selectedRows[i].name + "\",\""
			+ param[2] +"\":\"" + selectedRows[i].sex + "\",\""
			+ param[3] +"\":\"" + selectedRows[i].telephone + "\",\""
			+ param[4] +"\":\"" + selectedRows[i].email + "\",\""
			+ param[5] +"\":\"" + selectedRows[i].cardNumber + "\",\""
			+ param[6] +"\":\"" + selectedRows[i].birthday + "\",\""
			+ param[7] +"\":\"" + selectedRows[i].positionId + "\",\""
			+ param[8] +"\":\"" + selectedRows[i].positionName + "\",\""
			+ param[9] +"\":\"" + selectedRows[i].nation + "\",\""
			+ param[10] +"\":\"" + selectedRows[i].schoolRecord + "\",\""
			+ param[11] +"\":\"" + selectedRows[i].graduateSchool + "\",\""
			+ param[12] +"\":\"" + selectedRows[i].editTime + "\",\""
			+ param[13] +"\":\"" + selectedRows[i].memo + "\"},"	;
		}
		json = json.substring(0, json.lastIndexOf(","));
		json += "]";
		$.messager.confirm("系统提示","您确认要导出这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("<%=request.getContextPath()%>/back/exportExcel.action", {json:json, code: 2}, function(result){
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
        
    <table id="dg" title="员工列表" class="easyui-datagrid" style="width: 1300px; height: 465px;"
    url="<%=path %>/back/emp_findAll.action"
   	fitColumns="true" rownumbers="true" fit="true" pagination="true"  toolbar="#toolbar">
       <thead>
           <tr>
           	   <th field="cb" checkbox="true" ></th>
               <th field="employeeId" width="30">员工编号</th>
               <th field="name" width="20">姓名</th>
               <th field="sex" width="18">性别</th>
               <th field="cardNumber" width="60">身份证号</th>
               <th field="birthday" width="20">生日</th>
               <th field="positionId" hidden="true">职位编号</th>
               <th field="positionName" width="25">职位名称</th>
               <th field="nation" width="16">国籍</th>
               <th field="schoolRecord" width="15">学历</th>
               <th field="graduateSchool" width="30">毕业学校</th>
               <th field="telephone" width="32">手机号</th>
               <th field="email" width="50">邮箱</th>
               <th field="editTime" width="50">编辑时间</th>
               <th field="memo" width="50">描述信息</th>
           </tr>
       </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        <a href="javascript:exportExcel()" class="easyui-linkbutton" data-options="iconCls:'icon-excel'" plain="true">导出</a>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:400px;height:285px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    <div class="ftitle">员工信息</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>员工编号:</label><input id="employeeId" name="employeeId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>姓名:</label><input name="name" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>性别:</label><input name="sex" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>身份证号:</label><input name="cardNumber" class="easyui-textbox" >
        </div>
        <div class="fitem">
            <label>生日:</label><input name="birthday" class="easyui-textbox">
        </div>
        <div class="fitem">
            <label>手机号:</label><input name="telephone" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>邮箱:</label><input name="email" class="easyui-textbox" >
        </div>
        <div class="fitem">
            <label>学历:</label><input name="schoolRecord" class="easyui-textbox">
        </div>
        <div class="fitem">
            <label>毕业学校:</label><input name="graduateSchool" class="easyui-textbox">
        </div>
        <div class="fitem">
            <label>国籍:</label><input name="nation" class="easyui-textbox">
        </div>
        <!-- 选择职位 -->
        <div class="fitem">
            <div style="margin:20px 0"></div><label>请选择职位:</label>
            <select id="cc" style="width:165px;" name="positionId"></select>
            <div id="sp">
                <div style="color:#99BBE8;background:#fafafa;padding:5px;">请选择职位</div>
                <div id="items-input" style="padding:10px">
                   <!-- 动态添加元素 -->
                </div>
            </div>
        </div>
        <div class="fitem">
            <label>描述信息:</label><input name="schoolRecord" class="easyui-textbox">
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
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加信息');
            $('#fm').form('clear');
            url = '<%=path %>/back/emp_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改信息');
                $('#fm').form('load',row);
                $('#fm .fitem #employeeId').attr('disabled', true);
                url = '<%=path %>/back/emp_updById.action?employeeId='+row.employeeId;
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
                        $.post('<%=path %>/back/emp_delById.action', 
                        		{employeeId : row.employeeId},
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
        /** 动态获取职位 **/
        $(document).ready(function(){
            $.ajax({
                type: 'post',      
                url: '<%=path %>/back/pos_findAll.action',  
                cache: false,  
                dataType: 'json',  
                success: function(data){  
                	if(data.length == 0) {
                		alert("职位不存在");
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