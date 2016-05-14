<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工管理</title>
<link rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/themes/color.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/easyui/demo/demo.css">
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	//先请求所有数据
	$(document).ready(function() {
		 $('#dg').datagrid({  
			 //搜索前,触发此action请求所有用户信息
	        url:'<%=path%>/back/emp_findAll.action',   
	        loadMsg:'数据加载中......',  
	        fitColumns:true,//允许表格自动缩放,以适应父容器  
	        sortName:'employeeId',  
	        sortOrder:'asc',  
	        remoteSort:false,  
	        columns : [ [ { field : 'employeeId', width : 40, title : '员工编号'}, { field : 'name', width : 30, align : 'left', title : '姓名'  
	        }, { field : 'sex', width : 15, align : 'left', title : '性别' }, { field : 'cardNumber', width : 80, align : 'left', title : '身份证号'  
	        }, { field : 'birthday', width : 40, align : 'left', title : '生日' }, { field : 'positionId', hidden : true, title : '职位编号'
	        }, { field : 'positionName', width : 35, align : 'left', title : '职位名称' }, { field : 'nation',  width : 23, align : 'left', title : '国籍'  
	        }, { field : 'schoolRecord', width : 23, align : 'left', title : '学历' }, { field : 'graduateSchool', width : 50, align : 'left', title : '毕业学校'  
	        }, { field : 'telephone', width : 40, align : 'left', title : '手机号' }, { field : 'email', width : 60, align : 'left', title : '邮箱'  
	        }, { field : 'editTime', width : 40, align : 'left', title : '编辑时间' }, { field : 'memo', width : 60, align : 'left', title : '描述信息'  
	        } ] ],
	        pagination : true,  
	        rownumbers : true
	    }); 
	});
	// 搜索
	function searchEmployee(){
		var s_id = $('#s_employeeId').val();
		var s_name = $('#s_name').val();
		$('#dg').datagrid({
			url:'<%=path %>/back/emp_searchByKey.action?employeeId=' + s_id + "&name=" + s_name,
			loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器  
            sortName:'employeeId',  
            sortOrder:'asc',  
            remoteSort:false,
            columns : [ [ { field : 'employeeId', width : 40, title : '员工编号'}, { field : 'name', width : 30, align : 'left', title : '姓名'  
	        }, { field : 'sex', width : 12, align : 'left', title : '性别' }, { field : 'cardNumber', width : 80, align : 'left', title : '身份证号'  
	        }, { field : 'birthday', width : 40, align : 'left', title : '生日' }, { field : 'positionId', hidden : true, title : '职位编号'
	        }, { field : 'positionName', width : 35, align : 'left', title : '职位名称' }, { field : 'nation',  width : 23, align : 'left', title : '国籍'  
	        }, { field : 'schoolRecord', width : 23, align : 'left', title : '学历' }, { field : 'graduateSchool', width : 50, align : 'left', title : '毕业学校'  
	        }, { field : 'telephone', width : 40, align : 'left', title : '手机号' }, { field : 'email', width : 60, align : 'left', title : '邮箱'  
	        }, { field : 'editTime', width : 40, align : 'left', title : '编辑时间' }, { field : 'memo', width : 60, align : 'left', title : '描述信息'  
	        } ] ],
            pagination : true,  
            rownumbers : true
		});				
	}

	// 格式化日期选择框的日期显示格式
	function myformatter(date){
	    var y = date.getFullYear();
	    var m = date.getMonth()+1;
	    var d = date.getDate();
	    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	}
	function myparser(s){
	    if (!s) return new Date();
	    var ss = (s.split('-'));
	    var y = parseInt(ss[0],10);
	    var m = parseInt(ss[1],10);
	    var d = parseInt(ss[2],10);
	    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
	        return new Date(y,m-1,d);
	    } else {
	        return new Date();
	    }
	}
</script>
</head>
<body>
	<table id="dg" title="员工列表" class="easyui-datagrid"
		style="width: 1300px; height: 465px;" toolbar="#toolbar"
		pagination="true" rownumbers="true" fitColumns="true"
		singleSelect="true">
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
		&nbsp;&nbsp;&nbsp;员工编号:&nbsp;<input type="text" name="s_employeeId" id="s_employeeId" size="10" /> 
		&nbsp;&nbsp;姓名:&nbsp;<input type="text" name="s_name" id="s_name" size="10" />
		<a href="javascript:searchEmployee()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a>
	</div>

	<div id="dlg" class="easyui-dialog" style="width: 480px; height: 480px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
		<div class="ftitle">员工信息</div>
		<form id="fm" method="post" validate>
			<div class="fitem">
				<label>员工编号:</label>
				<input id="employeeId" name="employeeId" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>姓名:</label>
				<input name="name" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>性别:</label>
				<input name="sex" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>身份证号:</label>
				<input name="cardNumber" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>生日:</label>
				<input name="birthday" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser">
			</div>
			<div class="fitem">
				<label>手机号:</label>
				<input name="telephone" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>邮箱:</label>
				<input name="email" class="easyui-textbox" validType="email">
			</div>
			<div class="fitem">
				<label>学历:</label>
				<input name="schoolRecord" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>毕业学校:</label>
				<input name="graduateSchool" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>国籍:</label>
				<input name="nation" class="easyui-textbox">
			</div>
			<!-- 选择职位 -->
			<div class="fitem">
				<div style="margin: 20px 0"></div>
				<label>请选择职位:</label> 
				<select id="cc" style="width: 165px;" name="positionId"></select>
				<div id="sp">
					<div style="color: #99BBE8; background: #fafafa; padding: 5px;">请选择职位</div>
					<div id="items-input" style="padding: 10px">
						<!-- 动态添加元素 -->
					</div>
				</div>
			</div>
			<div class="fitem">
				<label>描述信息:</label>
				<input name="memo" class="easyui-textbox">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width: 90px">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width: 90px">取消</a>
	</div>
	<script type="text/javascript">
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加信息');
            $('#fm').form('clear');
            $('#fm .fitem #employeeId').attr('disabled', false);
            $('#fm .fitem #employeeId').attr('required', true);
            
            url = '<%=path%>/back/emp_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改信息');
                $('#fm').form('load',row);
                $('#fm .fitem #employeeId').attr('disabled', true);
                url = '<%=path%>/back/emp_updById.action?employeeId='+row.employeeId;
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
                        $.post('<%=path%>/back/emp_delById.action', 
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
                url: '<%=path%>/back/pos_findAll.action',
				cache : false,
				dataType : 'json',
				success : function(data) {
							if (data.length == 0) {
								alert("职位不存在");
								return;
							}
							for (var i = 0; i < data.length; i++) {
								/* alert(data.length); */
								if (i != data.length - 1) {
									$('#sp #items-input').append("<input id='positionId' name='positionId' type='radio' value='" + data[i].positionId + "'><span>"+ data[i].positionName+ "</span><br/>");
									continue;
								}
								$('#sp #items-input').append("<input id='positionId' name='positionId' type='radio' value='" + data[i].positionId + "'><span>" + data[i].positionName + "</span>");
							}
							$('#cc').combo({
									required : true,
									editable : false
							});
							$('#sp').appendTo($('#cc').combo('panel'));
							$('#sp input').click(
									function() {
										var v = $(this).val();
										var s = $(this).next('span').text();
										$('#cc').combo('setValue',v).combo('setText',s).combo('hidePanel');
									});
						}
				});
			});
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