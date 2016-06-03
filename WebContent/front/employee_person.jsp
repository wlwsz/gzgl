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
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="front/login-res/favicon.ico">
    <title>个人详情</title>
    <!-- Bootstrap core CSS -->
    <link href="front/login-res/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="front/dashboard.css" rel="stylesheet">
    <link href="front/carousel.css" rel="stylesheet">
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="front/login-res/ie-emulation-modes-warning.js"></script>
    <script type="text/javascript">
    	$(document).ready(function() {
	   		<%-- $.ajax({
	   			type:"POST",
	   			url:"<%=path %>/frontS_findByEmpId.action",
	   			success: function(all){
		   			alert(all);
	   		    }
	   		}); --%>
    	});
    </script>
  </head>
  <body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	    <div class="container-fluid">
	      <div class="navbar-header">
	        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	          <span class="sr-only">Toggle navigation</span>
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>
	        </button>
	        <a class="navbar-brand" href="#">Project name</a>
	        <ul class="nav navbar-nav navbar-right">
	          <li><a href="#">主页</a></li>
	          <li><a href="#">设置</a></li>
	          <li><a href="#">关于</a></li>
	          <li><a href="#">帮助</a></li>
	        </ul>
	      </div>
	      <div id="navbar" class="navbar-collapse collapse">
	      	<ul class="nav navbar-nav">
	      	  <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">条件<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                  </ul>
              </li>
              <li>
                  <form class="navbar-form navbar-right">
		           <input type="text" class="form-control" placeholder="Search...">
		          </form>
              </li>
	       </ul>
	      </div>
	  </div>
	</nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
          	<li><!-- <a style="color: blue; padding-left: 20px; font-size: 20px; font-family: DFKai-SB; ">个人信息</a> --></li>
            <li style="margin-bottom: 20px;"><span style="padding-right: 20px; padding-left: 50px;"><img alt="头像" style="border: 1px solid #cccccc; border-radius: 50%; height: 100px; width: 100px;" src="<%=path%>${sessionScope.employee.iconPath}"></span></li>
            <li style="padding-left: 20px; font-size: 20px; font-family: DFKai-SB;">当前用户：<font style="color: orange; padding-right: 20px; padding-left: 8px;">${sessionScope.employee.name}</font></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a>性别：${sessionScope.employee.sex}</a></li>
            <li><a>手机号：${sessionScope.employee.telephone}</a></li>
            <li><a>描述：${sessionScope.employee.memo}</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="#">设置头像</a></li>
            <li><a href="#">修改密码</a></li>
            <li><a href="#">退出系统</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">工资记录</h2>
          <div class="table-responsive">
            <table class="table table-striped" data-url="<%=path %>/frontS_findByEmpId.action">
              <thead>
                <tr>
	                <th field="salaryId" hidden="true">编号</th>
	                <th field="employeeId">员工编号</th>
	                <th field="employeeName">姓名</th>
	                <th field="basicWage">基本工资</th>
	                <th field="overtimeWage">加班工资</th>
	                <th field="sellmoneyGet">提成</th>
	                <th field="totalWage">总工资</th>
	                <th field="totalReduce">扣税</th>
	                <th field="realWage">实际工资</th>
	                <th field="month">月份</th>
	                <th field="editTime">编辑时间</th>
	                <th field="memo">说明</th>
                </tr>
              </thead>
              <tbody>
              	<c:forEach var="salary" items="${responseScope.all}" >
	                <tr>
	                	<td hidden="true">${salary.salaryId}</td>
	                	<td>${salary.employeeId}</td>
	                	<td>${salary.employeeName}</td>
	                	<td>${salary.basicWage}</td>
	                	<td>${salary.overtimeWage}</td>
	                	<td>${salary.sellmoneyGet}</td>
	                	<td>${salary.totalWage}</td>
	                	<td>${salary.totalReduce}</td>
	                	<td>${salary.realWage}</td>
	                	<td>${salary.month}</td>
	                	<td>${salary.editTime}</td>
	                	<td>${salary.memo}</td>
	                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="front/login-res/jquery.min.js"></script>
    <script src="front/login-res/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="front/login-res/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="front/login-res/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
