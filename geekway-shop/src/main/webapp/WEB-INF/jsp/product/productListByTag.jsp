<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.utils.*" %>
<%@ page import="com.bruce.geekway.utils.ShopLinkUtil.SlideImage" %>

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

<title>【美妞儿】</title>

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
	int tagId = 0;
	WxProductTag productTag = (WxProductTag)request.getAttribute("productTag");
	if(productTag!=null){
		tagId = productTag.getId();
	}
	%>
	
    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb"><%=productTag.getName()%></p>
            <a href="${pageContext.request.contextPath}/cart/" class="deploy-cart"></a>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div> 
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
        </div>
        
        <div class="content">
        	<div class="container">
        		
        		<div class="decoration"></div> 
        		<jsp:include page="../inc/weixinShareNotification.jsp"></jsp:include>
        		
				<%
				List<SlideImage> slideImageList = (List<SlideImage>) request.getAttribute("slideImageList");
				if(slideImageList!=null&&slideImageList.size()>0){
            	%>
                <div class="slider-controls" data-snap-ignore="true">
					<%for(SlideImage slideImage: slideImageList){%>
					<div>
						<a href="<%=slideImage.getLink()%>"><img src="<%=slideImage.getImageUrl()%>" class="responsive-image"></a>
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
					<h4>
						<a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)"><%=productTag.getName()%></a>
					</h4>
				</div>
        	</div>
        	
        	<div id="moreProductsContainer" class="container center-text">
        		<a href="javascript:void(0)" id="moreProductsBtn" class="button button-turqoise">加载更多</a>
        		
        		<input type="hidden" id="tagId" name="tagId" value="<%=tagId%>"/>
        		<input type="hidden" id="pageNo" name="pageNo" value="1"/>
        	</div>
        	
        	<div class="decoration"></div>

			<jsp:include page="../inc/footer.jsp"></jsp:include>
        </div>
    </div>  
</div>

</body>


<script>
fallLoad();

 	$('#moreProductsBtn').click(function(){
 		fallLoad();
 	});
 	
 	function fallLoad(){
 		//置为数据加载状态
 		$('#moreProductsBtn').val("努力加载中...");
 		$('#moreAlbumsBtn').attr("disabled","disabled");
 		var pageNo = $("#pageNo").val();
 		var jsonData = {'tagId' : '1', 'pageNo' : pageNo};
 		$.post('<%=contextPath%>/moreTagProducts.json', jsonData, function(data) {
 			var result = data.result;
		if(result==1){
			$("#productsContainer").append(data.data.html);
				var nextPageNo = data.data.nextPageNo;
  				$("#pageNo").val(nextPageNo);
  				if(nextPageNo<=1){//无更多数据，则隐藏按钮
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