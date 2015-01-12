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


<title>我的订单【美妞儿】</title>

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
            <p class="bread-crumb">我的订单</p>
            <a href="${pageContext.request.contextPath}/cart/" class="deploy-cart"></a>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="./" class="content-logo"></a>
        </div>
        
        <div class="content">
        	<div class="decoration"></div>
        	
			<div class="container no-bottom">
				<div class="big-notification blue-notification"> 
					<h4 class="uppercase">订单介绍</h4>
					<a href="#" class="close-big-notification">x</a>
					<p>
					</p>
				</div>
			</div>
			
			<div class="container no-bottom">
            	<div class="section-title">
					<h4><a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">我的订单</a></h4>
				</div>
			</div>
			
			<div class="decoration"></div>
			<div id="ordersContainer" class="container no-bottom">
				<div class="section-title">
                	<h4>我的订单</h4>
                    <em></em>
                </div>
            </div>
			
            <div id="moreOrdersContainer" class="container center-text">
        		<a href="javascript:void(0)" id="moreOrdersBtn" class="button button-dark">加载更多</a>
        		<input type="hidden" id="pageNo" name="pageNo" value="1"/>
        	</div>
        	
        	<div id="emptyContainer" class="container center-text gone" >
        		<a href="${pageContext.request.contextPath}/index" class="button button-blue">订单记录为空，快去选购些商品吧</a>
	        </div>

			<div class="decoration"></div>
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>
</body>


<script>
	fallLoad();

  	$('#moreOrdersBtn').click(function(){
  		fallLoad();
  	});
  	
  	function fallLoad(){
  		//置为数据加载状态
  		$('#moreOrdersBtn').text("努力加载中...");
  		$('#moreOrdersBtn').attr("disabled","disabled");
  		var pageNo = $("#pageNo").val();
  		var jsonData = {'pageNo' : pageNo};
  		$.post('${pageContext.request.contextPath}/moreOrders.json', jsonData, function(data) {
  			var result = data.result;
			if(result==1){
				$("#ordersContainer").append(data.data.html);
 				var nextPageNo = data.data.nextPageNo;
   				$("#pageNo").val(nextPageNo); 
   				if(nextPageNo<=1){//无更多数据，则隐藏更多按钮
  					//$('#moreOrdersContainer').attr("style","display:none");
  					//$("#buyContainer").show();
   					$('#moreOrdersContainer').hide();
  				}else{//还有更多数据，启用加载按钮
  					$('#moreOrdersBtn').removeAttr("disabled");
  					$('#moreOrdersBtn').text("加载更多...");
  				}
			}else{
				$('#moreOrdersContainer').hide();
				$("#emptyContainer").show();
			}
		})
	}
</script>


<!-- 微信默认分享 -->
<jsp:include page="../inc/weixinShareJsDefault.jsp"></jsp:include>

</html>