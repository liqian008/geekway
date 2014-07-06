<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.wx.json.response.WxUserInfoResult" %>

<!-- 带用户登录模块的首页 -->


<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />
<title>可乐惠</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/klh/css/klh.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/klh/js/jquery.js"></script>

<style>
</style>

<body>
	<!--
	<div class="header">
		<div class="header_menu">
			
		</div>		
	</div>
	-->
	<div class="content">
		<div class="logo">
			<img src="./images/logo.png" class="radius">
		</div>

		<div class="menu">
			<ul>
				<li><a href="./latestWallImages" class="klh-button radius">随手拍</a></li>
				<li><a href="./edbOrderMobile" class="klh-button radius">订单查询</a></li>
				<li><a href="./scoreIntro" class="klh-button radius">我的积分</a></li>
				<li><a href="./scoreProductList" class="klh-button radius">兑换赠品</a></li>
				<li><a href="javascript:void(0)" class="klh-button radius lock">登 录</a></li>
				<li><a href="javascript:void(0)" class="klh-button radius lock">注 册</a></li>
			</ul>
		</div>	
	</div>
		
</body>

<script>
$(".lock").click(function(){
	alert("因可乐惠系统未开放用户注册，此功能暂时予以屏蔽");
})

</script>

</html>
