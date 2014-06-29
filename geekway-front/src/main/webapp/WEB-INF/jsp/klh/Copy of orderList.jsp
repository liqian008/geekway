<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.klh.*" %>


<%!
public String getExpressUrl(String expressName, String expressCoding, String expressNo){
	
	String deliverInfo = "快递类型: "+expressName;
	String expressCompany = null;
	if(expressCoding!=null){
		if("SF".equalsIgnoreCase(expressCoding)){
			expressCompany = "shunfeng";
		}else if("YUNDA".equalsIgnoreCase(expressCoding)){
			expressCompany = "yunda";
		}else if("STO".equalsIgnoreCase(expressCoding)){
			expressCompany = "shentong";
		}else if("快捷".equalsIgnoreCase(expressCoding)){
			expressCompany = "kuaijiesudi";
		}else if("YTO".equalsIgnoreCase(expressCoding)){
			expressCompany = "yuantong";
		}else if("ZJS".equalsIgnoreCase(expressCoding)){
			expressCompany = "zhaijisong";
		}
	}
	if(expressCompany!=null){
		return deliverInfo + " <a href='http://m.kuaidi100.com/index_all.html?type="+expressCompany+"&postid="+expressNo+"' id='submitBtn' class='o-buttons blue'>查看物流</a>";
	}else{
		return deliverInfo;
	}
}
%>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>可乐惠</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
		<!-- 
		<script type="text/javascript" src="js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="js/jquery.easy-pie-chart.js"></script>
		<script type="text/javascript" src="js/o-script.js"></script>
		 -->

	</head>
	<body class="o-page">
		<div id="page">
			<!-- 
			<div id="header">
				<a href="#menu"></a>
				<span id="Logo" class="svg">
					<img src="img/logo.svg" />
				</span>
				<a class="backBtn" href="javascript:history.back();"></a>
			</div>
			-->
			<div id="header">
				ITO订单列表
				<a class="backBtn" href="javascript:history.back();"></a>
				
			</div>
			<div id="content">
				
				<%
				List<KlhEdbOrder> edbOrderList = (List<KlhEdbOrder>)request.getAttribute("edbOrderList");
				if(edbOrderList!=null&&edbOrderList.size()>0){
					for(KlhEdbOrder edbOrder: edbOrderList){
				%>	
					<article>
						<h4>【<%=edbOrder.getPlatType()%> - <%=edbOrder.getOrderChannel() %> - <%=edbOrder.getShopName() %>】</h4>
						<h4>【购物清单】</h4>
						<%
						List<KlhEdbOrderItem> orderItemList = edbOrder.getEdbOrderItemList();
						if(orderItemList!=null&&orderItemList.size()>0){
							for(KlhEdbOrderItem orderItem: orderItemList){
						%>
						<%=orderItem.getProName()%>, 价格: <%=orderItem.getSellPrice()%>元<br/>
						<%}%>
						<%}%>
						
						<h4>【订单信息】</h4>
						
						<p>
						收件人：<%=edbOrder.getReceiverName()%><br/>
						订单状态：<%=edbOrder.getType()%><br/>
						发货状态：<%=edbOrder.getDeliveryStatus()%><br/>
						</p>
						<%=getExpressUrl(edbOrder.getExpress(), edbOrder.getExpressCoding(), edbOrder.getExpressNo())%>
						
						<!-- <div class="a-meta">
							<a href="./blog"><i class="i-comments i-small"></i>6 评论</a> 
							<a href="about.html"><i class="i-author i-small"></i>John Doe</a> 
							<a href="./blog"><i class="i-calendar i-small"></i>31 Nov 2013</a>
						</div> -->
					</article> 
				<%}
				}%>

			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
</html>