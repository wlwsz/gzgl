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
<title>工资计算管理</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/demo/demo.css">
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	//先请求所有数据
	$(document).ready(function() {
		 $('#dg').datagrid({  
			 //搜索前,触发此action请求所有用户信息
	        url:'<%=path%>/back/way_findAll.action',   
	        loadMsg:'数据加载中......',  
	        fitColumns:true,//允许表格自动缩放,以适应父容器  
	        sortName:'positionName',  
	        sortOrder:'asc',  
	        remoteSort:false,  
	        columns : [ [ { field : 'id', hidden : true, title : '编号'}, { field : 'positionId',hidden : true, title : '职位编号'  
	        }, { field : 'positionName', width : 30, align : 'left', title : '职位名称' }, { field : 'basicWage', width : 20, align : 'left', title : '基本工资' 
	        }, { field : 'cdMoneny', width : 23, align : 'left', title : '迟到(每小时/元)' }, { field : 'ztMoneny', width : 23, align : 'left', title : '早退(每小时/元)' 
	        }, { field : 'kgMoneny', width : 23, align : 'left', title : '旷工(每小时/元)' }, { field : 'secureReduce', width : 20, align : 'left', title : '五险一金'  
	        }, { field : 'percent',  width : 23, align : 'left', title : '提成(%)' }, { field : 'ohMoneny', width : 23, align : 'left', title : '加班费(每小时/元)'  
	        } ] ],
	        pagination : true,  
	        rownumbers : true
	    }); 
	});
	// 搜索
	function searchWageWay(){
		var s_name = $('#s_positionName').val();
		$('#dg').datagrid({
			url:'<%=path %>/back/way_searchByKey.action?positionName=' + s_name,
			loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器  
			sortName:'positionName',  
	        sortOrder:'asc',  
	        remoteSort:false,  
	        columns : [ [ { field : 'id', hidden : true, title : '编号'}, { field : 'positionId',hidden : true, title : '职位编号'  
	        }, { field : 'positionName', width : 30, align : 'left', title : '职位名称' }, { field : 'basicWage', width : 20, align : 'left', title : '基本工资' 
	        }, { field : 'ohMoneny', width : 40, align : 'left', title : '加班费(每小时/元)' }, { field : 'secureReduce', width : 20, align : 'left', title : '五险一金'  
	        }, { field : 'cdMoneny', width : 20, align : 'left', title : '迟到' }, { field : 'ztMoneny', width : 20, align : 'left', title : '早退' 
	        }, { field : 'kgMoneny', width : 20, align : 'left', title : '旷工' }, { field : 'percent',  width : 20, align : 'left', title : '提成(%)'  
	        } ] ],
	        pagination : true,  
	        rownumbers : true
		});				
	}
</script>
</head>
<body>
	 <table id="dg" title="工资计算列表" class="easyui-datagrid" style="width:auto;height:465px;"
        toolbar="#toolbar" pagination="true"
        rownumbers="true" fitColumns="true" singleSelect="true">
    </table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
		&nbsp;&nbsp;&nbsp;职位名称:&nbsp;<input type="text" name="s_positionName" id="s_positionName" size="10" /> 
		<a href="javascript:searchWageWay()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a>
	</div>

	<div id="dlg" class="easyui-dialog" style="width: 380px; height: 400px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
		<div class="ftitle">工资计算方式</div>
		  <form id="fm" method="post" validate>
			<!-- 选择职位 -->
			<div class="fitem">
				<div style="margin: 10px 0"></div>
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
	            <div style="margin:5px 2px"></div>
	            <label>基本工资:</label>
	            <input id="basicWage" name="basicWage" type="number" value="0" class="easyui-textbox">
        	</div>
        	<div class="fitem">
	            <div style="margin:5px 2px"></div>
	            <label>五险一金:</label>
	            <input id="secureReduce" name="secureReduce" type="number" value="0" class="easyui-textbox">
        	</div>
        	<div class="fitem">
	            <div style="margin:5px 2px"></div>
	            <label>销售提成:</label>
	            <input id="percent" name="percent" type="number" value="0" class="easyui-textbox">
        	</div>
			<div class="fitem">
	            <div style="margin:5px 2px"></div>
	            <label>加班:</label>
	            <input id="ohMoneny" name="ohMoneny" type="number" value="0" class="easyui-textbox">
        	</div>
        	<div class="fitem">
	            <div style="margin:5px 2px"></div>
	            <label>迟到:</label>
	            <input id="cdMoneny" name="cdMoneny" type="number" value="0" class="easyui-textbox">
        	</div>
        	<div class="fitem">
	            <div style="margin:5px 2px"></div>
	            <label>早退:</label>
	            <input id="ztMoneny" name="ztMoneny" type="number" value="0" class="easyui-textbox">
        	</div>
        	<div class="fitem">
	            <div style="margin:5px 2px"></div>
	            <label>旷工:</label>
	            <input id="kgMoneny" name="kgMoneny" type="number" value="0" class="easyui-textbox">
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
            url = '<%=path%>/back/way_add.action';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改信息');
                $('#fm').form('load',row);
                url = '<%=path%>/back/way_updById.action?id='+row.id;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return true;
                },
                success: function(result){
                    /* var result = eval('('+result+')'); */
                    $('#dlg').dialog('close');        // close the dialog
                    $('#dg').datagrid('reload');    // reload the user data
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                    	
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
									// 此处的异步请求是为了保证每一个职位只有一个计算方式,如果存在则提示
									$.ajax({
										url: "<%=path %>/back/way_findByPosId.action",
										type: "post",
										cache : false,
										dataType: "json",
										data: {positionId: v},
										success : function(result) {
											if(result.success) {
												return true;
											} else {
												$('#cc').empty();
												$.messager.alert("系统提示","<font color='red'>"+ result.Msg+"</font>");
											}
										}
									});
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