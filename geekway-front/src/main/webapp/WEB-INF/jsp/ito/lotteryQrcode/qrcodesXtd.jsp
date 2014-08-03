<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>NUTS Machine互动游戏@XTD</title>
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
			<div id="ito-header">
				NUTS Machine互动游戏@XTD
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
					
					<img src="<%=request.getContextPath()%>/mobile/img/ito/title_xtd.jpg">
					
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
						$.post('<%=request.getContextPath()%>/ito/getSubscribedXtdQrcode.json', jsonData, function(data) {
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
					<div style="text-align:center"><img id="subscribedQrcode" src="<%=subscribedQrcodeUrl%>" style="width:60%"></div>
					<h5 style="text-align:center">请将二维码对准操纵杆上方的扫描端口，扫描成功启动游戏</h5>
					
					<br/> 
					
					<img src="<%=request.getContextPath()%>/mobile/img/ito/map_xtd.jpg">
					
					<!-- 	
					<h5>NUTS by概念店地址:</h5>
					<h5>地址：上海市太仓路181弄新天地北里25号</h5>
					<h5>*礼品兑换 优惠券使用方法请致电或至XTD新天地礼品店咨询店员。</h5>
					<h5>*本游戏目前仅在上海地区开通。</h5>
					 -->
				</article>
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
	
</html>