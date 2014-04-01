<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>ArticleInfo</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="css/style.css" />

		<script type="text/javascript" src="js/jquery.min.js"></script>
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
			<!-- 
			<div id="header">
				<a href="blog-single-post.html#menu"></a>
				<span id="Logo" class="svg">
					<img src="img/logo.svg" />
				</span>
				<a class="backBtn" href="javascript:history.back();"></a>
			</div>
			-->
			<div class="subHeader">
				<!-- 
				<i class="i-blog i-small"></i>
				 -->
				文章详情 
			</div>
			<div id="content">
				<%
				WxArticle article = (WxArticle)request.getAttribute("article");
				if(article!=null){
				%>	
				<article>
					<img src="<%=article.getCoverImageUrl()%>" />
					<h2><a href="blog-single-post.html#"><%=article.getTitle()%></a></h2>
					<p>
						<%=article.getContent()%>
					</p>
					<div class="a-meta">
						<a href="blog-single-post.html#"><i class="i-comments i-small"></i>6 Comments</a> 
						<a href="blog-single-post.html#"><i class="i-author i-small"></i>John Doe</a> 
						<a href="blog-single-post.html#"><i class="i-calendar i-small"></i>31 Nov 2013</a>
					</div>
				</article>
				<%} %>
				
			</div>
			<div class="subFooter">Copyright 2013. All rights reserved.</div>
			<nav id="menu">
				<ul>
					<li>
						<a href="index.html">
							<i class="i-home i-small"></i>Home
						</a>
					</li>
					<li>
						<a href="about.html">
							<i class="i-about i-small"></i>About
						</a>
					</li>
					<li class="Selected">
						<a href="blog.html">
							<i class="i-blog i-small"></i>Blog
						</a>
						<ul>
							<li><a href="blog-single-post.html">First Post</a></li>
							<li>
								<a href="blog-single-post.html">Second Post</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="gallery.html">
							<i class="i-gallery i-small"></i>Gallery
						</a>
					</li>
					<li>
						<a href="shortcodes.html">
							<i class="i-shortcodes i-small"></i>Shortcodes
						</a>
					</li>
					<li>
						<a href="contact.html">
							<i class="i-contact i-small"></i>Contact
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</body>
</html>