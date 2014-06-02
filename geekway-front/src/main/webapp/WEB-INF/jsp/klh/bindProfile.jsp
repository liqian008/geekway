<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>用户资料绑定</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
	</head>
	<body class="o-page">
		<div id="page">
			<div id="header">
				用户资料绑定
				<a class="backBtn" href="javascript:history.back();"></a>
			</div> 
			
			<!-- <div class="subHeader">
				用户资料绑定
			</div> -->
			<div id="content">
				<h3 class="title">用户资料绑定</h3>
					
				
				<form action="./bindProfile" method="post" name="bindForm" id="bindForm">
				
				<h5>姓名：<input type="text" id="realname" name="realname"/></h5>
				<h5>手机号：<input type="text" id="mobile" name="mobile"/></h5>
				<h5>生日：<input type="text" id="birth" name="birth"/></h5>
				<h5>邮箱：<input type="text" id="email" name="email"/></h5>
				<h5>地址：<input type="text" id="address" name="address"/></h5>
				<h5>淘宝账户：<input type="text" id="taobaoId" name="taobaoId"/></h5>
				
				
				<a href="javascript:void(0)" id="submitBtn" class="o-buttons blue">注册</a>
				
				<a href="javascript:void(0)" id="resetBtn" class="o-buttons red">重置</a>
				
				</form>
				
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
	
	
	<script>
	$("#submitBtn").click(function(){
		$("#bindForm").submit();
	});
	
	$("#resetBtn").click(function(){
		$("#bindForm").reset();
	});
	</script>
</html>