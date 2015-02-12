<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.constants.*" %>

<%
String contextPath = request.getContextPath();

String prompt = (String) request.getAttribute(ConstFront.REDIRECT_PROMPT);
String redirectUrl = (String) request.getAttribute(ConstFront.REDIRECT_URL);
if(prompt==null||"".equals(prompt)){
	prompt = "操作成功，现在将转入后续页面，请稍候…";
}
if(redirectUrl==null||"".equals(redirectUrl)){
	redirectUrl = contextPath + "/";
}
%>


<!DOCTYPE HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>系统跳转</title>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/url.js"></script>

<jsp:include page="./inc/baiduAsyncStat.jsp"></jsp:include>
</head>
<body>


<div class="all-elements">
    <jsp:include page="./inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">系统跳转</p>
            <a href="contact.html" class="deploy-contact"></a>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
            <a href="http://www.facebook.com/enabled.labs" class="facebook-content"></a>
            <a href="https://twitter.com/iEnabled" class="twitter-content"></a>
        </div>
        
        <div class="content"> 
	
			<div class="decoration"></div> 

        	<div class="container no-bottom">
            	<div class="section-title">
                	<h4><a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">操作提示</a></h4>
				</div>
            </div> 
            
			<div class="decoration"></div>
        	<div class="container no-bottom">
	            <div class="big-notification blue-notification"> 
	                <h4 class="uppercase">系统跳转提示</h4> 
	                <p>
	                	<%=prompt%><br/>
						如果您的浏览器没有自动跳转，请点击 <a href="<%=redirectUrl%>">此处链接</a> 进行跳转
					</p> 
	            </div>
            </div>
            
            <div class="decoration"></div>
            <jsp:include page="./inc/footer.jsp"></jsp:include>
            
        </div>                
    </div>  
</div>

</body>

<script language='Javascript'>
    setTimeout("location.href='<%=redirectUrl%>'", 2000);
</script>
	
</html>