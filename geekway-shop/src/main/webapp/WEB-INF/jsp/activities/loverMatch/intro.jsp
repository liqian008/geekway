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

<jsp:include page="../../inc/jssdk.jsp"></jsp:include> 
<jsp:include page="../../inc/baiduAsyncStat.jsp"></jsp:include>

</head>

<body>

<%
WxWebUser friendWebUser = (WxWebUser)request.getAttribute("friendWebUser");
String title = "［情侣匹配度测试］";
String activityImg = "http://imgqn.meiniur.com/avatar/default.jpg";
if(friendWebUser!=null&&friendWebUser.getNickname()!=null){
	title = "\""+friendWebUser.getNickname()+"\"想和您进行"+title;
	activityImg = friendWebUser.getHeadImgUrl();
}
%>

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
					<h4><%=title%></h4>
					<p>
						<strong>游戏提示：</strong><br/>
               			当您首次开始时，页面将弹起［微信授权］，用于获取您的生（ge）辰（ren）八（zi）字（liao），以进行匹配计算。
					</p>
				</div>
				
            	<div class="section-title">
            		
                </div>
            </div>
            
			<div class="container no-bottom center-text">
                <div class="portfolio-item-full-width" style="padding:0px 60px">
                    <img class="responsive-image" src="<%=activityImg%>" alt="img">
                </div>
                <h4><%=title%></h4>
        	</div>
        	
        	<!-- <div class="decoration"></div>
			<div class="container no-bottom">
	       		<p class="left-text"> 
               		<strong>游戏规则：</strong><br/>
               		当您首次开始游戏时，页面将弹起［微信授权］，用于获取您的生（ge）辰（ren）八（zi）字（liao），并匹配计算。
               	</p>
	        </div> -->
			
			<div class="decoration"></div>
			<div class="container">
				<a href="./loverMatch?friendOpenId=${friendWebUser.openId}" id="matchSubmitBtn" class="button-big button-green button-fullscreen">进 入</a>
	        </div>
			
            <div class="decoration"></div>
            
            <jsp:include page="../../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>

</body>

<script>
	//分享到朋友圈的内容
	window.timeLineShareData = {
		"title" : "情侣匹配度测试 - 【美妞儿】",
		"desc" : "情侣匹配度测试",
		"link" : "http://wx.meiniur.com/activities/loverMatchIntro?friendOpenId=${_currentWxUser.openId}",
		"imgUrl" : "<%=activityImg%>",
	};
	
	//分享给朋友的内容
	window.friendShareData = {
		"title" : "情侣匹配度测试 - 【美妞儿】",
		"desc" : "情侣匹配度测试",
		"link" : "http://wx.meiniur.com/activities/loverMatchIntro?friendOpenId=${_currentWxUser.openId}",
		"imgUrl" : "<%=activityImg%>",
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
