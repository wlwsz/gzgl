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
  <title>销售管理模块</title>
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
	           url:'<%=path %>/back/sel_findAll.action',   
	           loadMsg:'数据加载中......',  
	           fitColumns:true,//允许表格自动缩放,以适应父容器  
	           sortName:'sellId',  
	           sortOrder:'asc',  
	           remoteSort:false,  
	           columns : [ [ { field : 'sellId', width : 50, align : 'left', title : '销售编号' }, { field : 'employeeId', hidden : true, title : '员工编号' 
	        		}, { field : 'employeeName', width : 50, align : 'left', title : '销售人' }, { field : 'sellMoney', width : 50, align : 'left', title : '销售金额(元)'  
	           		}, { field : 'sellYear', width : 50, align : 'left', title : '销售年份' }, { field : 'sellMonth', width : 50, align : 'left', title : '销售月份'  
	           		}, { field : 'memo', width : 50, align : 'left', title : '评价'  
		        } ] ],
	           pagination : true,  
	           rownumbers : true
	       }); 
	});
	//搜索
	function searchSell(){
	    var s_name = $('#s_employeeName').val();
		var s_year = $('#s_year').val();
		var s_month = $('#s_month').val();
	    $('#dg').datagrid({
	          url:'<%=path %>/back/sel_searchByKey.action?employeeName=' + s_name + "&year=" + s_year + "&month=" + s_month,
	      loadMsg:'数据加载中......',
	      fitColumns:true,//允许表格自动缩放,以适应父容器  
	      sortName:'sellId',  
	      sortOrder:'asc',  
	      remoteSort:false,
	      columns : [ [ { field : 'sellId', width : 50, align : 'left', title : '销售编号' }, { field : 'employeeId', hidden : true, title : '员工编号' 
  			},  { field : 'employeeName', width : 50, align : 'left', title : '销售人' }, { field : 'sellMoney', width : 50, align : 'left', title : '销售金额(元)'  
     		},  { field : 'sellYear', width : 50, align : 'left', title : '销售年份' }, { field : 'sellMonth', width : 50, align : 'left', title : '销售月份' 
     	  	}, { field : 'memo', width : 50, align : 'left', title : '评价'  
	        } ] ],
	     pagination : true,  
	     rownumbers : true
	});                 
}
  </script>
</head>
<body>
        
<%--  url="<%=path %>/back/sel_findAll.action" --%>
    <table id="dg" title="销售列表" class="easyui-datagrid" style="width:auto;height:465px;"
        toolbar="#toolbar" pagination="true"
        rownumbers="true" fitColumns="true" singleSelect="true">
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        &nbsp;&nbsp;&nbsp;销售人:&nbsp;<input id="s_employeeName" name="s_employeeName" type="text" size="10" />
        &nbsp;年份:&nbsp;<input id="s_year" name="s_year" class="Wdate" style="width:80px;height:15px" type="text" onClick="WdatePicker({dateFmt:'yyyy', maxDate:'%y'})">
        &nbsp;月份:&nbsp;<input id="s_month" name="s_month" class="Wdate" style="width:80px;height:15px" type="text" onClick="WdatePicker({dateFmt:'MM', maxDate:'%y-%M'})">
		<a href="javascript:searchSell()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:500px;height:350px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
    <div class="ftitle">销售信息</div>
    <form id="fm" method="post" validate>
        <div class="fitem">
            <label>销售编号:</label>
            <input name="sellId" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>销售金额:</label>
            <input name="sellMoney" id="sellMoney" type="number"  class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <div style="margin:20px 0"></div><label>选择销售人:</label>
            <select id="cc" style="width:165px;" name="employeeId"></select>
            <div id="sp">
                <div style="color:#99BBE8;background:#fafafa;padding:5px;">选择销售人</div>
                <div id="items-input" style="padding:10px">
                   <!-- 动态添加元素 -->
                </div>
            </div>
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>销售年份:</label>
            <input name="sellYear" class="Wdate" style="width:80px;height:15px" type="text" onClick="WdatePicker({dateFmt:'yyyy', maxDate:'%y'})" required="required">
        </div>
        <div class="fitem">
            <div style="margin:15px 0"></div>
            <label>销售月份:</label>
            <input name="sellMonth" class="Wdate" style="width:80px;height:15px" type="text" onClick="WdatePicker({dateFmt:'MM', maxDate:'%y-%M'})" required="required">
        </div>
        <div class="fitem">
            <label>销售评价:</label><input name="memo" class="easyui-textbox">
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
            url = '<%=path %>/back/sel_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改销售信息');
                $('#fm').form('load',row);
                /* $('#fm .fitem #departmentId').attr('disabled', 'disabled'); */
                url = '<%=path %>/back/sel_updById.action?sellId=' + row.sellId;
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
                $.messager.confirm('Confirm','你确定要删除此销售信息?',function(r){
                    if (r){
                        $.post('<%=path %>/back/sel_delById.action', 
                        	{ sellId : row.sellId }, 
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