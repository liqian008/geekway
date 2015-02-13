<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>情侣匹配度测试 - 【美妞儿】</title>


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

<style>
.change{-webkit-transition:all .7s ease-out .1s; -moz-transition:all .7s ease-out .1s; -o-transition:all .7s ease-out .1s; transition:all .7s ease-out .1s}
#lbox{position:absolute;top:0;left:0; width:100%; max-width:600px; max-height:600px;background-color:#aaa;}
        .changelbox{position:absolute;top:0;left:0;width:18%;height:18%; background-color:#333;-webkit-transition:all .7s ease-out .1s; -moz-transition:all .7s ease-out .1s; -o-transition:all .7s ease-out .1s; transition:all .7s ease-out .1s}
#inbox{position:absolute;top:0;left:0; width:100%; max-width:600px; max-height:600px;z-index:2;}
.random_current {background-color:#fff;}
.changeinbox{position:absolute;top:0;left:0;width:18%;height:18%; }
</style>

<jsp:include page="../../inc/jssdk.jsp"></jsp:include> 
<jsp:include page="../../inc/baiduAsyncStat.jsp"></jsp:include>

</head>

<body>

<div class="all-elements">

    <div id="content" class="page-content">
    	<div class="page-header">
            <p class="bread-crumb">${product.name}</p>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
        </div>
        
        <div class="content"> 
			
			<div class="decoration"></div>
			<div class="container no-bottom">
            	<div class="section-title">
                	<h4>情侣匹配度测试</h4>
                </div>
            </div>
            
			<div class="container no-bottom">
            	<div class="one-half center-text">
					<img class="responsive-image" src="${_currentWxUser.headImgUrl}">
					<h2>${_currentWxUser.nickname}</h2>
				</div>
				<div class="center-text">
					<img class="responsive-image" src="http://tp3.sinaimg.cn/1407260150/180/5614558400/1">
					<h2>李乾</h2>
				</div>
			</div>
			<div class="decoration"></div>
			<div class="container">
				<a href="javascript:void(0)" id="matchSubmitBtn" class="button-big button-blue button-fullscreen">开始测试</a>
	        </div>
            <div class="decoration"></div>
            
            <jsp:include page="../../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>

</body>

<script>

	$("#matchSubmitBtn").click(function() {
		$("#matchSubmitBtn").removeClass("button-blue").addClass("button-dark");
		//$(this).text("匹配度计算中，请稍候...");
		/* setTimeout(function() {
			$("#matchSubmitBtn").removeClass("button-blue").addClass("button-green");
			$("#matchSubmitBtn").text("计算完成，点击查看匹配结果");
			console.log("需要构造几组情侣匹配度的结果（图文方式）");
		}, 2000); */
		var count = 0;
		var timer = setInterval(function(){
			count++;
			var xxx= count%6;
			$("#matchSubmitBtn").text("匹配度计算中，请稍候"+fillDots((6-xxx), 6));
			if(count>=18){
				$("#matchSubmitBtn").removeClass("button-dark").addClass("button-green");
				$("#matchSubmitBtn").text("计算完成，点击查看匹配结果");
				clearInterval(timer);
			}
		}, 400);
	})
	
	function fillDots(nowLength, maxLength){
		var text = "";
		while(nowLength<maxLength){
			text+=".";
			nowLength++;
		}
		return text;
	}
	
	//分享到朋友圈的内容
	window.timeLineShareData = {
		"title" : "情侣匹配度测试 - 【美妞儿】",
		"desc" : "情侣匹配度测试",
		"link" : "http://wx.meiniur.com/lucky/loverMatch", 
		"imgUrl" : "${_currentWxUser.headImgUrl}",
	};
	
	//分享给朋友的内容
	window.friendShareData = {
		"title" : "情侣匹配度测试 - 【美妞儿】",
		"desc" : "情侣匹配度测试",
		"link" : "http://wx.meiniur.com/lucky/loverMatch", 
		"imgUrl" : "${_currentWxUser.headImgUrl}",
	};

	wx.ready(function() {
		wx.onMenuShareAppMessage({//分享给朋友
			title : "情侣匹配度测试 - 【美妞儿】",
			desc : "情侣匹配度测试 - 【美妞儿】",
			link : "http://wx.meiniur.com/lucky/loverMatch",
			imgUrl :  "${_currentWxUser.headImgUrl}",
			trigger : function(res) {
			},
			success : function(res) {
			},
			cancel : function(res) {
			},
			fail : function(res) {
			}
		});
		
		wx.onMenuShareTimeline({//朋友圈分享
			title : "情侣匹配度测试 - 【美妞儿】",
			desc : "情侣匹配度测试 - 【美妞儿】",
			link : "http://wx.meiniur.com/lucky/loverMatch",
			imgUrl :  "${_currentWxUser.headImgUrl}",
			trigger : function(res) {
			},
			success : function(res) {
			},
			cancel : function(res) {
			},
			fail : function(res) {
			}
		});
	});
</script>

</html>
