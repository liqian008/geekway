<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.wx.json.response.WxUserInfoResult" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>个人信息</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
		
	</head>
	<body class="o-page">
		<div id="page">
			<!-- Header -->
			<div id="header">
				<%
				WxUserInfoResult sessionUser = (WxUserInfoResult)session.getAttribute("sessionUser");
				if(sessionUser==null){%>
					个人信息
				<%}else{%>
					您好，<%=sessionUser.getNickname() %>!
				<%}%>
				<!-- <a class="backBtn" href="javascript:history.back();"></a> -->
			</div>
			
			
			<div class="exploreSiteFullPane">
				
				<a href="./bindProfile" class="explorePane">
					<span class="i-pane i-blue">
						<i class="i-home"></i>
					</span>
					<h4>信息绑定</h4>
				</a>
				<a href="./productList" class="explorePane middle">
					<span class="i-pane i-blue">
						<i class="i-about"></i>
					</span>
					<h4>兑换赠品</h4>
				</a>
				<a href="javascript:void(0)" class="explorePane">
					<span class="i-pane i-blue">
						<i class="i-blog"></i>
					</span>
					<h4>我的订单</h4>
				</a>
				<a href="./userScoreHistoryList" class="explorePane">
					<span class="i-pane i-blue">
						<i class="i-gallery"></i>
					</span>
					<h4>我的积分</h4>
				</a>
				<a href="./voteList" class="explorePane middle">
					<span class="i-pane i-blue">
						<i class="i-shortcodes"></i>
					</span>
					<h4>投票</h4>
				</a>
				<a href="javascript:void(0)" class="explorePane">
					<span class="i-pane i-blue">
						<i class="i-contact"></i>
					</span>
					<h4>关于我们</h4>
				</a>
			</div>
			
			
		</div>
	</body>
</html>