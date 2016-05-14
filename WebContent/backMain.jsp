<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head id="Head1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>工资管理系统--后台</title>
        <link href="<%=path %>/back/css/default.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="<%=path %>/back/js/themes/default/easyui.css" />
        <link rel="stylesheet" type="text/css" href="<%=path %>/back/js/themes/icon.css" />
        <script type="text/javascript" src="<%=path %>/back/js/jquery-1.4.4.min.js"></script>
        <script type="text/javascript" src="<%=path %>/back/js/jquery.easyui.min.1.2.2.js"></script>
        <script type="text/javascript" src="<%=path %>/back/js/outlook2.js"></script>
        <script type="text/javascript">
            var _menus = {"menus":[
                                {"menuid":"1","icon":"icon-sys","menuname":"编辑菜单",
                                    "menus":[
                                            {"menuid":"11","menuname":"用户管理","icon":"icon-users","url":"<%=path%>/admin_manager.jsp"},
                                            {"menuid":"12","menuname":"部门管理","icon":"icon-users","url":"<%=path%>/department_manager.jsp"},
                                            {"menuid":"13","menuname":"职位管理","icon":"icon-users","url":"<%=path%>/position_manager.jsp"},
                                            {"menuid":"14","menuname":"员工管理","icon":"icon-users","url":"<%=path%>/employee_manager.jsp"},
                                            {"menuid":"15","menuname":"考勤管理","icon":"icon-users","url":"<%=path%>/attendance_manager.jsp"},
                                            {"menuid":"16","menuname":"销售管理","icon":"icon-users","url":"<%=path%>/sell_manager.jsp"},
                                            {"menuid":"17","menuname":"扣税管理","icon":"icon-users","url":"<%=path%>/deduction_manager.jsp"},
                                            {"menuid":"18","menuname":"角色管理","icon":"icon-role","url":"#"},
                                            {"menuid":"19","menuname":"权限设置","icon":"icon-set","url":"#"},
                                            {"menuid":"20","menuname":"系统日志","icon":"icon-log","url":"#"}
                                        ]
                                },{"menuid":"2","icon":"icon-sys","menuname":"工资管理",
                                    "menus":[{"menuid":"22","menuname":"员工工资","icon":"icon-users","url":"<%=path%>/wageInfo/salary_manager.jsp"},
                                             {"menuid":"23","menuname":"工资计算方式","icon":"icon-users","url":"<%=path%>/wageInfo/wage_manager.jsp"},
                                             {"menuid":"24","menuname":"其他管理","icon":"icon-users","url":"#"},
                                        ]
                                },{"menuid":"3","icon":"icon-sys","menuname":"导出管理",
                                    "menus":[{"menuid":"32","menuname":"职位列表","icon":"icon-users","url":"<%=path%>/excel/position_manager.jsp"},
                                             {"menuid":"33","menuname":"员工列表","icon":"icon-users","url":"<%=path%>/excel/employee_manager.jsp"},
                                             {"menuid":"34","menuname":"考勤列表","icon":"icon-users","url":"<%=path%>/excel/attendance_manager.jsp"},
                                             {"menuid":"35","menuname":"销售列表","icon":"icon-users","url":"<%=path%>/excel/sell_manager.jsp"},
                                        ]
                                },{"menuid":"4","icon":"icon-sys","menuname":"个人管理",
                                    "menus":[{"menuid":"41","menuname":"个人信息","icon":"icon-users","url":"<%=path %>/personnalPage.jsp"},
                                             {"menuid":"42","menuname":"修改头像","icon":"icon-users","url":"<%=path %>/uploadIcon.jsp"},
                                             {"menuid":"43","menuname":"其他项目","icon":"icon-users","url":"#"}
                                        ]
                                },{"menuid":"5","icon":"icon-sys","menuname":"其他管理",
                                    "menus":[{"menuid":"51","menuname":"个人信息","icon":"icon-users","url":"<%=path %>/personnalPage.jsp"},
                                             {"menuid":"52","menuname":"修改头像","icon":"icon-users","url":"<%=path %>/uploadIcon.jsp"},
                                             {"menuid":"53","menuname":"留言板信息","icon":"icon-users","url":"#"}
                                        ]
                                }
                        ]};
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }
        
        //修改密码
        function updPassword() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');
            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }
            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }
            $.ajax({
				url: '<%=path%>/back/adm_updPasswordAjax.action?',
				type: 'POST',
				data: {username: '${sessionScope.user_session.username}', password:$newpass.val()},
				dataType: 'json',
				success : function(msg) {
	                 msgShow('系统提示', '恭喜，密码修改成功！<br>');
	                 closePwd();
	                //window.location.reload(); 
				} 
            });

        }
        $(function() {
            openPwd();
            $('#editpass').click(function() {
                $('#w').window('open');
            });
            $('#btnEp').click(function() {
                serverLogin();
            })
            $('#btnCancel').click(function(){closePwd();})
            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
                    if (r) {
                        location.href = '<%=path%>/Login.jsp';
                    }
                });
            })
        });     
                
        </script>
    </head>
    <body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
        <noscript>
            <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
                <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
            </div>
        </noscript>
        <div region="north" split="true" border="false" style="overflow: hidden; height: 64px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 46px;color: #fff; font-family: Verdana, 微软雅黑,黑体;">
            <span style="float:right; padding-right:50px; font-size:20px;" class="head">欢迎  <font style="color: red; font-size:20px;">${sessionScope.user_session.username} </font><a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
            <span style="padding-left:10px; font-size: 16px; "><img src="images/blocks.png" width="20" height="20" align="absmiddle" /> 工资管理系统</span>
        </div>
        <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
            <div class="footer">工资管理系统基本情况</div>
        </div>
        <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
            <div id="nav" class="easyui-accordion" fit="true" border="false">
                <!--  导航内容 -->
            </div>
        </div>
        <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
            <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
	           <div title="欢迎使用" style="background-color: #A7ABAE;">
            	<center>
	              <img style="width: 880px; height: 485px;padding-top: 10px;" src="<%=path %>/back/bg.gif"/>
                </center>
	          </div>
            </div>
        </div>
        
        
        <!--修改密码窗口-->
        <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
            maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
            background: #fafafa;">
            <div class="easyui-layout" fit="true">
                <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                    <table cellpadding=3>
                        <tr>
                            <td>新密码：</td>
                            <td><input id="txtNewPass" type="password" class="txt01" /></td>
                        </tr>
                        <tr>
                            <td>确认密码：</td>
                            <td><input id="txtRePass" type="password" class="txt01" /></td>
                        </tr>
                    </table>
                </div>
                <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                    <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="#" onclick="updPassword()">
                        确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
                    </div>
                </div>
            </div>
            <div id="mm" class="easyui-menu" style="width:150px;">
                <div id="mm-tabupdate">刷新</div>
                <div class="menu-sep"></div>
                <div id="mm-tabclose">关闭</div>
                <div id="mm-tabcloseall">全部关闭</div>
                <div id="mm-tabcloseother">除此之外全部关闭</div>
                <div class="menu-sep"></div>
                <div id="mm-tabcloseright">当前页右侧全部关闭</div>
                <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
                <div class="menu-sep"></div>
                <div id="mm-exit">退出</div>
            </div>
        </body>
    </html>