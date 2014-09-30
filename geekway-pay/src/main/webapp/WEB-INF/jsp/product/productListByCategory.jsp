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

<title>美妞儿</title>

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
	
	<%
	int categoryId = 0;
	WxProductCategory productCategory = (WxProductCategory)request.getAttribute("productCategory");
	if(productCategory!=null){
		categoryId = productCategory.getId();
	}
	%>
	
    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb"><%=productCategory.getName()%></p>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="index.html" class="content-logo"></a>
            <a href="http://www.facebook.com/enabled.labs" class="facebook-content"></a>
            <a href="https://twitter.com/iEnabled" class="twitter-content"></a>
        </div>
        
        <div class="content">
        	<div class="decoration"></div>

        	<div class="container no-bottom" id="productsContainer">
        	</div>
        	
        	<div id="moreProductsContainer" class="container center-text">
        		<a href="javascript:void(0)" id="moreProductsBtn" class="button button-dark">加载更多</a>
        		
        		<input type="hidden" id="categoryId" name="categoryId" value="<%=categoryId%>"/>
        		<input type="hidden" id="tailId" name="tailId" value="0"/>
        	</div>
        	
            <div class="decoration"></div>

           	<jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>

</body>
<!-- 微信分享 -->
<jsp:include page="../inc/weixinShareJs.jsp"></jsp:include>

<script>
	fallLoad();

  	$('#moreProductsBtn').click(function(){
  		fallLoad();
  	});
  	
  	function fallLoad(){
  		//置为数据加载状态
  		$('#moreProductsBtn').val("努力加载中...");
  		$('#moreAlbumsBtn').attr("disabled","disabled");
  		var tailId = $("#tailId").val();
  		var jsonData = {'categoryId' : '1', 'tailId' : tailId};
  		$.post('${pageContext.request.contextPath}/moreProducts.json', jsonData, function(data) {
  			var result = data.result;
			if(result==1){
				$("#productsContainer").append(data.data.html);
 				var nextTailId = data.data.tailId;
   				$("#tailId").val(nextTailId);
   				if(nextTailId<=0){//无更多数据，则隐藏按钮
  					$('#moreProductsContainer').attr("style","display:none");
  				}else{//还有更多数据，启用加载按钮
  					$('#moreProductsBtn').removeAttr("disabled");
  					$('#moreProductsBtn').val("加载更多...");
  				}
			}
		})
	}
</script>

</html>