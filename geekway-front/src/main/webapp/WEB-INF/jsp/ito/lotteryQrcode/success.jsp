<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>


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
				游戏资格二维码
			</div>
			<div id="content">
				<article>
					<img src="<%=request.getAttribute("qrcodeUrl")%>">
					<h2><a href="javascript:void(0)">娃娃机游戏资格二维码</a></h2>
					<p>
						每个用户仅限两次免费游戏机会。
					</p>
					<%
					Integer userApplyedTime = (Integer)request.getAttribute("userApplyedTime");
					if(userApplyedTime!=null&&userApplyedTime<=1){//首次进入时才展示此按钮
					%>
					<a href="http://wap.koudaitong.com/v2/apps/vote/index.html?alias=15jc2sfe7&kdt_id=20949" id="submitBtn" class="o-buttons blue">获取第二次游戏机会</a>
					<%}%>
				</article>
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
</html>