<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.wx.pay.*" %>

<!DOCTYPE html>
<html>
<head>
<title>公众号支付测试产品</title>
<script language="javascript" src="http://res.mail.qq.com/mmr/static/lib/js/jquery.js"></script>
<script language="javascript" src="http://res.mail.qq.com/mmr/static/lib/js/lazyloadv3.js"></script>

<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta id="viewport" name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1; user-scalable=no;" />


<%
WxPayItemJsObj itemJsObj = (WxPayItemJsObj)request.getAttribute("itemJsObj");
%>

<script language="javascript">
	// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		//公众号支付
		jQuery('a#getBrandWCPayRequest').click(function(e) {
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
				}
				// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
				//因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面。
			});
		});
		WeixinJSBridge.log('yo~ ready.');
	}, false)

</script>
</head>
<body>

	<div class="WCPay">
		<p>微信支付JSAPI测试页面</p>
		<p>请将您申请公众账号支付权限的四个参数替换页面中的参数：partnerid、partnerkey、appid、appkey</p>
		<p>将此页面放在的支付授权测试目录下，测试微信号需添加白名单，并在公众账号内发起访问此页面
		<p>
		<p>即可检查公众账号支付功能是否正常</p>
		<p></p>
		<a id="getBrandWCPayRequest" href="javascript:void(0);"><h1
				class="title">点击提交测试</h1></a>
	</div>

</body>
</html>