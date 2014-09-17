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
        	<a href="./#" class="deploy-sidebar"></a>
            <p class="bread-crumb">welcome</p>
            <a href="contact.html" class="deploy-contact"></a>
        </div>
        <div class="content-header">
        	<a href="./" class="content-logo"></a>
            <a href="http://www.facebook.com/enabled.labs" class="facebook-content"></a>
            <a href="https://twitter.com/iEnabled" class="twitter-content"></a>
        </div>
        
        <div class="content">
        	<div class="decoration"></div>
            
            <div class="container">
                <div class="slider-controls" data-snap-ignore="true">                
                    <div>
                        <img src="images/general-nature/3.jpg" class="responsive-image" alt="img">
                        <p class="title-slider-caption">
                            <strong>Welcome</strong>
                            <em>We make your mobile and tablet rock!</em>
                        </p>
                    </div>
                
                    <div>
                        <img src="images/general-nature/2.jpg" class="responsive-image" alt="img">
                        <p class="small-slider-caption">A small caption</p>
                    </div>

                    <div>
                        <img src="images/general-nature/1.jpg" class="responsive-image" alt="img">
                        <p class="title-slider-caption">
                            <strong>A caption title</strong>
                            <em>A caption text and what not!</em>
                        </p>
                    </div>
                </div>
                <a href="./#" class="next-slider"></a>
                <a href="./#" class="prev-slider"></a>
            </div>
                   
            <div class="decoration"></div>
            
            <div class="container no-bottom">
            	<div class="section-title">
                	<h4>Well, hello there!</h4>
                    <em>just a few words to start</em>
                    <strong><img src="images/misc/icons/leaf.png" width="20" alt="img"></strong>
                </div>
                <p>When it comes to user experience, we spare no expense to make our pages look awesomesauce and run even better! As an elite author we strive to give you nothing else but the best! </p>
            </div>
            
            <div class="decoration"></div>

            <div class="container no-bottom">
            	<div class="section-title">
                	<h4>Services we provides!</h4>
                    <em>just a few words to start</em>
                    <strong><img src="images/misc/icons/cog2.png" width="20" alt="img"></strong>
                </div>
                <p>Services or quotes have a major role in any template, so having a responsive slider that provides the ability to include any content is vital! It's touch enabled as well! Enjoy</p>
            </div>
            
            <div class="decoration"></div>
            
    		<div class="container">
                <a href="./#" class="next-quote"></a>
                <a href="./#" class="prev-quote"></a>
                <div class="quote-slider" data-snap-ignore="true">
                    <div>
                        <div class="services-item">
                            <img src="images/general-nature/3s.jpg" alt="img">
                            <h4>Mobile Web</h4>
                            <em>for your device</em>
                            <strong>We love quotes, and sometimes it's annoying to see tons of them that you need to scroll to!</strong>
                       		<a href="./#" class="button button-blue center-button">Mail</a>
                        </div>
                    </div>
                    <div>
                        <div class="services-item">
                            <img src="images/general-nature/4s.jpg" alt="img">
                            <h4>Tablet Design</h4>
                            <em>for your tablets</em>
                            <strong>We love quotes, and sometimes it's annoying to see tons of them that you need to scroll to!</strong>
                        	<a href="./#" class="button button-dark center-button">Read More</a>
                        </div>
                    </div>
                    <div>
                        <div class="services-item">
                            <img src="images/general-nature/5s.jpg" alt="img">
                            <h4>Web Design</h4>
                            <em>front end developer</em>
                            <strong>We love quotes, and sometimes it's annoying to see tons of them that you need to scroll to!</strong>
                        	<a href="./#" class="button button-orange center-button">Facebook</a>
                        </div>
                    </div>
                    <div>
                        <div class="services-item">
                            <img src="images/general-nature/6s.jpg" alt="img">
                            <h4>Graphic Design</h4>
                            <em>front end designs</em>
                            <strong>We love quotes, and sometimes it's annoying to see tons of them that you need to scroll to!</strong>
                        	<a href="./#" class="button button-yellow center-button">Twitter</a>
                        </div>
                    </div>
                </div>
            </div>      
            
            <div class="decoration"></div>                    

            <div class="container no-bottom">
            	<div class="section-title">
                	<h4>Testimonials!</h4>
                    <em>just a few words to start</em>
                    <strong><img src="images/misc/icons/usercomment.png" width="20" alt="img"></strong>
                </div>
                <p>
                	So we included a testimonials section, that is responsive, it goes in awesome mode
                    on tablets simply making it into two columns! You can make responsive columns with 
                    great ease by adding a simple class to your regular column classes!
                </p>
            </div>
            <div class="decoration"></div>
            <div class="container no-bottom">
            	<div class="one-half-responsive">
                	<p class="quote-item">
                    	<img src="images/general-nature/6s.jpg" alt="img">
                        Great product and awesome help to get for this! Many thanks mate!
                        <em>John Doe - ThemeForest Customer</em>
                    </p>
                </div>
                <div class="one-half-responsive last-column">
                	<p class="quote-item">
                    	<img src="images/general-nature/2s.jpg" alt="img">
                        Fast support, awesome file, good  docs, this rocks! Thank you for all!
                        <em>John Doe - ThemeForest Customer</em>
                    </p>                
                </div>
            	<div class="one-half-responsive">
                	<p class="quote-item">
                    	<img src="images/general-nature/3s.jpg" alt="img">
                        Thanks for the awesome item, just what I was searching for all along!
                        <em>John Doe - ThemeForest Customer</em>
                    </p>
                </div>
                <div class="one-half-responsive last-column">
                	<p class="quote-item">
                    	<img src="images/general-nature/4s.jpg" alt="img">
                        Fast loading, great support, easy to use, everything I asked for!
                        <em>John Doe - ThemeForest Customer</em>
                    </p>                
                </div>
            </div>  
            
            <div class="decoration"></div>
            
            <div class="container no-bottom">
            	<div class="section-title">
                	<h4>Recent Works!</h4>
                    <em>everyone likes to showoff a little</em>
                    <strong><img src="images/misc/icons/hd.png" width="20" alt="img"></strong>
                </div>
 				<p>You can share you awesome or latest projects in a simple and gorgeous touchscreen gallery! Here! </p>
            </div>
            <div class="container no-bottom">
                <ul class="gallery square-thumb">
                    <li>
                        <a class="swipebox" href="images/gallery/full/1.jpg" title="An image caption!">
                        <img src="images/general-nature/1s.jpg" alt="img" /></a>
                    </li>
                    <li>
                        <a class="swipebox" href="images/gallery/full/2.jpg" title="It can change!">
                        <img src="images/general-nature/2s.jpg" alt="img" /></a>
                    </li>
                    <li>
                        <a class="swipebox" href="images/gallery/full/3.jpg" title="To be whatever you want!">
                        <img src="images/general-nature/3s.jpg" alt="img" /></a>
                    </li>
                    <li>
                        <a class="swipebox" href="images/gallery/full/1.jpg" title="It's connected to the href!">
                        <img src="images/general-nature/4s.jpg" alt="img" /></a>
                    </li>
                    <li>
                        <a class="swipebox" href="images/gallery/full/2.jpg" title="Easy to change and edit!">
                        <img src="images/general-nature/5s.jpg" alt="img" /></a>
                    </li>
                    <li>
                        <a class="swipebox" href="images/gallery/full/3.jpg" title="What an awesome gallery!">
                        <img src="images/general-nature/6s.jpg" alt="img" /></a>
                    </li>
                </ul>   
            </div>
            
            <div class="decoration"></div>
            
            <div class="content-footer">
            	<p class="copyright-content">Copyright 2013.<br> All rights reserved</p>
                <a href="./#" class="go-up-footer"></a>
                <a href="./#" class="facebook-footer"></a>
                <a href="./#" class="twitter-footer"></a>
                <div class="clear"></div>
            </div> 
            
              
        </div>                
    </div>  
</div>

</body>
</html>