<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
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


</head>
<body>

<div class="all-elements">
    <jsp:include page="../inc/sidebar.jsp"></jsp:include>

    <div id="content" class="page-content">
    	<div class="page-header">
        	<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">${product.name}</p>
            <a href="contact.html" class="deploy-contact"></a>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
            <a href="http://www.facebook.com/enabled.labs" class="facebook-content"></a>
            <a href="https://twitter.com/iEnabled" class="twitter-content"></a>
        </div>
        
        <div class="content"> 
        	
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
            		<li><span class="text-highlight highlight-dark">原 价</span><del>20.00元</del></li>
	            	<li><span class="text-highlight highlight-red">现 价</span>10.00元</li>
	            	
	            	<%
	            	Map<Integer, List<WxSkuPropValue>> skuGroupMap = (Map<Integer, List<WxSkuPropValue>>)request.getAttribute("skuGroupMap");
	            	if(skuGroupMap!=null&&skuGroupMap.get(1)!=null){
	            	%>
	            	<li id="choose-color" class="choose-color-shouji">
	            		<div class="dt">选择颜色：</div>
	            		<div class="dd">
							<%
							List<WxSkuPropValue> colorSkuValueList = skuGroupMap.get(1);
							for(WxSkuPropValue skuPropValue : colorSkuValueList){
								String displayName = skuPropValue.getName();
							%>	            		
	            			<div class="item">
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
							%>	  
	            			<div class="item"><b></b><a href="#none" title="<%=displayName%>" style="cursor: pointer;"><%=displayName%></a></div>
	            			<%}%>
	            			<!-- 
	            			<div class="item"><b></b><a href="#none" title="38" style="cursor: pointer;">38</a></div>
	            			<div class="item"><b></b><a href="#none" title="38.5" style="cursor: pointer;">38.5</a></div>
	            			<div class="item  selected"><b></b><a href="#none" title="39.5" style="cursor: pointer;">39.5</a></div>
	            			<div class="item"><b></b><a href="#none" title="40" style="cursor: pointer;">40</a></div>
	            			<div class="item disabled"><b style="display: none;"></b><i></i><a href="#none" title="所选颜色该尺码商品在该地区无货" style="cursor: not-allowed;">42</a></div>
	            			<div class="item disabled"><b style="display: none;"></b><i></i><a href="#none" title="所选颜色该尺码商品在该地区无货" style="cursor: not-allowed;">42.5</a></div>
	            			<div class="item disabled"><b style="display: none;"></b><i></i><a href="#none" title="所选颜色该尺码商品在该地区无货" style="cursor: not-allowed;">43</a></div>
	            			 -->
	            		</div>
	            	</li>
	            	<%}%>
	            	
					<script>
						var skuMap = new Map();
						<%
						Map<String, String> productSkuMap = (Map<String, String>)request.getAttribute("productSkuMap");
						if(productSkuMap!=null&&productSkuMap.size()>0){
							for(Entry<String, String> productSkuEntry: productSkuMap.entrySet()){
								String key = productSkuEntry.getKey();
								String value = productSkuEntry.getValue();
						%>
						var skuJson = <%=value%>;
						skuMap.put('<%=key%>', value);
						<%}
						}%>
						//选定时检查二维数组的数据
						//var skuObj = skuMap.get("selectedKey");//根据选定的key查询
						//刷新价格
						//刷新库存
						//刷新购买按钮
					</script>
	            	
	            	<li><span class="text-highlight highlight-blue">购买数量</span>1件</li>
	            	
            	</ul>
                <a href="javascript:void(0)" class="button-big button-red">点击购买</a> 
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
                        <img src="images/general-nature/1s.jpg" class="float-left" width="70" alt="img">
                        Sed sed gravida turpis. Suspendisse quis lacus non lacus fermentum lobortis non et orci. Nullam bibendum non ligula ut viverra.
                    </p>
                </div>
                <div class="tab-content tab-content-2">
                    <p>
                        <img src="images/general-nature/2s.jpg" class="float-left" width="70" alt="img">
                        Sed sed gravida turpis. Suspendisse quis lacus non lacus fermentum lobortis non et orci. Nullam bibendum non ligula ut viverra.                    
                    </p>
                </div>
            </div>
			
			                
            <div class="decoration"></div>
            
            <div class="container no-bottom">
            	<div class="section-title">
                	<h4>推荐商品</h4>
                </div>
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
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>                
    </div>  
</div>

</body>
</html>