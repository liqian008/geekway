<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import ="com.bruce.geekway.model.wx.pay.*" %>

<!DOCTYPE HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>支付</title>

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
            <p class="bread-crumb">123</p>
            <a href="contact.html" class="deploy-contact"></a>
        </div>
        <div class="content-header">
        	<a href="index.html" class="content-logo"></a>
            <a href="http://www.facebook.com/enabled.labs" class="facebook-content"></a>
            <a href="https://twitter.com/iEnabled" class="twitter-content"></a>
        </div>
        
        <div class="content"> 
	
			<div class="decoration"></div> 

        	<div class="container no-bottom">
            	<div class="section-title">
                	<h4><a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">订单确认</a></h4>
				</div>
            </div> 
            
			<div class="decoration"></div>
        	<div id="vouchersContainer" class="container no-bottom">
				<%
				List<WxProductVoucher> availableVoucherList = (List<WxProductVoucher>)request.getAttribute("availableVoucherList");
				if(availableVoucherList!=null&&availableVoucherList.size()>0){
				%>
					<div class="section-title">
	                	<h4>选择使用优惠券</h4>
	                </div>
	                <%for(WxProductVoucher voucher: availableVoucherList){%>
	            	<div>
		            	<p class="quote-item">
			            	<input type="radio"/>
			            	优惠券信息： 编号 <%=voucher.getVoucherCode()%> | 金额 <%=voucher.getPrice()%>元  | 状态 可用
		            	</p> 
		            </div>
		            <%}%>
				<%}%>
            </div>
            
            <div class="decoration"></div>
            <div class="container">
            	<div class="section-title">
                	<h4>订单信息</h4>
				</div>
            	<p class="quote-item">
                	<img src="${pageContext.request.contextPath}/slideby/images/general-nature/6s.jpg" alt="img">
                    <em>${productSku.name}——${productSku.skuName}</em>
                    单价：&nbsp;<span class="text-highlight highlight-red">${productSku.price}</span>元&nbsp;|&nbsp;
                    数量：&nbsp;<span class="text-highlight highlight-blue">${amount}</span>件
                </p> 
                <div class="decoration"></div> 
                <p class="quote-item">
                    <h5 class="center-text"> 
                    优惠券折扣：&nbsp;<span id="buyAmount" class="text-highlight highlight-yellow">-0.00</span>元&nbsp;|&nbsp;
                    合计：&nbsp;<span id="totalPrice" class="text-highlight highlight-green">${totalPrice}</span>元
                    </h5>
                </p>
                <div class="center-text">
                	<a href="javascript:void(0)" id="submitOrder" class="button-big button-red">确认无误，提交订单</a>
                </div> 
            </div>
            
            <div class="decoration"></div>
            <div class="container center-text">
                <a href="javascript:void(0)" id="chooseAddress" class="button-big button-dark">请选择收获地址</a>
            </div>
            
            <div class="decoration"></div>
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>                
    </div>  
</div>

</body>


<%
WxOrderAddressJsObj orderAddressJsObj = (WxOrderAddressJsObj)request.getAttribute("orderAddressJsObj");
if(orderAddressJsObj!=null){
%>
<script>
$("#chooseAddress").click(function(){
	alert("choose");
	//获取微信共享地址
	WeixinJSBridge.invoke('editAddress',{
		"appId" : "<%=orderAddressJsObj.getAppId()%>",
		"scope" : "<%=orderAddressJsObj.getScope()%>",
		"signType" : "<%=orderAddressJsObj.getSignType()%>",
		"addrSign" : "<%=orderAddressJsObj.getAddrSign()%>",
		"timeStamp" : "<%=orderAddressJsObj.getTimeStamp()%>",
		"nonceStr" : "<%=orderAddressJsObj.getNonceStr()%>" 
		},function(res){
			//若res 中所带的返回值不为空,则表示用户选择该返回值作为收货地址。否则若返回空,则表示用户取消了这一次编辑收货地址。
			alert(res.proviceFirstStageName);
			alert(res.addressCitySecondStageName);
			//document.form1.address1.value =res.proviceFirstStageName;
			//document.form1.address2.value =res.addressCitySecondStageName;
			//document.form1.address3.value =res.addressCountiesThirdStageName;
			//document.form1.detail.value = res.addressDetailInfo;
			//document.form1.phone.value = res.telNumber;
		}
	);
})
</script>
<%}%>
</html>
