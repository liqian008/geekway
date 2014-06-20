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
			<!-- <div id="header">
				<i class="i-blog i-small"></i>
				NUTS Machine互动游戏
			</div> -->
			<div id="ito-content">
				<article>
					<h1>NUTS<br/>MACHINE</h1>
					<h4>
						Nuts Machine是ITO NUTS概念店的互动游戏装置，请参照以下步骤开始游戏。抓取到的Nuts Bubble中可能包含一只ITO Ginkgo系列旅行箱。
					</h4>
					<!-- <div class="notifications success"> 
						NUTS Machine互动游戏二维码区域
					</div> -->
					
					<%
					String loadingImgUrl = request.getContextPath()+"/mobile/img/qrcode/error.jpg";
					String subscribedQrcodeUrl = (String) request.getAttribute("subscribedQrcodeUrl");
					if(subscribedQrcodeUrl==null){
						//展示图片加载中
						subscribedQrcodeUrl= loadingImgUrl;
					%>
						<script>
						var jsonData = {'a':'1'};
						$.post('<%=request.getContextPath()%>/ito/getSubscribedQrcode.json', jsonData, function(data) {
			   				var result = data.result;
			   				if(result==1){
								//成功
								//alert(data.data.subscribeQrcodeUrl);
			   					$('#subscribedQrcode').attr("src", data.data.subscribeQrcodeUrl);
							}else{
								//失败，暂时失败的图片
								$('#subscribedQrcode').attr("src", "<%=request.getContextPath()%>/mobile/img/qrcode/error.jpg");
							}
			   			});
						</script>
					<%
						
					}%>
					<img id="subscribedQrcode" src="<%=subscribedQrcodeUrl%>">
					<h5 style="text-align:center">请将二维码对准操纵杆上方的扫描端口，扫描成功启动游戏</h5>
					
					<%
					String regedQrcodeUrl = (String) request.getAttribute("regedQrcodeUrl");
					if(regedQrcodeUrl!=null){
					%>
						<h5>二维码为用户注册后，系统奖励二维码</h5>
						<img id="regedQrcode"  src="<%=regedQrcodeUrl%>">
					<%}else{%>
						<h5>请先完成第一次游戏，再点击下方“再玩一次”按钮，每个微信帐号仅有两次游戏机会。</h5>
 						<a href="./register" id="submitBtn" class="ito-buttons grey">再玩一次</a>
					<%}%>
					
					<h5>NUTS by概念店地址：</h5>
					<h5>上海市武康路216号</h5>
					<img src="<%=request.getContextPath()%>/mobile/img/ito/nuts-machine-2_05.jpg"">
					<!-- <div class="notifications success">
						获取NUTS Machine互动游戏二维码攻略
					</div>		
					<p>
						1.打开微信，扫描橱窗上的二维码，或搜索”ITO”，关注ITO官方微信；<br/>
						2.点选首页下方”活动”按钮中的”NUTS Machine”选项；<br/>
						3.将二维码对准操纵杆上方的扫描仪，扫描成功，开始游戏；<br/>
						4.点击页面下方“再玩一次”按钮，可再获得一次游戏机会.<br/>
					</p>
					<div class="notifications success">
						NUTS Machine位置
					</div>		
					<p>
						1.目前仅在上海地区开通；<br/>
						2.上海地区NUTS Machine位于武康路216号；<br/>
					</p> -->
					
				</article>
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
	
	
	
	
</html>