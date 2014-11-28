<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@page import="com.bruce.geekway.model.enumeration.GeekwayEnum"%>
<%@ page import ="com.bruce.geekway.model.wx.pay.*" %>

<!-- 待付条款 -->

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>为女神买单，我骄傲啊！【美妞儿】</title>

<link href="${pageContext.request.contextPath}/slideby/styles/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/framework.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/owl.carousel.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/owl.theme.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/swipebox.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/colorbox.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/meiniu.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/jqueryui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/colorbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/snap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/contact.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/framework.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/framework.launcher.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/map.js"></script>

</head>
<body>

<div class="all-elements">
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">为女神买单，我骄傲啊！</p>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
        </div>
        
        <div class="content"> 
	
			<div class="decoration"></div>
			<div class="container no-bottom">
				<div class="big-notification yellow-notification"> 
					<h4 class="uppercase">提醒：</h4>
					<a href="#" class="close-big-notification">x</a>
					<p>
						您即将查看&付款的是女神的订单，请付款前务必确认好订单信息。
					</p>
				</div>
			</div>

        	<div class="container no-bottom">
            	<div class="section-title">
                	<h4><a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">代付订单信息</a></h4>
				</div>
            </div> 
            
            <div class="decoration"></div>
            <div class="container no-bottom">
            	<div class="section-title"> 
                	<h4>代付订单信息</h4>
                	<em></em> 
				</div>
				<p class="quote-item">
					订单号： ${orderInfo.outTradeNo}<br/>
					下单时间：${orderInfo.createTime}
				</p>

				<p class="quote-item">
					<img src="${orderWebUser.headImgUrl}">
					<span class="text-highlight highlight-dark">
						下单人昵称： ${orderWebUser.nickname}<br/>
					</span>
				</p>
			</div>
             
            <div class="decoration"></div>
            <div class="container" id="product-intro">
            	<div class="section-title">
                	<h4>订单金额</h4>
                	<em>&nbsp;</em>
				</div>
            	
                <h5 class="center-text"> 
                商品：&nbsp;<span id="productFee" class="text-highlight highlight-blue">${orderInfo.productFee}</span>元&nbsp;|&nbsp; 
                运费：&nbsp;<span id="deliveryFee" class="text-highlight highlight-red">${orderInfo.transportFee}</span>元&nbsp;|&nbsp; 
                
                <%
                WxProductOrder productOrder = (WxProductOrder)request.getAttribute("orderInfo");
                if(productOrder.getDiscountFee()!=null&&productOrder.getDiscountFee()>0){
                %>
                折扣：&nbsp;<span class="text-highlight highlight-dark">-${orderInfo.discountFee}</span>元&nbsp;|&nbsp;
                <%}%>
                
                合计：&nbsp;<span id="totalPrice" class="text-highlight highlight-green">${orderInfo.totalFee}</span>元
                </h5>
            </div>
            
            <div class="decoration"></div>
           	<div class="container center-text">
           		<div class="section-title">
                	<h4>您即将查看的是女神的订单，请务必先确认好订单信息，再确认付款</h4>
				</div>  
           	 
               	<a href="javascript:void(0)" id="tuhaoPay" class="button-big button-green">少废话，买完赶紧啪啪啪</a>
               	<a href="javascript:void(0)" id="diaosiCancel" class="button-big button-blue">算了，我自己都还没买呢</a>
           	</div> 
            
            <div class="decoration"></div>
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>
</body>

<script>
$("#tuhaoPay").click(function(){
	location.href="${pageContext.request.contextPath}/payForAngle?tradeNo=${orderInfo.outTradeNo}";
});

$("#diaosiCancel").click(function(){
	location.href="${pageContext.request.contextPath}/index";
});

</script>


<!-- 禁用微信分享 -->
<jsp:include page="../inc/weixinHideOptionMenu.jsp?hideOpt=1"></jsp:include>

</html>
