<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>NUTS Machine互动游戏</title>
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
				NUTS Machine互动游戏
			</div>
			<div id="content">
				<article>
					
					<h2><a href="javascript:void(0)">注册奖励娃娃机抓取二维码</a></h2>
					<p>
						恭喜您，获得再次体验抓娃娃机游戏机会！一定要把握住这次机会！
					</p>
					<%
					String regedQrcodeUrl = (String)request.getAttribute("regedQrcodeUrl");
					if(regedQrcodeUrl==null){
						regedQrcodeUrl= request.getContextPath()+"/mobile/img/qrcode/error.jpg";
					}
					%>
					<img src="<%=regedQrcodeUrl%>">
					
					<p>
						即刻扫描此二维码，启动抓娃娃机。
					</p>
					
				</article>
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
</html>