<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

<%
String contextPath = request.getContextPath();
%>


<!DOCTYPE HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>美妞儿</title>

<link href="${pageContext.request.contextPath}/slideby/styles/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/framework.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/owl.carousel.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/owl.theme.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/swipebox.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/colorbox.css" rel="stylesheet" type="text/css">

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
            <p class="bread-crumb">introduction</p>
            <a href="contact.html" class="deploy-contact"></a>
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
                	<h4>${productSku.name}</h4> 
                    <em>${productSku.description}</em>
                    <strong><img src="images/misc/icons/flag.png" width="20" alt="img"></strong>
                </div>
                <p>
					${productSku.description}
                </p>
            </div>
            
            <div class="decoration"></div>
            
            <div class="container no-bottom">
            	<img class="responsive-image" src="images/misc/intro.png" alt="img">
            </div>
            
            <div class="decoration"></div>
            
            <div class="container no-bottom">
            	<div class="section-title">
                	<h4>Page features</h4>
                    <em>some of the features we've included</em>
                    <strong><img src="images/misc/icons/applications.png" width="20" alt="img"></strong>
                </div>
                <p>
					These are just a few of the awesome features we've included in this template! For more features,
                    swipe the body to the right, and check out the pages! You'll love em!
                </p>
            </div>
            
            <div class="decoration"></div>
            
            <div class="container no-bottom">
                <div class="one-half-responsive">
                    <h4>General Features</h4>
                    <ul>
                      <li>1, 2, 3 text columns</li>
                      <li>1, 2, 3 image columns</li>
                      <li>1, 2, 3 icon columns</li>
                      <li>CSS3 8 text highlight variations</li>
                      <li>CSS3 Code structures</li>
                      <li>CSS3 Table</li>
                      <li>CSS3 Speach bubbles</li>
                      <li>CSS3 Fields</li>
                      <li>CSS3 8 Color variation small buttons</li>
                      <li>CSS3 8 Color variation big buttons</li>
                      <li>CSS3 8 Buttons with 400 retina ready icons</li>
                      <li>CSS3 Hoirzontal square and rounded charts</li>
                      <li>4 different testimonial variations</li>
                      <li>48 icon lists</li>
                      <li>PSD Files Included</li>
                      <li>High DPI screen ready ( retina included )</li>
                      <li>Very well documented and explained</li>
                      <li>24/7 support for our buyers!</li>
                    </ul> 
                </div>
                <div class="decoration hide-if-responsive"></div>
                <div class="one-half-responsive last-column">
                    <h4>jQuery Features</h4>
                    <ul>
                        <li>Custom jQuery Code!</li>
                        <li>CSS3, AJAX, PHP contact form with validation</li>
                        <li>jQuery Page Preloader</li>
                        <li>jQuery Device detection</li>
                        <li>jQuery Tap sliding door</li>
                        <li>jQuery Submenus</li>
                        <li>jQuery 4 Toggle Variations</li>
                        <li>jQuery Tabs</li>
                        <li>jQuery Big Notifications</li>
                        <li>jQuery Small Notifications</li>
                        <li>jQuery Checkboxes</li>
                        <li>jQuery Radioboxes</li>
                        <li>jQuery Image Slider</li>
                        <li>jQuery Qute Slider</li>
                        <li>jQuery Text Slider</li>
                        <li>jQuery Thumbnail Slider</li>
                        <li>jQuery Colorbox Portofolio</li>
                        <li>jQuery Swipebox Touch Swipe Gallery</li>
                    </ul>
                </div> 
                <div class="clear"></div>
            </div>
            
            <div class="decoration"></div>      
            <div class="content-footer">
            	<p class="copyright-content">Copyright 2013.<br> All rights reserved</p>
                <a href="#" class="go-up-footer"></a>
                <a href="#" class="facebook-footer"></a>
                <a href="#" class="twitter-footer"></a>
                <div class="clear"></div>
            </div>
            
              
        </div>                
    </div>  
</div>

</body>
</html>

























