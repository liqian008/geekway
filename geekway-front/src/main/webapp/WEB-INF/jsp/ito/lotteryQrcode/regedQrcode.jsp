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
			<div id="ito-header">
				NUTS Machine互动游戏
			</div>
			<div id="content">
				<article>
					<h5 style="text-align:center">恭喜您，获得再次游戏机会！一定要把握住这次机会！</h5>
					
					<%
					String regedQrcodeUrl = (String)request.getAttribute("regedQrcodeUrl");
					if(regedQrcodeUrl==null){
						regedQrcodeUrl= request.getContextPath()+"/mobile/img/qrcode/error.jpg";
					%>
					
						<script>
						var jsonData = {'a':'1'};
						$.post('<%=request.getContextPath()%>/ito/getRegedQrcode.json', jsonData, function(data) {
			   				var result = data.result;
			   				if(result==1){
								//成功
								//alert(data.data.subscribeQrcodeUrl);
			   					$('#regedQrcode').attr("src", data.data.regedQrcodeUrl);
							}else{
								//失败，暂时失败的图片
								$('#regedQrcode').attr("src", "<%=request.getContextPath()%>/mobile/img/qrcode/error.jpg");
							}
			   			});
						</script>
					
					<%}
					%>
					<div style="text-align:center"><img id="regedQrcode" src="<%=regedQrcodeUrl%>" style="width:50%"></div>
					<h5 style="text-align:center">请将二维码对准操纵杆上方的扫描端口，扫描成功启动游戏</h5>
					
				</article>
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
</html>