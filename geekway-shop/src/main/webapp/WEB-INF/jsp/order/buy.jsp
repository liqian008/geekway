<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import ="com.bruce.geekway.model.wx.pay.*" %>
<%@ page import ="com.bruce.geekway.model.WxProductCart.CartProductSku" %>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>购买商品</title>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/common.js"></script>

</head>
<body>

<div class="all-elements">
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">123</p>
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
                	<h4><a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">订单确认</a></h4>
				</div>
            </div> 
            
			<%
			List<WxProductVoucher> availableVoucherList = (List<WxProductVoucher>)request.getAttribute("availableVoucherList");
			if(availableVoucherList!=null&&availableVoucherList.size()>0){
			%>
            <div class="decoration"></div>
        	<div id="vouchersContainer" class="container no-bottom">
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
            </div>
	            <%}%>
			<%}%>
            
            <div class="decoration"></div>
            <div class="container">
            	<div class="section-title">
                	<h4>订单信息</h4>
				</div>
				<%
				List<CartProductSku> cartItemList = (List<CartProductSku>)request.getAttribute("cartItemList");
				if(cartItemList!=null&&cartItemList.size()>0){
				for(CartProductSku cartItem: cartItemList){%>
            	<p class="quote-item">
                	<img src="<%=cartItem.getProductSku().getSkuPic1Url()%>" alt="<%=cartItem.getProductSku().getName() %>">
                    <em><%=cartItem.getProductSku().getName() %></em>
                    合计：&nbsp;<span class="text-highlight highlight-red"><%=cartItem.getProductSku().getPrice() %></span>元 X <span id="buyAmount" class="text-highlight highlight-blue"><%=cartItem.getAmount() %></span>件 = <span id="productTotalFee" class="text-highlight highlight-green"><%=cartItem.getAmount()*cartItem.getProductSku().getPrice() %></span>元
                </p>
                <input type="hidden" id="productSkuId" class="paramField" name="productSkuId" value="<%=cartItem.getProductSku().getId() %>"/>
                <input type="hidden" id="productSkuId" class="paramField" name="buyAmount" value="<%=cartItem.getAmount() %>"/>
	            <%}
	            }%>
	            
                <div class="decoration"></div> 
                <p class="quote-item">
                    <h5 class="center-text"> 
                    运费：&nbsp;<span id="deliveryFee" class="text-highlight highlight-blue">0.00</span>元&nbsp;|&nbsp;
                    <!-- 
                    优惠：&nbsp;<span id="buyAmount" class="text-highlight highlight-yellow">-0.00</span>元&nbsp;|&nbsp;
                     -->
                    总计：&nbsp;<span id="totalFee" class="text-highlight highlight-green">${totalFee}</span>元
                    <input type="hidden" id="hiddenTotalFee" class="paramField" name="hiddenTotalFee" value="${totalFee}"/>
                    <input type="hidden" id="cartBuy" class="paramField" name="cartBuy" value="${cartBuy}"/>
                    <input type="hidden" id="selfPay" class="paramField" name="selfPay" value="1"/>
                    </h5>
                </p>
                <div class="center-text">
                	<a href="javascript:void(0)" id="submitOrder" class="button-big button-green">微信支付</a>
                	<a href="javascript:void(0)" id="shareOrder" class="button-big button-orange">我想找人，替我买单</a>
                </div>
            </div>
            
            <div class="decoration"></div>
            <div class="container" id="product-intro">
            	<div class="section-title">
                	<h4>发货信息</h4>
                	<em>&nbsp;</em>
				</div>
            	<ul id="choose">
            		<li>
            		姓 名：&nbsp;<span id="postName" class="text-highlight highlight-green">无</span>
            		<input type="hidden" id="hiddenPostName" class="paramField" name="postName" value=""/>
            		</li>
	            	<li>
	            	手机号：&nbsp;<span id="postMobile" class="text-highlight highlight-red">无</span>
	            	<input type="hidden" id="hiddenPostMobile" class="paramField" name="postMobile" value=""/>
	            	</li>
	            	<li>邮寄地址：&nbsp;
		            	<span id="postProvince" class="text-highlight highlight-dark">省</span>
		            	<span id="postCity" class="text-highlight highlight-dark">市</span>
		            	<span id="postCountries" class="text-highlight highlight-dark">区/县</span>
		            	<span id="postAddressDetailInfo" class="text-highlight highlight-dark"></span>
		            	
		            	<input type="hidden" id="hiddenPostProvince" class="paramField" name="postProvince" value=""/>
            			<input type="hidden" id="hiddenPostCity" class="paramField" name="postCity" value=""/>
            			<input type="hidden" id="hiddenPostCountries" class="paramField" name="postCountries" value=""/>
            			<input type="hidden" id="hiddenAddressDetailInfo" class="paramField" name="postAddressDetailInfo" value=""/>
	            	</li>
	            	<li>
	            	邮 编：&nbsp;<span id="postCode" class="text-highlight highlight-yellow">无</span>
	            	<input type="hidden" id="hiddenPostCode" class="paramField" name="postCode" value=""/>
	            	</li>
	            </ul>
	            <input type="hidden" class="paramField" id="postNationalCode" name="postNationalCode" value=""/>
	            <div class="center-text">
					<a href="javascript:void(0)" id="chooseAddress" class="button-big button-dark">请选择收获地址</a>
				</div>
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

$("#shareOrder").click(function(){
	var postName = $("#hiddenPostName").val();
	var postMobile = $("#hiddenPostMobile").val();
	
	var postProvince = $("#hiddenPostProvince").val();
	var postCity = $("#hiddenPostCity").val();
	var postCountries = $("#hiddenPostCountries").val();
	var postAddressDetailInfo = $("#postAddressDetailInfo").val();
	
	//检查商品&数量有效性
	
	//检查地址输入有效性
	/* var postInfoError = isEmpty(postName) || isEmpty(postMobile) || isEmpty(postProvince) || isEmpty(postCity)|| isEmpty(postCountries)|| isEmpty(postAddressDetailInfo);
	if(postInfoError){
		alert("收货地址填写有误，请重新选择");
		return;
	} */
	
	$('#chooseAddress').hide();//隐藏地址按钮
	$('#shareOrder').text("代付订单提交中...");
	$('#shareOrder').attr("disabled","disabled");
	
	var paramData = $(".paramField").serialize();
	//alert(paramData);
	//var cartBuy = $("#cartBuy").val();//是否来自购物车
	
	//var paramData = {'productSkuId':productSkuId, 'buyAmount':buyAmount, 'cartBuy':cartBuy, 'postName' : postName, 'postMobile':postMobile, 'postProvince':postProvince, 'postCity':postCity, 'postCountries':postCountries, 'postAddressDetailInfo':postAddressDetailInfo, 'postCode':postCode, 'postNationalCode':postNationalCode};
	$.post('${pageContext.request.contextPath}/submitOrder.json', paramData, function(responseData) {
		var result = responseData.result;
		if(result==1){
			$('#shareOrder').text("代付订单创建成功");
			$('#shareOrder').removeClass("");
			
			var tradeNo = responseData.data.tradeNo;
			location.href= "${pageContext.request.contextPath}/orderInfoAngle?tradeNo="+tradeNo;
		}else{
			alert(responseData.message);
		} 
	} , "json");
});


$("#chooseAddress").click(function(){
	try{
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
			alert(res.err_msg);
			if(res.err_msg=="edit_address:ok"){
				$("#postName").text(res.userName);
				$("#postMobile").text(res.telNumber);
				$("#postCode").text(res.addressPostalCode);
				$("#postNationalCode").val(res.nationalCode);
				
				$("#provice").text(res.proviceFirstStageName);
				$("#city").text(res.addressCitySecondStageName);
				$("#country").text(res.addressCountiesThirdStageName);
				$("#postAddressDetailInfo").text(res.postAddressDetailInfo);
				
				$("#chooseAddress").text("重新选择收货地址");
				
				refreshDeliveryFee(10, 3, res.proviceFirstStageName, res.addressCitySecondStageName);
			}else{
				alert("获取用户收货地址失败");
			}
		}
	);
	}catch(e){
		$("#postName").text("fail: name");
		$("#hiddenPostName").val("fail: name");
		$("#postMobile").text("fail: mobile");
		$("#hiddenPostMobile").val("fail: mobile");
		$("#postCode").text("fail: code");
		$("#hiddenPostCode").val("fail: code");
		$("#hiddenPostNationalCode").val("fail: nationalCode");
		
		
		$("#postProvince").text("fail:province");
		$("#hiddenPostProvince").val("fail:province");
		$("#postCity").text("fail:city");
		$("#hiddenPostCity").val("fail:city");
		
		$("#postCountries").text("fail: country");
		$("#hiddenPostCountries").val("fail: country");
		
		$("#postAddressDetailInfo").text("fail:postAddressDetailInfo");
		$("#hiddenAddressDetailInfo").val("fail:postAddressDetailInfo");
		
		$("#chooseAddress").text("重新选择收货地址");
		
		refreshDeliveryFee(10, 3, '3','4');
	}
})

function refreshDeliveryFee(totalProductFee, totalAmount, province, city){
	//ajax重新计算运费
	var paramData = {'totalProductFee': totalProductFee, 'totalAmount': totalAmount, 'country':'', 'province':province, 'city':city};
	$.post('${pageContext.request.contextPath}/calcDeliverFee.json', paramData, function(responseData) {
		var result = responseData.result;
		if(result==1){ 
			//展示运费价格
			var deliveryFee=  fmoney(responseData.data.deliveryFee, 2);
			$("#deliveryFee").text(deliveryFee);
			//刷新总价格
			var productTotalFee = $("#hiddenTotalFee").val();
			var totalFee = accAdd(deliveryFee, productTotalFee);
			totalFee=  fmoney(totalFee, 2);
			$("#totalFee").text(totalFee);
		}else{
			alert(responseData.message);
		}
	} , "json");
}

</script>
<%}%>

<!-- 禁用微信分享 -->
<jsp:include page="../inc/weixinHideOptionMenu.jsp?hideOpt=1"></jsp:include>
</html>
