<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Demo JS-SDK</title>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <!-- 引用js-sdk插件 -->
    <script>
	(function() {
		var jssdkEle = document.createElement("script");
		jssdkEle.type = "text/javascript";
		var currentUrl = encodeURIComponent(location.href.split('#')[0]);
		//jssdkEle.src = "http://api.js-sdk.com.cn/api/wxJsConfigSrc?wxAppId=wxc2a0a71ba13664f6&nonce=nonceVal&sign=signVal&pageUrl="+currentUrl;
		//开发、测试阶段如需开启debug模式，请在wxJssdkSrc中增加debug=true的参数，请使用下行
		jssdkEle.src = "http://api.js-sdk.com.cn/api/wxJsConfigSrc?debug=true&wxAppId=wxc2a0a71ba13664f6&nonce=nonceVal&sign=signVal&pageUrl="+currentUrl;
		var allJsEles = document.getElementsByTagName("script")[0];
		allJsEles.parentNode.insertBefore(jssdkEle, allJsEles);
	})();
	</script>
	<!-- 微信配置&回调文案 -->
	<script>
		//分享到朋友圈的内容
		window.timeLineShareData = {
			"title" : "分享标题（From朋友圈）",
			"desc" : "分享内容（From朋友圈）",
			"link" : "www.baidu.com",
			"imgUrl" : "http://www.baidu.com/img/baidu_jgylogo3.gif",
		};
		
		//分享给朋友的内容
		window.friendShareData = {
			"title" : "分享标题（From好友）",
			"desc" : "分享内容（From好友）",
			"link" : "www.baidu.com",
			"imgUrl" : "http://www.baidu.com/img/baidu_jgylogo3.gif",
		};
		
		wx.ready(function () {
	        wx.onMenuShareTimeline({
	            title: window.timeLineShareData.title, // 分享标题
	            desc: window.timeLineShareData.desc, // 分享标题
	            link: window.timeLineShareData.link, // 分享链接
	            imgUrl: window.timeLineShareData.imgUrl, // 分享图标
	            success: function () {
	               alert("成功");
	            },
	            cancel: function () {
	            	alert("取消");
	            }
	        });
	        
	        wx.onMenuShareAppMessage({
	            title: window.friendShareData.title, // 分享标题
	            desc: window.friendShareData.desc, // 分享标题
	            link: window.friendShareData.link, // 分享链接
	            imgUrl: window.friendShareData.imgUrl, // 分享图标
	            success: function () {
	               alert("好友分享成功");
	            },
	            cancel: function () {
	            	alert("好友分享取消");
	            }
	        });
		});
	</script>
</head>

<body>
提醒：<br/>
1、开发者可在微信中打开本页面，验证js-sdk分享等功能。<br/>
2、请务必引入微信的js文件：http://res.wx.qq.com/open/js/jweixin-1.0.0.js<br/>
3、开发、测试阶段如需开启debug模式，请在js中的wxJssdkSrc中增加debug=true的参数
</body>



</html>
