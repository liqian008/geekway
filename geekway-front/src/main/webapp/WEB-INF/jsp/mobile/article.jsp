<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>ITO文章</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
		<!-- 
		<link type="text/css" rel="stylesheet" href="css/jquery.mmenu.all.css" />
		<script type="text/javascript" src="js/jquery.mmenu.min.all.js"></script>
		<script type="text/javascript" src="js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="js/jquery.easy-pie-chart.js"></script>
		<script type="text/javascript" src="js/o-script.js"></script>
 		-->
	</head>
	<body class="o-page">
		<div id="page">
			<div id="header">
				<!-- 
				<i class="i-blog i-small"></i>
				 -->
				文章详情
				<a class="backBtn" href="javascript:history.back();"></a>
			</div>
			<div id="content">
				<%
				WxMaterialArticle article = (WxMaterialArticle)request.getAttribute("article");
				if(article!=null){
				%>	
				<article>
					<h2><a href="blog-single-post.html#"><%=article.getTitle()%></a></h2>
					<p>
						<%=article.getContent()%>
					</p>
					<!-- <div class="a-meta">
						<a href="blog-single-post.html#"><i class="i-comments i-small"></i>6 Comments</a> 
						<a href="blog-single-post.html#"><i class="i-author i-small"></i>John Doe</a> 
						<a href="blog-single-post.html#"><i class="i-calendar i-small"></i>31 Nov 2013</a>
					</div> -->
				</article>
				<%} %>
				
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
</html>