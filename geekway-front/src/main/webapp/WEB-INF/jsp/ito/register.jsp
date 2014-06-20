<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.wx.json.response.WxUserInfoResult" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>用户注册</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
	</head>
	<body class="o-page">
		<div id="page">
			<div id="ito-header">
				用户信息填写
			</div> 
			<div id="content">
				<h4 class="title">请填写以下信息，系统会自动为你推送二维码以启动游戏设备；</h4>
				
				<form action="./register" method="post" name="registerForm" id="registerForm">
				
				<h5>姓名：<input type="text" id="reg-username" name="username"></h5>
				<h5>手机：<input type="text" id="reg-mobile" name="mobile"/></h5> 
				<h5>邮箱：<input type="text" id="reg-email" name="email"/></h5>
				
				<h5><input type="radio" name="promoting" checked="checked" value="1"/>我希望接收关于ITO旅行箱产品，促销以及活动信息</h5>
				<h5><input type="radio" name="promoting"  value="0"/>我不希望接任何关于ITO旅行箱产品，促销以及活动信息</h5>
				
				<a href="javascript:void(0)" id="submitBtn" class="ito-buttons2 grey">确认提交</a>
				<a href="javascript:void(0)" id="resetBtn" class="ito-buttons2 grey">重置</a>
				
				</form>
				
				<p>
				我们将采取严格的措施保护您的隐私，确保您的隐私权不受任何侵犯。
				只要您提供给我们任何个人信息，我们都会采取有效的手段保护您的信息（法律或政府的强制性规定不在此限）
				<p>
				
				
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
	
	
	<script>
	/*注册部分JS*/
	var regUsernameAvailable = false;
	var regMobileAvailable = false;
	
	$('#reg-username').blur(function(){
		checkRegUsername();
	});
	$('#reg-mobile').blur(function(){
		checkRegMobile();
	});
	
	//检查用户名是否合法
	function checkRegUsername(){
		var usernameVal = $('#reg-username').val();
		if(usernameVal==''){
			alert("用户名不能为空");
    		return false;
		}else{//ajax检查是否可用
			var jsonData = {'username':usernameVal};
			$.post('<%=request.getContextPath()%>/ito/usernameExists.json', jsonData, function(data) {
   				var result = data.result;
   				if(result==1){
   					//设置username available的标识
   					regUsernameAvailable = true;
   				}else{
   					//设置username unabailable的标识
   					regUsernameAvailable = false;
   					alert("用户名已被注册");
   				}
   			});
		}
	}
	
	//检查用户名是否合法
	function checkRegMobile(){
		var mobileVal = $('#reg-mobile').val();
		if(mobileVal==''){
			alert("手机号不能为空");
    		return false;
		}else{//ajax检查是否可用
			var jsonData = {'mobile':mobileVal};
			$.post('<%=request.getContextPath()%>/ito/mobileExists.json', jsonData, function(data) {
   				var result = data.result;
   				if(result==1){
   					//设置mobile available的标识
   					regMobileAvailable = true;
   				}else{
   					//设置mobile unabailable的标识
   					regMobileAvailable = false;
   					alert("手机号已被注册");
   				}
   			});
		}
	}
	
	$("#submitBtn").click(function(){
   		if(regUsernameAvailable && regMobileAvailable){
   			//所有数据项均可用
			$("#registerForm").submit();
   		}else{
   			return false;
   		}
	});
	
	$("#resetBtn").click(function(){
		$("#registerForm").reset();
	});
	</script>
</html>