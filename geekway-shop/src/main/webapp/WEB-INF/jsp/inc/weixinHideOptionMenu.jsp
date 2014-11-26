<%@ page language="java" contentType="text/html; charset=utf-8"%>

<%
String hideOpt = request.getParameter("hideOpt");
String menuOption = "showOptionMenu";
if("1".equals(hideOpt)){
	menuOption = "hideOptionMenu"; 
}
%>
<script>
function onBridgeReady() {
	WeixinJSBridge.call('<%=menuOption%>');
}

if (typeof WeixinJSBridge == "undefined") {
	if (document.addEventListener) {
		document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
				false);
	} else if (document.attachEvent) {
		document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
		document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	}
} else {
	onBridgeReady();
}
</script>