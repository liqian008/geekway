<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>


<%
String errorMsg = (String) request.getAttribute("errorMsg");
if(errorMsg==null){
	errorMsg = "系统正在开小差，请稍后再试";
}

%>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>娃娃机游戏</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
	</head>
	<body class="o-page">
		<div id="page">
			<div id="header">
				错误
			</div>
			<div id="content">
				<article>
					<h2><a href="javascript:void(0)">错误</a></h2>
					<p>
						<%=errorMsg%>
					</p>
				</article>
				
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
</html>