<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import ="com.bruce.geekway.model.wx.pay.*" %>

<!-- 要分享出的页面 -->

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>找土豪朋友待付【美妞儿】</title>

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
            <p class="bread-crumb">找土豪朋友待付</p>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
        </div>
        
        <div class="content"> 
	
			<div class="decoration"></div>
			<div class="container no-bottom">
				<div class="big-notification green-notification"> 
					<h4 class="uppercase">看看有多少土豪替为我买单？</h4>
					<p>
						点击右上角的分享按钮，将此订单【分享给好友】，让土豪朋友替我买单<br/>
						隐私提醒：代付时，土豪可能会确认本订单的信息，如：收件人信息、地址、电话。【美妞儿】在此提醒您，务必注意避免将该订单发给陌生人
					</p>
				</div>
			</div>

        	<div class="container no-bottom">
            	<div class="section-title">
                	<h4><a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">女神订单信息</a></h4>
				</div>
            </div> 
            
            <div class="decoration"></div>
            <div class="container no-bottom">
            	<div class="section-title"> 
                	<h4>订单信息</h4>
                	<em></em> 
				</div>
				<p class="quote-item">
					订单号： ${orderInfo.outTradeNo}<br/>
					下单时间：${orderInfo.createTime}
				</p>

				<p class="quote-item">
					<%
					List<WxProductOrderItem> orderItemList = (List<WxProductOrderItem>)request.getAttribute("orderItemList"); 
					if(orderItemList!=null&&orderItemList.size()>0){
						for(WxProductOrderItem orderItem: orderItemList){
					%>  
						<img src="<%=orderItem.getProductPicUrl()%>" alt="<%=orderItem.getProductName() %>">
						<span class="text-highlight highlight-dark"><a href="${pageContext.request.contextPath}/product/<%=orderItem.getProductId()%>/<%=orderItem.getProductSkuId()%>"><%=orderItem.getProductName()%></a></span>
						X&nbsp;<span class="text-highlight highlight-blue"><%=orderItem.getAmount()%></span>件 
						=&nbsp;<span class="text-highlight highlight-green"><%=orderItem.getTotalFee()%>元</span><br/> <br/>
					<%} 
					}%>
				</p>
				
				<p class="quote-item">
					商品合计：
					<span class="text-highlight highlight-red">${orderInfo.productFee}</span>元
				</p>
				
				</div>
             
            <div class="decoration"></div>
            <div class="container" id="product-intro">
            	<div class="section-title">
                	<h4>发货信息</h4>
                	<em>&nbsp;</em>
				</div>
            	<ul id="choose">
            		<li>姓 名：&nbsp;<span id="postName" class="text-highlight highlight-green">${orderInfo.postName}</span></li>
	            	<li>手机号：&nbsp;<span id="postMobile" class="text-highlight highlight-red">${orderInfo.postMobile}</span></li>
	            	<li>收货地址：&nbsp;<span id="postAddress" class="text-highlight highlight-dark">${orderInfo.postProvince}${orderInfo.postCity}${orderInfo.postCountries}-${orderInfo.postAddressDetailInfo}</span></li>
	            	<li>邮 编：&nbsp;<span id="postCode" class="text-highlight highlight-yellow">${orderInfo.postCode}</span></li>
	            </ul>
	            <div class="decoration"></div>
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
                
                <%
                WxProductOrder orderInfo = (WxProductOrder)request.getAttribute("orderInfo");
                if(orderInfo.getStatus()==1){
                %>
                <div class="decoration"></div>
            	<div class="center-text">
	               	<a href="javascript:void(0)" id="wxPay" class="button-big button-green">微信支付</a>
            	</div> 
            	<%}%>
            </div>
            
            <div class="decoration"></div>
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>
</body>

<!-- 允许微信分享 -->
<jsp:include page="../inc/weixinHideOptionMenu.jsp?hideOpt=0"></jsp:include>

</html>
