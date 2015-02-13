<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

<%
WxWebUser friendWebUser = (WxWebUser)request.getAttribute("friendWebUser");
String friendHeadImg = "http://imgqn.meiniur.com/avatar/default.jpg";
String friendNickname = "暂无好友参与";
if(friendWebUser!=null&&friendWebUser.getNickname()!=null){
	friendHeadImg = friendWebUser.getHeadImgUrl();
	friendNickname = friendWebUser.getNickname();
}
%>

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
            	<div class="big-notification blue-notification"> 
					<h4>［情侣匹配度测试］</h4>
					<p>
						<strong>游戏规则：</strong><br/>
               			
					</p>
				</div>
            </div>
            
			<div class="container no-bottom">
            	<div class="one-half center-text">
					<img class="responsive-image" src="${_currentWxUser.headImgUrl}">
					<h2>${_currentWxUser.nickname}</h2>
					<p></p>
				</div>
				<div class="one-half last-column center-text">
					<img class="responsive-image" src="<%=friendHeadImg%>">
					<h2><%=friendNickname%></h2>
					<p></p>
				</div>
			</div>
			<div class="decoration"></div>
			
			<%if(friendWebUser!=null){ %>
			<div class="container">
				<a href="javascript:void(0)" id="matchSubmitBtn" class="button-big button-blue button-fullscreen">开始匹配</a>
	        </div>
	        <div class="decoration"></div>
            <%} %>
            
            <jsp:include page="../../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>

</body>

<script>

	$("#matchSubmitBtn").click(function() {
		$("#matchSubmitBtn").removeClass("button-blue").addClass("button-dark");
		var count = 0;
		var timer = setInterval(function(){
			count++;
			var num= count%6;
			$("#matchSubmitBtn").text("匹配度计算中，请稍候"+fillDots((6-num), 6));
			$(".responsive-image").css("opacity",(10-num)/10);
			if(count>=24){
				clearInterval(timer);
				window.timeLineShareData.title="朋友圈xxx情侣匹配结果为：";
				window.timeLineShareData.desc="朋友圈xxx情侣匹配结果为：";
				window.friendShareData.title="xxx情侣匹配结果为：";
				window.friendShareData.desc="xxx情侣匹配结果为：";
				
				$("#matchSubmitBtn").removeClass("button-dark").addClass("button-green");
				$("#matchSubmitBtn").text("计算完成，点击查看匹配结果：${luckyNumber}");
				$("#matchSubmitBtn").unbind();
				
			}
		}, 250);
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
		"link" : "http://wx.meiniur.com/activities/loverMatchIntro?friendOpenId=${_currentWxUser.openId}",
		"imgUrl" : "${_currentWxUser.headImgUrl}",
	};
	
	//分享给朋友的内容
	window.friendShareData = {
		"title" : "情侣匹配度测试 - 【美妞儿】",
		"desc" : "情侣匹配度测试",
		"link" : "http://wx.meiniur.com/activities/loverMatchIntro?friendOpenId=${_currentWxUser.openId}",
		"imgUrl" : "${_currentWxUser.headImgUrl}",
	};

	wx.ready(function() {
		wx.onMenuShareAppMessage({//分享给朋友
			title : window.friendShareData.title,
			desc : window.friendShareData.desc,
			link : window.friendShareData.link,
			imgUrl : window.friendShareData.imgUrl,
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
			title : window.timeLineShareData.title,
			desc : window.timeLineShareData.desc,
			link : window.timeLineShareData.link,
			imgUrl : window.timeLineShareData.imgUrl,
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
