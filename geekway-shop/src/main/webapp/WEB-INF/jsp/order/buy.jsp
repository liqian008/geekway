<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.foundation.util.*" %>
<%@ page import ="com.bruce.geekway.model.wx.pay.*" %>
<%@ page import ="com.bruce.geekway.model.ProductCart.CartProductSku" %>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>购买商品【美妞儿】</title>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/url.js"></script>

<jsp:include page="../inc/baiduAsyncStat.jsp"></jsp:include>
</head>
<body>

<div class="all-elements">
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">订单确认</p>
            <a href="${pageContext.request.contextPath}/cart/" class="deploy-cart"></a>
            <!-- <a href="javascript:void(0)" class="deploy-refresh"></a> --> 
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
            
            <div class="decoration"></div>
            <div class="container">
            	<div class="section-title">
                	<h4>订单信息</h4>
				</div>
				<%
				List<CartProductSku> cartItemList = (List<CartProductSku>)request.getAttribute("cartItemList");
				if(cartItemList!=null&&cartItemList.size()>0){
					for(CartProductSku cartItem: cartItemList){
					%>
	            	<p class="quote-item">
	                	<img src="<%=cartItem.getProductSku().getSkuPicUrl()%>" alt="<%=cartItem.getProductSku().getName() %>">
	                    <em><%=cartItem.getProductSku().getName() %></em>
	                    商品合计：&nbsp;<span class="text-highlight highlight-red"><%=cartItem.getProductSku().getPrice() %></span>元 X <span id="buyAmount" class="text-highlight highlight-blue"><%=cartItem.getAmount() %></span>件 = <span id="productTotalFee" class="text-highlight highlight-green"><%=cartItem.getAmount()*cartItem.getProductSku().getPrice() %></span>元
	                </p>
	                <input type="hidden" id="productSkuId" class="paramField" name="productSkuId" value="<%=cartItem.getProductSku().getId() %>"/>
	                <input type="hidden" id="productSkuId" class="paramField" name="buyAmount" value="<%=cartItem.getAmount() %>"/>
	            <%}
	            }%>
	            
                <div class="decoration"></div> 
                <h5 class="center-text"> 
                商品：&nbsp;<span id="productFee" class="text-highlight highlight-blue">${totalFee}</span>元&nbsp;|&nbsp; 
            
                运费：&nbsp;<span id="deliveryFee" class="text-highlight highlight-red">0</span>元&nbsp;|&nbsp;
                <!-- 
                优惠：&nbsp;<span id="buyAmount" class="text-highlight highlight-yellow">-0.00</span>元&nbsp;|&nbsp;
                 -->
                总计：&nbsp;<span id="totalFee" class="text-highlight highlight-green">${totalFee}</span>元
                <input type="hidden" id="hiddenTotalFee" class="paramField" name="hiddenTotalFee" value="${totalFee}"/>
                <input type="hidden" id="cartBuy" class="paramField" name="cartBuy" value="${cartBuy}"/>
                <input type="hidden" id="selfPay" class="paramField" name="selfPay" value="1"/>
                </h5>
            </div>
            
            
            <div class="decoration"></div>
            <div class="container" id="product-intro">
            	<div class="section-title">
                	<h4>收货信息</h4>
                	<em>&nbsp;</em>
				</div>
            	<ul id="choose">
            		<li>
            		姓 名：&nbsp;<span id="postName" class="text-highlight highlight-green">无</span>
            		<input type="hidden" id="hiddenPostName" class="paramField" name="postName" value=""/>
            		</li>
	            	<li>
	            	手机号：&nbsp;<span id="postMobile" class="text-highlight highlight-blue">无</span>
	            	<input type="hidden" id="hiddenPostMobile" class="paramField" name="postMobile" value=""/>
	            	</li>
	            	<li>邮寄地址：&nbsp;
		            	<span id="postProvince" class="text-highlight highlight-dark">省</span>
		            	<span id="postCity" class="text-highlight highlight-dark">市</span>
		            	<span id="postCountry" class="text-highlight highlight-dark">区/县</span>
		            	<span id="postAddressDetailInfo" class="text-highlight highlight-dark"></span>
		            	
		            	<input type="hidden" id="hiddenPostProvince" class="paramField" name="postProvince" value=""/>
            			<input type="hidden" id="hiddenPostCity" class="paramField" name="postCity" value=""/>
            			<input type="hidden" id="hiddenPostCountry" class="paramField" name="postCountry" value=""/>
            			<input type="hidden" id="hiddenPostAddressDetailInfo" class="paramField" name="postAddressDetailInfo" value=""/>
	            	</li>
	            	<li>
	            	邮 编：&nbsp;<span id="postCode" class="text-highlight highlight-yellow">无</span>
	            	<input type="hidden" id="hiddenPostCode" class="paramField" name="postCode" value=""/>
	            	</li> 
	            </ul>
	            <input type="hidden" class="paramField" id="postNationalCode" name="postNationalCode" value=""/>
	            <div class="center-text">
					<a href="javascript:void(0)" id="chooseAddress" class="button-big button-dark">请选择收货地址</a>
				</div>
            </div>
            
            <div class="decoration"></div>
            <div class="container">
                <div class="center-text">
                	<a href="javascript:void(0)" id="wxpayBtn" class="button-big button-green button-fullscreen payBtn">自付【微支付】</a>
                	<!-- <a href="javascript:void(0)" id="alipayBtn" class="button-big button-orange button-fullscreen payBtn">自付【支付宝】</a> -->
                	<a href="javascript:void(0)" id="shareOrderBtn" class="button-big button-orange button-fullscreen payBtn">让土豪朋友【代付】</a>
                	<p class="left-text"> 
                		说明：
                		当您使用“代付”功能时，页面将弹起［微信授权］，以便于获取您的个人资料，供您的“土豪朋友”确认。
                	</p>
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
/* $("#wxpayBtn").click(function(){
	alert("支付能力申请中...暂不可用");
});
$("#alipayBtn").click(function(){
	alert("支付宝支付能力申请中...暂不可用");
}); */

$(".payBtn").click(function(){
	var choosedBtn = $(this);
	var submitBtnId = choosedBtn.attr("id");
	var payType = 0;//微支付
	if(submitBtnId=="shareOrderBtn"){
		payType=2;
	}
	
	var postName = $("#hiddenPostName").val();
	var postMobile = $("#hiddenPostMobile").val();
	
	var postProvince = $("#hiddenPostProvince").val();
	var postCity = $("#hiddenPostCity").val();
	var postCountry = $("#hiddenPostCountry").val();
	var postAddressDetailInfo = $("#hiddenPostAddressDetailInfo").val();
	
	//检查商品&数量有效性
	
	//检查地址输入有效性
	/* alert(postName);
	alert(postMobile);
	alert(postProvince);
	alert(postCity);
	alert(postCountries);
	alert(postAddressDetailInfo); */
	
	var postInfoError = isEmpty(postName) || isEmpty(postMobile) || isEmpty(postProvince) || isEmpty(postCity)|| isEmpty(postCountry)|| isEmpty(postAddressDetailInfo);
	if(postInfoError){
		alert("收货地址填写有误，请重新选择");
		return;
	}
	
	$('#chooseAddress').attr("disabled",true);
	$('#chooseAddress').hide();//隐藏地址按钮
	//$('#shareOrderBtn').text("代付订单提交中...");
	//$('#shareOrderBtn').attr("disabled","disabled");
	
	var paramData = $(".paramField").serialize();
	
	//var paramData = {'productSkuId':productSkuId, 'buyAmount':buyAmount, 'cartBuy':cartBuy, 'postName' : postName, 'postMobile':postMobile, 'postProvince':postProvince, 'postCity':postCity, 'postCountries':postCountries, 'postAddressDetailInfo':postAddressDetailInfo, 'postCode':postCode, 'postNationalCode':postNationalCode};
	$.post('${pageContext.request.contextPath}/pay/submitOrder.json', paramData, function(responseData) {
		var result = responseData.result;
		if(result==1){
			//$('#shareOrderBtn').removeClass("");
			var tradeNo = responseData.data.tradeNo;
			if(payType==2){
				choosedBtn.text("代付订单创建成功...");
				location.href= "${pageContext.request.contextPath}/pay/orderInfoShare?tradeNo="+tradeNo;
			}else{//微信支付
				choosedBtn.text("订单创建成功，正在跳转支付页...");
				var tradeNo = responseData.data.tradeNo;
				var appId = responseData.data.wxPayJsObj.appId;
				var timeStamp = responseData.data.wxPayJsObj.timeStamp;
				var nonceStr = responseData.data.wxPayJsObj.nonceStr;
				var packageValue = responseData.data.wxPayJsObj.packageValue;
				var signType = responseData.data.wxPayJsObj.signType;
				var paySign = responseData.data.wxPayJsObj.paySign;
				
				WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId" :  appId, //公众号名称，由商户传入
					"timeStamp" : timeStamp, //时间戳
					"nonceStr" : nonceStr, //随机串
					"package" : packageValue,//扩展包
					"signType" : signType, //微信签名方式:1.sha1
					"paySign" : paySign
				//微信签名
				}, function(res) {
					if (res.err_msg == "get_brand_wcpay_request:ok") {
						//支付成功
						//页面跳转
						/* var successUrl = '${pageContext.request.contextPath}/paySuccess?appKey=${appKey}&orderId='+tradeNo;
						setTimeout(function(){location.href=successUrl;}, 1000); */
						//alert("支付成功");
						choosedBtn.text("订单支付成功，正在返回订单详情页...");
						setTimeout(function(){location.href= "${pageContext.request.contextPath}/pay/orderInfo?tradeNo="+tradeNo;;}, 1000);
					}else{
						choosedBtn.text("订单支付失败，正在返回订单详情页...");
						setTimeout(function(){location.href= "${pageContext.request.contextPath}/pay/orderInfo?tradeNo="+tradeNo;;}, 1000);
						//alert("支付失败");
						//js支付有错误，提交日志记录
						/* var currentUrl = window.location.href;
						var failUrl = "${pageContext.request.contextPath}/payFailed?appKey=${appKey}&orderId="+tradeNo;
						if(res.err_msg=="system:access_denied"||res.err_msg=="get_brand_wcpay_request:fail_invalid appid"||res.err_msg=="getBrandWCPayRequest:fail_invalid appid"){
							//支付失败
						}
						failUrl = "${pageContext.request.contextPath}/payFailed?errorCode=1&appKey=${appKey}&orderId="+tradeNo;
						setTimeout(function(){location.href=failUrl;}, 1000); */
					}
					// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
					//因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面。
				});
			}
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
			if(res.err_msg=="edit_address:ok"){
				$("#postName").text(res.userName);
				$("#postMobile").text(res.telNumber);
				$("#postCode").text(res.addressPostalCode);
				$("#postNationalCode").val(res.nationalCode);
				$("#postProvince").text(res.proviceFirstStageName);
				$("#postCity").text(res.addressCitySecondStageName);
				$("#postCountry").text(res.addressCountiesThirdStageName);
				$("#postAddressDetailInfo").text(res.addressDetailInfo);
				
				$("#hiddenPostName").val(res.userName);
				$("#hiddenPostMobile").val(res.telNumber);
				$("#hiddenPostCode").val(res.addressPostalCode);
				$("#hiddenPostNationalCode").val(res.nationalCode);
				$("#hiddenPostProvince").val(res.proviceFirstStageName);
				$("#hiddenPostCity").val(res.addressCitySecondStageName);
				$("#hiddenPostCountry").val(res.addressCountiesThirdStageName);
				$("#hiddenPostAddressDetailInfo").val(res.addressDetailInfo);
				
				$("#chooseAddress").text("重新选择收货地址");
				//refreshDeliveryFee(10, 3, res.proviceFirstStageName, res.addressCitySecondStageName);
			}else{
				alert("用户收货地址有误");
				//chooseAddressFailed();
			}
		}
	);
	}catch(e){
		chooseAddressFailed();
		//refreshDeliveryFee(10, 3, '3','4');
	}
})

/* function refreshDeliveryFee(totalProductFee, totalAmount, province, city){
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
} */

function chooseAddressFailed(){
	//TODO 以下是为了在测试账户下能有地址显示
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
	
	$("#postCountry").text("fail: country");
	$("#hiddenPostCountry").val("fail: country");
	
	$("#postAddressDetailInfo").text("fail:postAddressDetailInfo");
	$("#hiddenPostAddressDetailInfo").val("fail:postAddressDetailInfo");
	
	$("#chooseAddress").text("重新选择收货地址");
}
</script>
<%}%>


<!-- 微信默认分享 -->
<jsp:include page="../inc/weixinShareJsDefault.jsp"></jsp:include>


</html>
