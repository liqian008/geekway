<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

<%
String showTitle = "［情侣匹配度测试］";
String showImg = "http://imgqn.meiniur.com/images/activities/loverMatchLogo.jpg";
WxWebUser friendWebUser = (WxWebUser)request.getAttribute("friendWebUser");
if(friendWebUser!=null&&friendWebUser.getNickname()!=null){
	showTitle = "［"+friendWebUser.getNickname()+"］想和您进行"+showTitle;
	showImg = friendWebUser.getHeadImgUrl();
}

String shareTitle = "［情侣匹配度测试］- 【美妞儿】";
String shareImg = "http://imgqn.meiniur.com/avatar/default.jpg";
WxWebUser myWebUser = (WxWebUser)request.getAttribute("_currentWxUser");
if(myWebUser!=null&&myWebUser.getNickname()!=null){
	shareTitle = "［"+myWebUser.getNickname()+"］想和您进行"+shareTitle;
	System.out.println("====="+shareTitle);
	if(myWebUser.getHeadImgUrl()!=null&&!"".equals(myWebUser.getHeadImgUrl())){
		shareImg = myWebUser.getHeadImgUrl();
	} 
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
           	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">情侣匹配度结果</p> 
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
        </div>
        
        
        <div class="content"> 
			
			<div class="decoration"></div>
			<div class="container no-bottom">
				
				<div class="big-notification blue-notification"> 
					<h4><%=showTitle%></h4>
					<p>
						<strong>游戏提示：</strong><br/>
               			当您首次开始时，页面将弹起［微信授权］，用于获取您的生（ge）辰（ren）八（zi）字（liao），以进行匹配计算。<br/>
						更多有趣内容，请点击关注<a href="javascript:void(0)" style="color:#efe;font-size:14px"><strong>［美妞儿］</strong></a>公众账号</strong>
					</p>
				</div>
				
            	<div class="section-title">
            		
                </div>
            </div>
            
			<div class="container no-bottom center-text">
                <div class="portfolio-item-full-width" style="padding:0px 60px">
                    <img class="responsive-image" src="<%=showImg%>" alt="img">
                </div>
                <h4><%=showTitle%></h4>
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
		"title" : "<%=shareTitle%>",
		"desc" : "情侣匹配度测试",
		"link" : "http://wx.meiniur.com/activities/loverMatchIntro?friendOpenId=${_currentWxUser.openId}",
		"imgUrl" : "<%=shareImg%>",
	};
	
	//分享给朋友的内容
	window.friendShareData = {
		"title" : "<%=shareTitle%>",
		"desc" : "情侣匹配度测试",
		"link" : "http://wx.meiniur.com/activities/loverMatchIntro?friendOpenId=${_currentWxUser.openId}",
		"imgUrl" : "<%=shareImg%>",
	};

	wx.ready(function() {
		wx.onMenuShareAppMessage({//分享给朋友
			title : '<%=shareTitle%>',
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
			title : '<%=shareTitle%>',
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
