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

</head>

<%
	//选中的sku属性map
ProductSku currentProductSku = (ProductSku)request.getAttribute("currentProductSku");
Map<Integer, List<SkuPropValue>> skuGroupMap = (Map<Integer, List<SkuPropValue>>)request.getAttribute("skuGroupMap");
%>

<body>

<div class="all-elements">
    <jsp:include page="./inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">${product.name}</p>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
        </div>
        
        <div class="content"> 
			
			<div class="decoration"></div>
			<div class="container no-bottom">
            	<div class="section-title">
                	<h4>关于【美妞儿】</h4>
                </div>
                <p>
					xxxx
                </p>
            </div>
            
            <div class="decoration"></div>
            <div class="container no-bottom">
            	<img class="responsive-image" src="http://imgqn.jinwanr.com/images/icon/mp_qrcode.jpg">
            </div>
            
            <div class="container">
            	<div class="container">
                    <div class="submenu-navigation button-green">
                        <a href="#" class="submenu-nav-deploy">流量合作</a>
                        <div class="submenu-nav-items">
                            <a href="tel:13810637222">联系电话：13810637222</a>
                            <a href="mailto:liqian008@sina.com">电子邮箱：liqian008@sina.com</a>
                        </div>
                    </div>
                </div>
            
            	<div class="container">
                    <div class="submenu-navigation button-orange">
                        <a href="#" class="submenu-nav-deploy">内容合作</a>
                        <div class="submenu-nav-items">
                            <a href="tel:13810637222">联系电话：13810637222</a>
                            <a href="mailto:liqian008@sina.com">电子邮箱：liqian008@sina.com</a>
                        </div>
                    </div>
                </div>
            
				<!-- <div class="sliding-door">
					<div class="sliding-door-top button-green">
						<a href="#"><em></em> 流量合作 </a>
					</div>
					<div class="sliding-door-bottom button-green">
						<a href="tel:13810637222"><em></em> <strong>联系电话:</strong>13810637222
						</a>
					</div>
				</div>
				<div class="sliding-door-clear"></div>
				
				<div class="sliding-door">
					<div class="sliding-door-top button-orange">
						<a href="#"><em></em>内容合作 </a>
					</div>
					<div class="sliding-door-bottom button-orange">
						<a href="tel:13810637222"><em></em> <strong>联系电话:</strong>13810637222
						</a>
					</div>
				</div>
				<div class="sliding-door-clear"></div> -->
			</div>
            
            <div class="decoration"></div> 
            
            <jsp:include page="./inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>

</body>

</html>
