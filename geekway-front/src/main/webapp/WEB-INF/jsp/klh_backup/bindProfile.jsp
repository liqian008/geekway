<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.wx.json.response.WxUserInfoResult" %>


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
					
				
				<%
				//WxUserInfoResult sessionUser = (WxUserInfoResult)session.getAttribute("sessionUser");
				KlhUserProfile sessionUserProfile = (KlhUserProfile)session.getAttribute("sessionUserProfile");
				
				String userOpenId = sessionUserProfile!=null&&sessionUserProfile.getUserOpenId()!=null?sessionUserProfile.getUserOpenId():"";
				String realname = sessionUserProfile!=null&&sessionUserProfile.getRealname()!=null?sessionUserProfile.getRealname():"";
				String mobile = sessionUserProfile!=null&&sessionUserProfile.getMobile()!=null?sessionUserProfile.getMobile():"";
				String birthday = sessionUserProfile!=null&&sessionUserProfile.getBirthday()!=null?sessionUserProfile.getBirthday()+"":"";
				String email = sessionUserProfile!=null&&sessionUserProfile.getEmail()!=null?sessionUserProfile.getEmail():"";
				String address = sessionUserProfile!=null&&sessionUserProfile.getAddress()!=null?sessionUserProfile.getAddress():"";
				%>
				
				<form action="./bindProfile" method="post" name="bindForm" id="bindForm">
				<input type="hidden" id="userOpenId" name="userOpenId" value="<%=userOpenId%>"/> 
				
				<h5>姓名：<input type="text" id="realname" name="realname" value="<%=realname%>"/></h5>
				<h5>手机号：<input type="text" id="mobile" name="mobile" value="<%=mobile%>"/></h5>
				<h5>生日：<input type="text" id="birthday" name="birthday" value="<%=birthday%>"/></h5>
				<h5>邮箱：<input type="text" id="email" name="email" value="<%=email%>"/></h5>
				<h5>地址：<input type="text" id="address" name="address" value="<%=address%>"/></h5>
				<!-- <h5>淘宝账户：<input type="text" id="taobaoId" name="taobaoId"/></h5> -->
				
				
				<a href="javascript:void(0)" id="submitBtn" class="o-buttons blue">绑定</a>
				
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