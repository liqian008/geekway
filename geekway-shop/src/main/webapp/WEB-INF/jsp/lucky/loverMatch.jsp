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

<title>${currentProductSku.name}【美妞儿】</title>


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

<jsp:include page="../inc/baiduAsyncStat.jsp"></jsp:include>

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
					<img class="responsive-image" src="http://img10.360buyimg.com/n0/jfs/t370/116/572252638/310514/af36a4af/542397f8N4ec1be8f.jpg">
					<h2>毛衣</h2>
				</div>
			</div>
			<div class="decoration"></div>
			<div class="container">
				<a href="javascript:void(0)" id="matchSubmitBtn" class="button-big button-green button-fullscreen">开始测试</a>
	        </div>
            <div class="decoration"></div>
            
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>

</body>

<script>
$("#matchSubmitBtn").click(function(){
	$(this).text("匹配度计算中...");
	setTimeout(function(){$("#matchSubmitBtn").text("匹配度计算完成");alert("需要构造几组情侣匹配度的结果（图文方式）");}, 3000);
})

</script>
</html>
