<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.constants.*" %>

<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>【美妞儿】首页</title>

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
        	<a href="./#" class="deploy-sidebar"></a>
            <p class="bread-crumb">美妞儿</p>
            <a href="${pageContext.request.contextPath}/cart/" class="deploy-cart"></a>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        
        <div class="content">
        	<div class="decoration"></div>
            
            <div class="container">
            	<div class="section-title">
                	<h4>时尚爆款</h4>
                    <em>最时尚的韩流服饰</em>
                </div>
            	
            	<%
				List<SlideImage> slideImageList = (List<SlideImage>) request.getAttribute("slideImageList");
				if(slideImageList!=null&&slideImageList.size()>0){
            	%>
                <div class="slider-controls" data-snap-ignore="true">
					<%for(SlideImage slideImage: slideImageList){%>
					<div>
						<a href="<%=slideImage.getLink()%>"><img src="<%=slideImage.getPicUrl()%>" class="responsive-image"></a>
					</div>
					<%}%>
				</div>
                <a href="javascript:void(0)" class="next-slider"></a>
                <a href="javascript:void(0)" class="prev-slider"></a>
                <%}%>
            </div>
            
            <div class="decoration"></div>

			<div class="container no-bottom" id="productsContainer">
				<div class="section-title">
                	<h4>韩版专区</h4>
                	<em>just a few words to start</em>
                </div>
				<div class="portfolio-item-thumb one-half ">
					<a href="http://localhost:8080/geekway-pay/product/66">
					<img class="responsive-image" src="/geekway-pay/slideby/images/general-nature/2s.jpg"/>
						<h4>null</h4>
						<ul id="choose">
							<li>原 价：&nbsp;<span id="price"
								class="text-highlight highlight-dark"><del>7.0</del></span>元
							</li>
							<li>现 价：&nbsp;<span id="price"
								class="text-highlight highlight-red">6.0</span>元
							</li>
						</ul></a>
				</div>
				<div class="portfolio-item-thumb one-half  last-column">
					<a href="http://localhost:8080/geekway-pay/product/65">
					<img class="responsive-image" src="/geekway-pay/slideby/images/general-nature/2s.jpg"/>
						<h4>null</h4>
						<ul id="choose">
							<li>原 价：&nbsp;<span id="price"
								class="text-highlight highlight-dark"><del>6.0</del></span>元
							</li>
							<li>现 价：&nbsp;<span id="price"
								class="text-highlight highlight-red">5.2</span>元
							</li>
						</ul></a>
				</div>
			</div>
			
			<div class="decoration"></div>
            <div class="container no-bottom">
            	<div class="section-title">
                	<h4>Testimonials!</h4>
                    <em>just a few words to start</em>
                </div>
            	<div class="one-half-responsive">
                	<p class="quote-item">
                    	<img src="slideby/images/general-nature/6s.jpg" alt="img">
                        Great product and awesome help to get for this! Many thanks mate!
                        <em>John Doe - ThemeForest Customer</em>
                    </p>
                </div>
                <div class="one-half-responsive last-column">
                	<p class="quote-item">
                    	<img src="slideby/images/general-nature/2s.jpg" alt="img">
                        Fast support, awesome file, good  docs, this rocks! Thank you for all!
                        <em>John Doe - ThemeForest Customer</em>
                    </p>
                </div>
            </div> 

			<div class="decoration"></div>
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>
</body>


</html>