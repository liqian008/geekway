<%@ page language="java" contentType="text/html; charset=utf-8"%>
<script>
function onBridgeReady() {
	WeixinJSBridge.call('hideOptionMenu');
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