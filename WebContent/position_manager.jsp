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
  <script type="text/javascript">
//先请求所有数据
	 $(document).ready(function() {
		 $('#dg').datagrid({  
			 //搜索前,触发此action请求所有用户信息
	         url:'<%=path %>/back/pos_findAll.action',   
	         loadMsg:'数据加载中......',  
	         fitColumns:true,//允许表格自动缩放,以适应父容器 
	         sortName:'positionId',  
	         sortOrder:'asc',  
	         remoteSort:false,
	         columns : [ [ {  
	             field : 'positionId',  
	             width : 50,  
	             title : '职位编号'  
	         }, {  
	             field : 'positionName',  
	             width : 50,  
	             align : 'left',  
	             title : '职位名称'  
	         }, {  
	             field : 'basicWage',  
	             width : 50,  
	             align : 'left',  
	             title : '基本工资'  
	         }, {  
	             field : 'departmentId',  
	             width : 50, 
	             hidden : true,
	             align : 'left',  
	             title : '部门编号'  
	         }, {  
	             field : 'departmentName',  
	             width : 50, 
	             align : 'left',  
	             title : '所属部门'  
	         }, {  
	             field : 'memo',  
	             width : 50, 
	             align : 'left',  
	             title : '职位描述'  
	         } ] ],
	         pagination : true,  
	         rownumbers : true
	     }); 
	 });
	// 搜索
	function searchPosition(){
		var s_id = $('#s_positionId').val();
		var s_name1 = $('#s_positionName').val();
		var s_name2 = $('#s_departmentName').val();
		$('#dg').datagrid({
			url:'<%=path %>/back/pos_searchByKey.action?positionId=' + s_id + "&positionName=" + s_name1 + "&departmentName=" + s_name2,
			loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器  
         sortName:'positionId',  
         sortOrder:'asc',  
         remoteSort:false,
         columns : [ [ {  
             field : 'positionId',  
             width : 50,  
             title : '职位编号'  
         }, {  
             field : 'positionName',  
             width : 50,  
             align : 'left',  
             title : '职位名称'  
         }, {  
             field : 'basicWage',  
             width : 50,  
             align : 'left',  
             title : '基本工资'  
         }, {  
             field : 'departmentId',  
             width : 50, 
             hidden : true,
             align : 'left',  
             title : '部门编号'  
         }, {  
             field : 'departmentName',  
             width : 50, 
             align : 'left',  
             title : '所属部门'  
         }, {  
             field : 'memo',  
             width : 50, 
             align : 'left',  
             title : '职位描述'  
         } ] ],
         pagination : true,  
         rownumbers : true
		});				
	}
  </script>
</head>
<body>
    <table id="dg" title="职位列表" class="easyui-datagrid" style="width:auto;height:465px;"
        toolbar="#toolbar" pagination="true"
        rownumbers="true" fitColumns="true" singleSelect="true">
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        &nbsp;职位编号:&nbsp;<input type="text" name="s_positionId" id="s_positionId" size="10" />
        &nbsp;职位名称:&nbsp;<input type="text" name="s_positionName" id="s_positionName" size="10" />
        &nbsp;所属部门:&nbsp;<input type="text" name="s_departmentName" id="s_departmentName" size="10" />
		<a href="javascript:searchPosition()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:500px;height:350px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    <div class="ftitle">职位</div>
    <form id="fm" method="post" validate>
        <div class="fitem">
            <label>职位编号:</label>
            <input name="positionId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>职位名称:</label>
            <input name="positionName" class="easyui-textbox" required="true">
        </div>
      <!--  <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>基本工资:</label>
            <input id="basicWage" name="basicWage" type="number" class="easyui-textbox">
        </div> -->
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