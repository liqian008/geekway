<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>


<%
ItoProductOrder itoProductOrder = (ItoProductOrder)request.getAttribute("productOrder");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>ITO订单确认</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="/geekway-front/mobile/css/style.css" />

		<script type="text/javascript" src="/geekway-front/mobile/js/jquery.min.js"></script>
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
			<div class="subHeader">
				订单确认
			</div>
			<div id="content">
				<h3 class="title">订单详情</h3>
				<!-- <div class="notifications success">
					Success - your request was successful
					<i></i>
				</div>
				<div class="notifications error">
					Error - your request was unsuccessful
					<i></i>
				</div>
				<div class="notifications warning">
					Warning - there is no turn back
					<i></i>
				</div>
				<hr> -->
					
				<div class="notifications info">
					尊敬的用户，以下是您要购买的商品信息，请确认：
					<i></i>
				</div>	
				<h5>
					<img src="http://wximg.jinwanr.com.cn/staticFile//image/20140511/original/1_29149a6beafbd9fdcd9c24a646b47fd4.jpg" width="100%"> 
				</h5>
				<h5>商品名称： <%=itoProductOrder.getTitle()%></h5>
				<h5>商品信息：<%=itoProductOrder.getSkuName()%></h5>
				<h5>商品单价：<%=itoProductOrder.getPrice()%>元</h5>
				<h5>购买数量：<%=itoProductOrder.getNum()%>个</h5>
				<h5>邮费：<%=itoProductOrder.getPostFee()%>元</h5>
				<h5>费用总计：<%=itoProductOrder.getTotalPrice()%>元</h5>
				
				
				<form action="#" method="post" name="orderForm">
				<input type="hidden" id="orderSn" name="orderSn" value="<%=itoProductOrder.getOrderSn()%>"/>
				<%
				String sig = (String)request.getAttribute("sig"); 
				if(sig!=null){
				%>
				<input type="hidden" id="sig" name="sig" value="<%=sig%>"/>
				
				<hr>
				
				<h5>收件人：<input type="text" id="postName" name="postName"/></h5>
				<h5>收货地址：<input type="text" id="postAddress" name="postAddress"/></h5>
				<h5>邮编：<input type="text" id="postCode" name="postCode"/></h5>
				<h5>联系电话：<input type="text" id="postMobile" name="postMobile"/></h5>
				<hr>
				
				<a href="javascript:void(0)" id="confirmBtn" class="o-buttons blue">确认购买</a>
				
				<%}%>
				</form>
				<hr>
				
			</div>
			<div class="subFooter">Copyright 2013. All rights reserved.</div>
		</div>
	</body>
	
	
	<script>
	
	$("#confirmBtn").click(function(){
		//disable submitBtn
		$("#confirmBtn").attr("disabled", "disabled");
		var orderJsonData = {"orderSn": $("#orderSn").val(),'sig': $("#sig").val(), 'postAddress':$("#postAddress").val(), 'postName':$("#postName").val(), 'postCode':$("#postCode").val(), 'postMobile':$("#postMobile").val()};
		$.post("../submitOrder.json", orderJsonData, function(data) {
			var result = data.result;
				if(result==1){
    				$("#confirmBtn").hide();
				}else{
					
				}
			//enable submitBtn
		 }, "json"); 
	});
	
	</script>
</html>