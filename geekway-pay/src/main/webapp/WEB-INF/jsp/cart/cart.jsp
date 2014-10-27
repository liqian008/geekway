<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import ="com.bruce.geekway.model.wx.pay.*" %>
<%@ page import ="com.bruce.geekway.model.WxProductCart.CartProductSku" %>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>我的购物车</title>

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

</head>
<body>

<div class="all-elements">
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">购物车</p>
            <a href="${pageContext.request.contextPath}/cart/" class="deploy-cart"></a>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
        </div>
        
        <div class="content"> 
	
			<div class="decoration"></div> 

        	<div class="container no-bottom">
            	<div class="section-title">
                	<h4><a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">我的购物车</a></h4>
				</div>
            </div> 
            
            <div class="decoration"></div>
            <div class="container">
            	<div class="section-title">
                	<h4>购物车清单</h4>
				</div>
				<%
				List<CartProductSku> cartItemList = (List<CartProductSku>)request.getAttribute("cartItemList");
				if(cartItemList!=null&&cartItemList.size()>0){
				%>
				<form action="${pageContext.request.contextPath}/buy" method="get" id="cartForm"> 	 
				<input type="hidden" name="cartBuy" value="true"/>
				<%
				int i=0;
				for(CartProductSku cartItem: cartItemList){
					i++;
				%>
					<input type="hidden" name="productSkuId" value="<%=cartItem.getProductSku().getId()%>"/>
					<input type="hidden" name="buyAmount" value="<%=cartItem.getAmount()+i%>"/>
				
            	<p class="quote-item">
                	<img src="<%=cartItem.getProductSku().getSkuPicUrl()%>" alt="<%=cartItem.getProductSku().getName() %>">
                    <em><%=cartItem.getProductSku().getName() %></em>
                    合计：&nbsp;<span class="text-highlight highlight-red"><%=cartItem.getProductSku().getPrice() %></span>元 X <span id="buyAmount" class="text-highlight highlight-blue"><%=cartItem.getAmount() %></span>件 = <span id="productTotalFee" class="text-highlight highlight-green"><%=cartItem.getAmount()*cartItem.getProductSku().getPrice() %></span>元
                    
                    |&nbsp;<span class="text-highlight highlight-dark"><a href="./cartItem?productSkuId=<%=cartItem.getProductSku().getId()%>">修改</a></span>
                    &nbsp;<span class="text-highlight highlight-dark"><a href="./removeFromCart?productSkuId=<%=cartItem.getProductSku().getId()%>">移除 X</a></span>
                </p>
                <%} %>
                <div class="center-text">
                	<a href="javascript:void(0)" id="submitOrder" class="button-big button-red">结算商品</a>
                </div>
                </form>
                <% 
                }%>
            </div>
            
            <div class="decoration"></div>
            <jsp:include page="../inc/footer.jsp"></jsp:include>
		</div>
	</div>  
</div>

</body>

<!-- 禁用微信分享 -->
<jsp:include page="../inc/weixinHideOptionMenu.jsp"></jsp:include>

<script>
$("#submitOrder").click(function(){
	$("#cartForm").submit();
});
</script>

</html>
