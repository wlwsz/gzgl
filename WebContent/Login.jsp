<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>登录页面</title> 
	<script src="<%=path %>/login-res/js/jquery-1.4.4.min.js" type="text/javascript"></script>

	<style>
		body{
			background: #ebebeb;
			font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif;
			color: #222;
			font-size: 12px;
		}
		*{padding: 0px;margin: 0px;}
		.top_div{
			background: #008ead;
			width: 100%;
			height: 310px;
		}
		.ipt{
			border: 1px solid #d3d3d3;
			padding: 10px 10px;
			width: 270px;
			border-radius: 4px;
			padding-left: 36px;
			-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
			box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
			-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
			-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
			transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
		}
		.ipt:focus{
			border-color: #66afe9;
			outline: 0;
			-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
			box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
		}
		.u_logo{
			background: url("<%=path %>/login-res/images/username.png") no-repeat;
			padding: 10px 10px;
			position: absolute;
			top: 43px;
			left: 56px;
		}
		
		.p_logo{
			background: url("<%=path %>/login-res/images/password.png") no-repeat;
			padding: 10px 10px;
			position: absolute;
			top: 12px;
			left: 56px;
		}
		a{
			text-decoration: none;
		}
		.tou{
			background: url("<%=path %>/login-res/images/tou.png") no-repeat;
			width: 97px;
			height: 92px;
			position: absolute;
			top: -87px;
			left: 140px;
		}
		.left_hand{
			background: url("<%=path %>/login-res/images/left_hand.png") no-repeat;
			width: 32px;
			height: 37px;
			position: absolute;
			top: -38px;
			left: 150px;
		}
		.right_hand{
			background: url("<%=path %>/login-res/images/right_hand.png") no-repeat;
			width: 32px;
			height: 37px;
			position: absolute;
			top: -38px;
			right: -64px;
		}
		.initial_left_hand{
			background: url("<%=path %>/login-res/images/hand.png") no-repeat;
			width: 30px;
			height: 20px;
			position: absolute;
			top: -12px;
			left: 100px;
		}
		.initial_right_hand{
			background: url("<%=path %>/login-res/images/hand.png") no-repeat;
			width: 30px;
			height: 20px;
			position: absolute;
			top: -12px;
			right: -112px;
		}
		.left_handing{
			background: url("<%=path %>/login-res/images/left-handing.png") no-repeat;
			width: 30px;
			height: 20px;
			position: absolute;
			top: -24px;
			left: 139px;
		}
		.right_handinging{
			background: url("<%=path %>/login-res/images/right_handing.png") no-repeat;
			width: 30px;
			height: 20px;
			position: absolute;
			top: -21px;
			left: 210px;
		}

	</style>

	<script type="text/javascript">
		$(function(){
			//得到焦点
			$("#password").focus(function(){
				$("#left_hand").animate({
					left: "150",
					top: " -38"
				},{step: function(){
					if(parseInt($("#left_hand").css("left"))>140){
						$("#left_hand").attr("class","left_hand");
					}
				}}, 2000);
				$("#right_hand").animate({
					right: "-64",
					top: "-38px"
				},{step: function(){
					if(parseInt($("#right_hand").css("right"))> -70){
						$("#right_hand").attr("class","right_hand");
					}
				}}, 2000);
			});
			//失去焦点
			$("#password").blur(function(){
				$("#left_hand").attr("class","initial_left_hand");
				$("#left_hand").attr("style","left:100px;top:-12px;");
				$("#right_hand").attr("class","initial_right_hand");
				$("#right_hand").attr("style","right:-112px;top:-12px");
			});
			// 表单验证以及绑定事件
			$("#register-a").bind("click",function (){
				$('#register-a').attr('href','<%=path %>/register.jsp'); 
			});
		});
		$(function(){
			var username = $('#username').val();
			var password = $('#password').val();
			if(username.length() > 6 && username.length() < 12) {
				if(password.length() > 6 && password.length() < 12) {
					$('form').submit();
				}
			}
			alert("用户名或密码格式不正确");
		});
		//========================
		function login_verify(){
			var username = $('#username').val();
			var password = $('#password').val();
			if(username.length() > 6 && username.length() < 12) {
				if(password.length() > 6 && password.length() < 12) {
					$('form').submit();
				}
			}
			alert("用户名或密码格式不正确");
		}
		
		/* $('a').click(function(){
			alert("用户名或密码格式不正确");
		    $.post('http://127.0.0.1/api.php', {'nike':0},
		    function(cdata) {
		        console.log('ok', cdata)
		    })
		}) */
</script>

<!-- <meta name="generator" content="mshtml 11.00.9600.17496"> -->
</head> 
<body>
	<div class="top_div"></div>
	<div style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
		<div style="width: 165px; height: 96px; position: absolute;">
			<div class="tou"></div>
			<div class="initial_left_hand" id="left_hand"></div>
			<div class="initial_right_hand" id="right_hand"></div>
		</div>
		<form action="<%=path %>/back/adm_login.action" method="post" id="login_form">
			<!-- 用户名 -->
			<p style="padding: 30px 0px 10px; position: relative;">
				<span class="u_logo"></span>         
				<input class="ipt" id="username" type="text" placeholder="请输入用户名或邮箱" name="username"> 
			</p>
			<!-- 密码 -->
			<p style="position: relative;">
				<span class="p_logo"></span>         
				<input class="ipt" id="password" type="password" placeholder="请输入密码" name="password">   
			</p>
		</form>
		
		<!-- 登录与注册按钮 -->
		<div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
			<p style="margin: 0px 35px 20px 45px;">
				<sapn style="float: left;">
					<a style="color: rgb(204, 204, 204);" href="#">忘记密码?</a>
				</sapn> 
				<span style="float: right;">
					<a style="color: rgb(204, 204, 204); margin-right: 10px;" href="#" id="register-a">注册</a>  
					<a style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;" 
					href="#" onclick="document:login_form.submit();">登录</a><!-- login_verify();  document:login_form.submit(); -->
				</span>         
			</p>
		</div>
	</div>
	<span style="color: red;">${requestScope.error}</span>
</body>
<script type="text/javascript">
	// js提交后判断error是否为空，不为空则提示错误信息，为空则不显示
	// 最好能记住用户名，不需要填入
</script>
</html>