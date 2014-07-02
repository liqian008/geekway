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
		return " <a href='http://m.kuaidi100.com/index_all.html?type="+expressCompany+"&postid="+expressNo+"' class='klh-button-white radius'>查看物流</a>";
	}
	return "";
}
%>


<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

<title>可乐惠</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/klh/css/klh.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/klh/js/jquery.js"></script>

<style>
.header{
 
height:40px;
padding-bottom:0px;
}

.nav{
background-color:#c6c0b4;
margin:0px;
height:40px;
}
 
.nav ul{
color:#93242a;
margin:0px;
padding:0px;
list-style:none;
}

.nav ul li{
float:left;
width:50%;
text-align:center;
line-height:40px;

}

.nav ul li div{
 
border-right:1px solid #fff;
}

.order_item{
background-color:#c6c0b4;
font-size: 14px;
}

.order_item div{
padding: 4px 10px;
color: #fff;
text-align:left
}

.order_item .product{
font-size:10px;
}

.order_item .trace{
float:right;
}


.order_item div.sperator{
padding: 0px;
margin: 6px 10px;
height:1px;
background-color:#fff;
}

</style>
<body>
	<div class="header">
		<div class="header_menu">
			<a class="back" href="javascript:history.back();"></a>
			<span>订单查询</span>
			<a class="home" href="./home.htm"></a>
		</div>	
		
	</div>
 
	
	<div class="nav">
		<%
		String userMobile = (String)request.getAttribute("userMobile");
		%>
		<ul>
			<li><div><a href="./edbOrderList?periodType=0&userMobile=<%=userMobile%>">近一周订单</a></div></li>
			<li><a href="./edbOrderList?periodType=1&userMobile=<%=userMobile%>">近两周订单</a></li>
 		</ul>
	</div>	
	

	<div class="content">
		<div class="main">
			<div class="order">
				
				<%
				List<KlhEdbOrder> edbOrderList = (List<KlhEdbOrder>)request.getAttribute("edbOrderList");
				if(edbOrderList!=null&&edbOrderList.size()>0){
				%>	
				<ul>
				
					<%for(KlhEdbOrder edbOrder: edbOrderList){ %>
					<li>
						<div class="order_item radius">
							<div class="trace">
							<%=getExpressUrl(edbOrder.getExpress(), edbOrder.getExpressCoding(), edbOrder.getExpressNo())%>
							<!-- <a href="#" class="klh-button-white radius">订单跟踪</a> -->
							</div>	
							<div>订单号: <%=edbOrder.getTransactionId()%></div>
							<div>订单金额:<%=edbOrder.getProTotalfee()%></div>
							<div>下单时间:<%=edbOrder.getTidTime()%></div>
							<div class="sperator"></div>
							<%
							List<KlhEdbOrderItem> orderItemList = edbOrder.getEdbOrderItemList();
							if(orderItemList!=null&&orderItemList.size()>0){
							%>
							<div class="product">
								<%for(KlhEdbOrderItem orderItem: orderItemList){%>
								<%
								String price = orderItem.getSellPrice();
								if(price.endsWith("00")){
									price = price.substring(0, price.length()-2);
								}
								%>
								<%=orderItem.getProName()%>, 价格: <%=price%>元<p/>
								<%}%>
							</div>
							<%}%>
							<div class="sperator"></div>
							<div>收件人:<%=edbOrder.getReceiverName()%></div>
							<div>订单状态:<%=edbOrder.getType()%></div>
							<div>发货状态：<%=edbOrder.getDeliveryStatus()%></div>
							
						</div>
					</li>
					<%} %>
				</ul>
				<%} %>
			</div>
		</div>
	</div>
</body>

<script>

</script>

</html>


