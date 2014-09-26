<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>支付</title>

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

</head>
<body>


<div class="all-elements">
	<jsp:include page="../inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">three column folio</p>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="index.html" class="content-logo"></a>
            <a href="http://www.facebook.com/enabled.labs" class="facebook-content"></a>
            <a href="https://twitter.com/iEnabled" class="twitter-content"></a>
        </div>
        
        <div class="content">
        	<div class="decoration"></div>

        	<div class="container no-bottom">
            	<div class="section-title">
                	<h4>Pure CSS3 Table</h4>
                    <em>a few words about this section</em>
                    <strong><img src="images/misc/icons/tables.png" width="20" alt="img"></strong>
                </div>
                <div class="decoration"></div>
            </div> 
            
            
            <div class="container no-bottom">
               	<p class="quote-item">
                   	<img src="images/general-nature/6s.jpg" alt="img">
                       Great product and awesome help to get for this! Many thanks mate!
                       <em>John Doe - ThemeForest Customer</em>
                   </p>
            </div>
            
            <div class="container no-bottom">
               <table cellspacing='0' class="table" id="orderTable">
                    <tr>
                        <th>订单信息</th>
                        <th class="table-title">收件人</th>
                        <th class="table-title">金额</th>
                        <th class="table-title">日期</th>
                        <th class="table-title">状态</th>
                    </tr>
                    <tr>
                        <td>
                        	<img src="images/general-nature/6s.jpg" alt="img"> 
                        	<img src="images/general-nature/6s.jpg" alt="img">
                        </td>
                        <td class="table-sub-title">bruce</td>
                        <td>￥120.00</td>
                        <td>2014.01.01</td>
                        <td>已发货</td>
                    </tr>
                </table> 
            </div>
            
            <div class="decoration"></div>      
           	<jsp:include page="../inc/footer.jsp"></jsp:include>
           	
           	 
        </div>                
    </div>  
</div>

</body>
</html>