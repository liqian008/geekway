<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.constants.*" %> 

<%
String contextPath = request.getContextPath();
%>

<%
String prompt = (String) request.getAttribute(ConstFront.REDIRECT_PROMPT);
String redirectUrl = (String) request.getAttribute(ConstFront.REDIRECT_URL);
if(prompt==null||"".equals(prompt)){
	prompt = "操作成功，现在将转入后续页面，请稍候…";
}
if(redirectUrl==null||"".equals(redirectUrl)){
	redirectUrl = contextPath + "/";
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>可乐惠</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
	</head>
	<body class="o-page">
		<div id="page">
			<div id="header">
				提示信息
			</div>
			<div id="content">
				<article>
					<h2><a href="javascript:void(0)">提示信息</a></h2>
					<p>
						<%=prompt%>
					</p>
				</article>
				
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
	
	<script language='Javascript'>
	    setTimeout("location.href='<%=redirectUrl%>'", 2000);
	</script>
</html>