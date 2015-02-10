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

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css?v=1" />

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
			<div id="ito-header">
				NUTS Machine互动游戏
			</div>
			<div id="ito-content">
				<article>
					<!-- <h1>NUTS<br/>MACHINE</h1>
					<h4>
						Nuts Machine是ITO NUTS概念店的互动游戏装置，请参照以下步骤开始游戏。抓取到的Nuts Bubble中可能包含一只ITO Ginkgo系列旅行箱。
					</h4> -->
					<!-- <div class="notifications success"> 
						NUTS Machine互动游戏二维码区域
					</div> -->
					
					<img src="<%=request.getContextPath()%>/mobile/img/ito/title.jpg">
					
					<%
					String loadingImgUrl = request.getContextPath()+"/mobile/img/ito/qrcode/loading.jpg";
					String errorImgUrl = request.getContextPath()+"/mobile/img/ito/qrcode/error.jpg";
					
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
								$('#subscribedQrcode').attr("src", "<%=errorImgUrl%>");
							}
			   			});
						</script>
					<%}%>
					<div style="text-align:center">
						<img id="subscribedQrcode" src="<%=subscribedQrcodeUrl%>" style="width:60%">
						<img src="<%=request.getContextPath()%>/mobile/img/ito/usage.jpg">
					</div>
					<!-- <h5 style="text-align:center">请将二维码对准操纵杆上方的扫描端口，扫描成功启动游戏</h5> -->
					
					<%
					String regedQrcodeUrl = (String) request.getAttribute("regedQrcodeUrl");
					if(regedQrcodeUrl!=null){//不为空的情况下直接展示
					%>
						<div style="text-align:center">
							<img id="regedQrcode" src="<%=regedQrcodeUrl%>" style="width:60%">
						</div>
						<h5 style="text-align:center">注册成功奖励二维码游戏机会</h5>
					<%}else{
						Boolean alreadyReged = (Boolean)request.getAttribute("alreadyReged");
						if(alreadyReged!=null&&alreadyReged){//注册成功，但未取到图片
						%>
						<div style="text-align:center">
							<img id="regedQrcode" src="<%=loadingImgUrl%>" style="width:60%">
						</div>
						<h5 style="text-align:center">注册成功奖励二维码游戏机会</h5>
						
						<script>
						//ajax方式重新获取图片
						var jsonData = {'a':'1'};
						$.post('<%=request.getContextPath()%>/ito/getRegedQrcode.json', jsonData, function(data) {
			   				var result = data.result;
			   				if(result==1){
								//成功
			   					$('#regedQrcode').attr("src", data.data.regedQrcodeUrl);
							}else{
								//失败，暂时失败的图片
								$('#regedQrcode').attr("src", "<%=errorImgUrl%>");
							}
			   			});
						</script>
						<%}else{//未注册，可以进行注册%>
						<img src="<%=request.getContextPath()%>/mobile/img/ito/comment.jpg">
						<!-- <h5>请先完成第一次游戏，再点击下方“再玩一次”按钮，每个微信帐号仅有两次游戏机会。</h5> -->
 						<div style="padding:0px 40px">
 							<a href="./register" id="submitBtn" class="ito-buttons grey">再玩一次</a>
 						</div>
					<%}
					}%>
					
					<br/> 
					<img src="<%=request.getContextPath()%>/mobile/img/ito/map.jpg">
					
					<!-- <h5>NUTS by概念店地址:</h5>
					<h5>上海市武康路216号</h5>
					<h5>*本游戏目前仅在上海地区开通。</h5> -->
				</article>
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
	
</html>