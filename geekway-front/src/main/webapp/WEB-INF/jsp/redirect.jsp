<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.constants.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>微信JS-SDK</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Responsive Minimal Bootstrap Theme">
    <meta name="keywords" content="responsive,minimal,bootstrap,theme">
    <meta name="author" content="">
    
	<!--[if lt IE 9]>
	<script src="${pageContext.request.contextPath}/js-sdk/js/html5shiv.js"></script>
    <link rel="stylesheet" href="css/ie.css" type="text/css">
	<![endif]-->

    <!-- CSS Files
    ================================================== -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js-sdk/css/main.css" type="text/css" id="main-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js-sdk/css/colors/aqua.css" type="text/css" id="main-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js-sdk/home-2.html#" id="boxed">

    <!-- Javascript Files
    ================================================== -->
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.isotope.min.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.prettyPhoto.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/easing.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.ui.totop.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/selectnav.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/ender.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.lazyload.js"></script>
    <script src="${pageContext.request.contextPath}／rs-plugin/js/jquery.themepunch.revolution.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.flexslider-min.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/custom.js"></script>

</head>

<%
String contextPath = request.getContextPath();
%>

<%
String prompt = (String) request.getAttribute(ConstFront.REDIRECT_PROMPT);
String redirectUrl = (String) request.getAttribute(ConstFront.REDIRECT_URL);
if(prompt==null||"".equals(prompt)){
	prompt = "操作成功，现在将转入后续页面，请稍候…";
}
if(redirectUrl==null||"".equals(redirectUrl)){
	redirectUrl = contextPath + "/";
}
%>

<body>
    <div id="wrapper">
        <!-- header begin -->
        <jsp:include page="./inc/header.jsp"></jsp:include>
        <!-- header close -->

        <!-- subheader begin -->
        <div id="subheader">
            <div class="container">
                <div class="row">
                    <div class="span12">
                        <h1>用户登录</h1>
                       <!--  <span>Some Cool Page Description.</span>
                        <ul class="crumb">
                            <li><a href="index.html">Home</a></li>
                            <li class="sep">/</li>
                            <li>Blog Right Sidebar</li>
                        </ul> -->
                    </div>
                </div>
            </div>
        </div>
        <!-- subheader close -->

        <div id="content">
            <div class="container">
                <div class="row">
                    <div class="span6 offset3">
                        <div class="de_testi">
                            <blockquote>
                                <p>
                               	<%=prompt%><br/><br/>
                               	如果您的浏览器没有自动跳转，请点击 <a href="<%=redirectUrl%>">此处链接</a> 进行跳转
                                </p>
                            </blockquote>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="./inc/footer.jsp"></jsp:include>

    </div>
    
</body>

<script language='Javascript'>
    setTimeout("location.href='<%=redirectUrl%>'", 2000);
</script>
	
<script>
$(document).ready(function(){
	$("#regBtn").click(function(){
		$("#commentform").submit();
	});
});
</script>
</html>
