<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>


<!DOCTYPE HTML>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/map.js"></script>

</head>
<body>

<div class="all-elements">
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">${product.name}</p>
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="index.html" class="content-logo"></a>
            <a href="http://www.facebook.com/enabled.labs" class="facebook-content"></a>
            <a href="https://twitter.com/iEnabled" class="twitter-content"></a>
        </div>
        
        <div class="content"> 
	
			<div class="decoration"></div> 
        	<jsp:include page="../inc/weixinShareNotification.jsp"></jsp:include>

				<div class="container no-bottom">
					<div class="section-title">
						<h4>
							<a href="${pageContext.request.contextPath}/index">首页</a>&nbsp;/&nbsp;<a href="javascript:void(0)">热销</a>
						</h4>
					</div>
				</div>

				<div class="container">
                <div class="slider-controls" data-snap-ignore="true">                
                    <div>
                        <img src="http://wximg.geekway.com.cn/staticFile/image/20140522/original/1_1f32d71a505e80bbfb612f97f062f1f3.jpg" class="responsive-image" alt="img">
                    </div>
                
                    <div>
                        <img src="http://wximg.geekway.com.cn/staticFile/image/20140522/original/1_1f32d71a505e80bbfb612f97f062f1f3.jpg" class="responsive-image" alt="img">
                    </div>

                </div>
                <a href="./#" class="next-slider"></a>
                <a href="./#" class="prev-slider"></a>
            </div>
            
            <div class="decoration"></div> 
           
            <div class="container no-bottom">
            	<div class="section-title">
                	<h4 class="center-text">${product.name}</h4> 
                </div>
            </div> 
            
            <div class="container" id="product-intro">
            	<ul id="choose">
            		<%
	            	//选中的sku属性map
	            	WxProductSku currentProductSku = (WxProductSku)request.getAttribute("currentProductSku");
	            	Map<Integer, List<WxSkuPropValue>> skuGroupMap = (Map<Integer, List<WxSkuPropValue>>)request.getAttribute("skuGroupMap");
	            	%>
            		<li>原 价：&nbsp;<span id="originPrice" class="text-highlight highlight-dark"><del>${currentProductSku.originPrice}</del></span>元</li>
	            	<li>现 价：&nbsp;<span id="price" class="text-highlight highlight-red">${currentProductSku.price}</span>元</li>
	            	<li>库 存：&nbsp;<span id="leftAmount" class="text-highlight highlight-yellow">${currentProductSku.amount}</span>件</li>
	            	
	            	<%
	            	if(skuGroupMap!=null&&skuGroupMap.get(1)!=null){
	            	%>
	            	<li id="choose-color" class="choose-color-shouji">
	            		<div class="dt">选择颜色：</div>
	            		<div class="dd">
							<%
							List<WxSkuPropValue> colorSkuValueList = skuGroupMap.get(1);
							for(WxSkuPropValue skuPropValue : colorSkuValueList){
								String displayName = skuPropValue.getName();
								String propertyName = skuPropValue.getSkuPropId()+":"+skuPropValue.getId()+";";
								
								boolean colorSelected = currentProductSku!=null&&skuPropValue.getId().equals(currentProductSku.getSkuColorValueId());
							%>	            		
	            			<div class="item <%=colorSelected?"selected":""%>" data="<%=propertyName%>">
	            				<b></b>
	            				<a href="#none" title="<%=displayName%>">
			            			<img data-img="1" src="http://img14.360buyimg.com/n9/jfs/t154/186/206532666/68265/1ee30751/53842794N21159018.jpg" width="25" height="25" alt="<%=displayName%> ">
		            				<i><%=displayName%></i>
		            			</a>
	            			</div>
	            			<%}%>
	            		</div>
	            	</li>
	            	<%}%>
	            	
	            	<%
	            	if(skuGroupMap!=null&&skuGroupMap.get(2)!=null){
	            	%>
	            	<li id="choose-version" class="choose-version-shouji">
	            		<div class="dt">选择尺码：</div>
	            		<div class="dd">
	            			<%
							List<WxSkuPropValue> sizeSkuValueList = skuGroupMap.get(2);
							for(WxSkuPropValue skuPropValue : sizeSkuValueList){
								String displayName = skuPropValue.getName();
								String propertyName = skuPropValue.getSkuPropId()+":"+skuPropValue.getId()+";";
								
								boolean sizeSelected = currentProductSku!=null&&skuPropValue.getId().equals(currentProductSku.getSkuSizeValueId());
							%>
	            			<div class="item <%=sizeSelected?"selected":""%>" data="<%=propertyName%>"><b></b><a href="#none" title="<%=displayName%>" style="cursor: pointer;"><%=displayName%></a></div>
	            			<%}%>
	            		</div>
	            	</li>
	            	<%}%>
	            	<li>购买数量: <span id="buyAmount" class="text-highlight highlight-blue">1</span>件</li> 
            	</ul>
            	<input type="hidden" id="productSkuId" name="productSkuId" value="${currentProductSku.id}"/> 
            	
            	<%
            	boolean buyNow = false;
            	if(currentProductSku!=null && currentProductSku.getPrice()!=null&&currentProductSku.getPrice()>0&&currentProductSku.getAmount()!=null&&currentProductSku.getAmount()>0){
            		buyNow = true;
            	}
            	%>
                <a href="javascript:void(0)" id="buyNow" class="button-big button-green <%=buyNow?"":"gone"%>">点击购买</a>
                <div class="static-notification-red <%=buyNow?"gone":""%>" id="buyDisable">
                    <p class="center-text uppercase">本品暂时缺货</p>
                </div>
                
                
                <script>
					var skuMap = new Map();
					<%
					Map<String, String> productSkuMap = (Map<String, String>)request.getAttribute("productSkuMap");
					if(productSkuMap!=null&&productSkuMap.size()>0){
						for(Entry<String, String> productSkuEntry: productSkuMap.entrySet()){
							String key = productSkuEntry.getKey();
							String value = productSkuEntry.getValue();
					%>
					var skuPropValueJson = <%=value%>;
					skuMap.put('<%=key%>', skuPropValueJson);
					<%}
					}%>
					//选定时检查二维数组的数据
					//var skuObj = skuMap.get("selectedKey");//根据选定的key查询
					
					$("#choose-color .item").click(function(){
						$("#choose-color .item").removeClass("selected");
						$(this).addClass("selected");
						
						var colorProperty = $(this).attr("data");
						var sizeProperty = $("#choose-version .selected").attr("data");
						if(colorProperty!=null && sizeProperty!=null){
							var propertiesName = colorProperty + sizeProperty;
							var productSkuJson = skuMap.get(propertiesName);
							reloadProductInfo(productSkuJson);
						}
					});
					
					$("#choose-version .item").click(function(){
						$("#choose-version .item").removeClass("selected");
						$(this).addClass("selected");
						
						var colorProperty = $("#choose-color .selected").attr("data");
						var sizeProperty = $(this).attr("data");
						if(colorProperty!=null && sizeProperty!=null){
							var propertiesName = colorProperty + sizeProperty;
							var productSkuJson = skuMap.get(propertiesName);
							reloadProductInfo(productSkuJson);
						}
					});
					
					//点击购买操作
					$("#buyNow").click(function(){
						var buyAmount = $("#buyAmount").text();
						var productSkuId = $("#productSkuId").val();
						location.href= "${pageContext.request.contextPath}/buyNow?code=1&amount="+buyAmount+"&productSkuId="+productSkuId;						
					});
					
					function reloadProductInfo(productSkuJson){
						if(productSkuJson!=null){
							$("#productSkuId").val(productSkuJson.id);//刷新库存
							$("#originPrice").html("<del>"+productSkuJson.originPrice+"</del>");//刷新价格
							$("#price").text(productSkuJson.price);//刷新价格
							$("#leftAmount").text(productSkuJson.amount);//刷新库存
							if(productSkuJson.amount>0&&productSkuJson.price>0){//刷新购买&购物车按钮
								$("#buyNow").removeClass("gone");
								$("#buyDisable").addClass("gone");
							}else{
								$("#buyDisable").removeClass("gone");
								$("#buyNow").addClass("gone");
							}
						}
					}
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
            
            <div class="container">
            	<div class="section-title">
                	<h4>推荐商品</h4>
                </div>
                <a href="index.html#" class="next-quote"></a>
                <a href="index.html#" class="prev-quote"></a>
                <div class="quote-slider" data-snap-ignore="true">
                    <div>
                        <div class="services-item">
                            <img src="images/general-nature/3s.jpg" alt="img">
                            原价:&nbsp;<span id="originPrice" class="text-highlight highlight-dark"><del>298.00</del></span>元
                        </div>
                    </div>
                    <div>
                        <div class="services-item">
                            <img src="images/general-nature/4s.jpg" alt="img">
                            原价:&nbsp;<span id="originPrice" class="text-highlight highlight-dark"><del>298.00</del></span>元
                        </div>
                    </div>
                    <div>
                        <div class="services-item">
                            <img src="images/general-nature/5s.jpg" alt="img">
                            原价:&nbsp;<span id="originPrice" class="text-highlight highlight-dark"><del>298.00</del></span>元
                        </div>
                    </div>
                    <div>
                        <div class="services-item">
                            <img src="images/general-nature/6s.jpg" alt="img">
                            原价:&nbsp;<span id="originPrice" class="text-highlight highlight-dark"><del>298.00</del></span>元
                        </div>
                    </div>
                </div>
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
var imgUrl = "http://images.1758.com/article/image/2014/08/27/25831409142359848.jpg";
var pageUrl = "网址";
var descContent = '爱在五月，\n\n妈咪爱1+1亲子健康之旅开启全国行首站----重庆站妈咪爱活性益生菌';
var shareTitle = '标题';
var imageHeight = '120';//图片高度
var imageWidth = '120';//图片宽度


function shareTimeline() {
    WeixinJSBridge.invoke('shareTimeline',{
		"img_height": imageHeight,
        "img_width": imageWidth,
        "img_url": imgUrl,
        "link": pageUrl,
        "desc": descContent,
        "title": shareTitle
    }, function(res) {//朋友圈分享成功后的回调
		//ajax方式增加礼券
		
    });
}

function shareFriend() {
    WeixinJSBridge.invoke('sendAppMessage',{
    	"img_height": imageHeight,
        "img_width": imageWidth,
    	"img_url": imgUrl,
        "link": pageUrl,
        "desc": descContent,
        "title": shareTitle
    }, function(res) {//分享给朋友成功后的回调
        
    });
}
</script>

</html>