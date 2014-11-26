<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import ="com.bruce.geekway.model.WxProductCart.CartProductSku" %>
<%@ page import="com.bruce.geekway.utils.ProductUtil.SlideImage" %>


<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>购物车商品信息</title>

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

<%

CartProductSku cartItem = (CartProductSku)request.getAttribute("cartItem");
WxProductSku cartProductSku = cartItem.getProductSku();
int buyAmount = cartItem.getAmount();
%>

<body>

<div class="all-elements">
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">${product.name}</p>
            <a href="${pageContext.request.contextPath}/cart/" class="deploy-cart"></a>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
            <a href="javascript:void(0)" id="shareToFriend" class="facebook-content"></a>
        </div>
        
        <div class="content"> 
	
			<div class="decoration"></div> 
        	<jsp:include page="../inc/weixinShareNotification.jsp"></jsp:include>

			<div class="container no-bottom">
				<div class="section-title">
					<h4>
						<a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">购物车商品</a>
					</h4>
				</div>
			</div>

			<div class="container">
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
           
            <div class="container no-bottom">
            	<div class="section-title">
                	<h4 class="center-text">${product.name}</h4>
                </div>
            </div> 
            
            <div class="container" id="product-intro">
            	<ul id="choose">
            		
            		<li>原 价：&nbsp;<span id="originPrice" class="text-highlight highlight-dark"><del><%=cartProductSku.getOriginPrice()%></del></span>元</li>
	            	<li>现 价：&nbsp;<span id="price" class="text-highlight highlight-red"><%=cartProductSku.getPrice()%></span>元</li>
	            	<li>库 存：&nbsp;<span id="leftStock" class="text-highlight highlight-yellow"><%=cartProductSku.getStock()%></span>件</li>
	            	
	            	<li id="choose-color" class="choose-color-shouji">
	            		<div class="dt">规格：</div>
	            		<div class="dd">
	            			<div class="item  selected">
	            				<b></b>
	            				<a href="javascript:void(0)" title="<%=cartProductSku.getSkuName()%>">
		            				<i><%=cartProductSku.getSkuName()%></i>
		            			</a>
	            			</div>
	            		</div>
	            	</li>
	            	<li>购买数量: <span id="buyAmount" class="text-highlight highlight-blue"><%=buyAmount%></span>件</li> 
            	</ul>
            	<input type="hidden" id="productSkuId" name="productSkuId" value="${currentProductSku.id}"/> 
            	
            	<%
            	boolean canBuy = false;
            	if(cartProductSku!=null && cartProductSku.getPrice()!=null&&cartProductSku.getPrice()>0&&cartProductSku.getStock()!=null&&cartProductSku.getStock()>0){
            		canBuy = true;
            	}
            	%>
                <a href="javascript:void(0)" id="buyNow" class="button-big button-green <%=canBuy?"":"gone"%>">保存修改</a>
                <a href="${pageContext.request.contextPath}/cart/removeFromCart?productSkuId=<%=cartProductSku.getId()%>" id="removeFromCart" class="button-big button-red <%=canBuy?"":"gone"%>">从购物车中移除</a>
                
                <div class="static-notification-red <%=canBuy?"gone":""%>" id="buyDisable">
                    <p class="center-text uppercase">本品暂时缺货</p>
                </div>
                
                <script>
					//点击购买操作
					$("#buyNow").click(function(){
						var buyAmount = "7";//$("#buyAmount").text();
						var productSkuId = $("#productSkuId").val();
						//检查商品&数量有效性
						var productInfoError = !isInteger(buyAmount) || !isInteger(productSkuId);
						if(productInfoError){
							alert("商品选择有误，请检查后重新提交");
							return;
						}
						location.href= "${pageContext.request.contextPath}/buy?buyAmount="+buyAmount+"&productSkuId="+productSkuId;
					});
					
				</script>
            </div>
            
            <div class="decoration"></div>
            
            <div class="container">
            	<div class="section-title">
                	<h4>商品详情</h4>
                </div>
                <div class="tabs">
                    <a href="#" class="tab-but tab-but-1 tab-active">商品描述</a>
                    <a href="#" class="tab-but tab-but-2">规格参数</a>
                </div>
                <div class="tab-content tab-content-1">
                    <p>
                        ${product.description}
                    </p>
                </div>
                <div class="tab-content tab-content-2">
                    <p>
                        ${product.param}
                    </p>
                </div>
            </div>
			
			                
            <div class="decoration"></div>
            
            <div id="recommendProductsContainer" class="container">
            	<div class="section-title">
                	<h4>猜您喜欢</h4>
                </div>
                <!-- 
                <p class="quote-item">
                	<img src="http://jinwanr.qiniudn.com/image/20140930/1_fb315d11ac58521c025082df3e0c4fff.jpg">
                    ${product.name}
                    <em>
					原 价：&nbsp;<span id='originPrice' class='text-highlight highlight-red'><del>3</del></span>元
					现 价：&nbsp;<span id='price' class='text-highlight highlight-green'>2</span>元
					</em>
                </p>
                 -->
            </div>
             
            <div class="decoration"></div>
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>                
    </div>  
</div>

</body>


<!-- 禁用微信分享 -->
<jsp:include page="../inc/weixinHideOptionMenu.jsp?hideOpt=1"></jsp:include>

<script>
recommendProducts();

function recommendProducts(){
	//置为数据加载状态
	var paramData = {};
	$.post('${pageContext.request.contextPath}/recommendProducts.json', paramData, function(data) {
		var result = data.result;
		if(result==1){
			$("#recommendProductsContainer").append(data.data.html);
		}
	})
}
</script>


</html>