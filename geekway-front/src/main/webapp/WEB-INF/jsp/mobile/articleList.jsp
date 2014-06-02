<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>ITO文章列表</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
		<!-- 
		<script type="text/javascript" src="js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="js/jquery.easy-pie-chart.js"></script>
		<script type="text/javascript" src="js/o-script.js"></script>
		 -->

	</head>
	<body class="o-page">
		<div id="page">
			<div id="header">
				文章列表
				<a class="backBtn" href="javascript:history.back();"></a>
			</div>
			<div id="content">
				
				<%
				List<WxMaterialArticle> articleList = (List<WxMaterialArticle>)request.getAttribute("articleList");
				if(articleList!=null&&articleList.size()>0){
					for(WxMaterialArticle article: articleList){
				%>	
					<article>
					<a href="../article/<%=article.getId()%>"><img src="<%=article.getCoverImageUrl()%>" /></a>
					<h2><a href="../article/<%=article.getId()%>"><%=article.getShortTitle()%></a></h2>
					<p>
						<%=article.getShortContent() %>
					</p>
					<div class="a-meta">
						<a href="./blog"><i class="i-comments i-small"></i>6 评论</a> 
						<a href="about.html"><i class="i-author i-small"></i>John Doe</a> 
						<a href="./blog"><i class="i-calendar i-small"></i>31 Nov 2013</a>
					</div>
				</article> 
				<%}
				}%>

			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
</html>