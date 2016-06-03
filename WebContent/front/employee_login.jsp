<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="front/login-res/favicon.ico">
    <title>用户登录界面</title>
    <link href="front/login-res/bootstrap.min.css" rel="stylesheet">
    <link href="front/signin.css" rel="stylesheet">
    <script src="front/login-res/ie-emulation-modes-warning.js"></script>
  </head>
  <body>
    <div class="container">
      <form class="form-signin" action="front_login.action" method="post">
        <h2 class="form-signin-heading">普通用户登录</h2>
        <label for="inputEmail" class="sr-only"></label>
        <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
        <p style="height:3px;"></p>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住用户
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登   录</button>
      </form>
    </div> 
    <script type="text/javascript">
		$(document).ready(function(){
			
			
		});
    </script>
	<div class="alert alert-danger" role="alert">${requestScope.error}</div>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="front/login-res/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
