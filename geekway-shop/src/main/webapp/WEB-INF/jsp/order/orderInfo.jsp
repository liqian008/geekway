<%@page import="com.bruce.geekway.model.enumeration.GeekwayEnum"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import ="com.bruce.geekway.model.wx.pay.*" %>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>我的订单详情【美妞儿】</title>

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
            <p class="bread-crumb">我的订单详情</p>
            <a href="${pageContext.request.contextPath}/cart/" class="deploy-cart"></a>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
        </div>
        
        <div class="content"> 
	
			<div class="decoration"></div> 

        	<div class="container no-bottom">
            	<div class="section-title">
                	<h4><a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">订单详情</a></h4>
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
                	<h4>收件人信息</h4>
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
                
                总计：&nbsp;<span id="totalPrice" class="text-highlight highlight-green">${orderInfo.totalFee}</span>元
                </h5>
                
            </div>
            
            
            <%
            WxProductOrder orderInfo = (WxProductOrder)request.getAttribute("orderInfo");
            if(orderInfo.getStatus()==GeekwayEnum.ProductOrderStatusEnum.SUBMITED.getStatus()){
            %>
            <div class="decoration"></div>
        	<div class="container center-text">
            	<a href="javascript:void(0)" id="wxpayBtn" class="button-big button-green button-fullscreen">自付【微支付】</a>
               	<a href="javascript:void(0)" id="alipayBtn" class="button-big button-orange button-fullscreen">自付【支付宝】</a>
               	<a href="javascript:void(0)" id="shareOrderBtn" class="button-big button-blue button-fullscreen">让土豪朋友【代付】</a>
        	</div> 
        	<%}%>
            <div class="decoration"></div>
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>
</body>

<%
WxPayItemJsObj itemJsObj = (WxPayItemJsObj)request.getAttribute("wxPayJsObj");
%>

<script language="javascript">
	// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		//公众号支付
		jQuery('a#wxPay').click(function(e) {
			WeixinJSBridge.invoke('getBrandWCPayRequest', {
				"appId" : "<%=itemJsObj.getAppId()%>", //公众号名称，由商户传入
				"timeStamp" : "<%=itemJsObj.getTimeStamp()%>", //时间戳
				"nonceStr" : "<%=itemJsObj.getNonceStr()%>", //随机串
				"package" : "<%=itemJsObj.getPackageValue()%>",//扩展包
				"signType" : "<%=itemJsObj.getSignType()%>", //微信签名方式:1.sha1
				"paySign" : "<%=itemJsObj.getPaySign()%>"
			//微信签名
			}, function(res) {
				if (res.err_msg == "get_brand_wcpay_request:ok") {
					payComplete();
				}
				// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
				//因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面。
			});
		});
	}, false);
</script>


</html>
