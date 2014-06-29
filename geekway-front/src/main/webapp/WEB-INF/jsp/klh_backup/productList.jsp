<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

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
		<!-- 
		<script type="text/javascript" src="js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="js/jquery.easy-pie-chart.js"></script>
		<script type="text/javascript" src="js/o-script.js"></script>
		 -->

	</head>
	<body class="o-page">
		<div id="page">
			<!-- 
			<div id="header">
				<a href="#menu"></a>
				<span id="Logo" class="svg">
					<img src="img/logo.svg" />
				</span>
				<a class="backBtn" href="javascript:history.back();"></a>
			</div>
			-->
			<div id="header">
				兑换产品列表
				<a class="backBtn" href="javascript:history.back();"></a>
				
			</div>
			<div id="content">
				
				<%
				List<KlhProduct> productList = (List<KlhProduct>)request.getAttribute("productList");
				if(productList!=null&&productList.size()>0){
					for(KlhProduct product: productList){
				%>	
					<article>
						<a href="../product/<%=product.getId()%>"><img src="<%=product.getProductPicUrl()%>" /></a>
						<h2><a href="../product/<%=product.getId()%>"><%=product.getTitle()%></a></h2>
						<p>
							<%=product.getDescription() %>
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