<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bruce.geekway.model.*"%>
<%@ page import="com.bruce.geekway.model.klh.*"%>


<%!public String getExpressUrl(String expressName, String expressCoding,
			String expressNo) {

		String deliverInfo = "快递类型: " + expressName;
		String expressCompany = null;
		if (expressCoding != null) {
			if ("SF".equalsIgnoreCase(expressCoding)) {
				expressCompany = "shunfeng";
			} else if ("YUNDA".equalsIgnoreCase(expressCoding)) {
				expressCompany = "yunda";
			} else if ("STO".equalsIgnoreCase(expressCoding)) {
				expressCompany = "shentong";
			} else if ("快捷".equalsIgnoreCase(expressCoding)) {
				expressCompany = "kuaijiesudi";
			} else if ("YTO".equalsIgnoreCase(expressCoding)) {
				expressCompany = "yuantong";
			} else if ("ZJS".equalsIgnoreCase(expressCoding)) {
				expressCompany = "zhaijisong";
			}
		}
		if (expressCompany != null) {
			return " <a href='http://m.kuaidi100.com/index_all.html?type="
					+ expressCompany + "&postid=" + expressNo
					+ "' class='klh-button-white radius'>查看物流</a>";
		}
		return "";
	}%>


<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport"
	content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

<title>可乐惠</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/klh/css/klh.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/klh/js/jquery.js"></script>
	
<script src="<%=request.getContextPath()%>/klh/dev/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/klh/dev/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>

<link href="<%=request.getContextPath()%>/klh/dev/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/klh/dev/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/klh/dev/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/klh/dev/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>

<!-- S 可根据自己喜好引入样式风格文件 -->
<script src="<%=request.getContextPath()%>/klh/dev/js/mobiscroll.android-ics-2.5.2.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/klh/dev/css/mobiscroll.android-ics-2.5.2.css" rel="stylesheet" type="text/css" />	

<style>
.header {
	height: 40px;
	padding-bottom: 0px;
}

.nav {
	background-color: #c6c0b4;
	margin: 0px;
	height: 40px;
}

.nav ul {
	color: #93242a;
	margin: 0px;
	padding: 0px;
	list-style: none;
}

.nav ul li {
	float: left;
	width: 50%;
	text-align: center;
	line-height: 40px;
}

.nav ul li.active {
	border-bottom: 2px solid;
}

.nav ul li a {
	color: #93242a;
}

.nav ul li div {
	border-right: 1px solid #fff;
}

.order_item {
	background-color: #c6c0b4;
	font-size: 14px;
}

.order_item div {
	padding: 4px 10px;
	color: #fff;
	text-align: left
}

.order_item .product {
	font-size: 10px;
}

.order_item .trace {
	float: right;
}

.order_item div.sperator {
	padding: 0px;
	margin: 6px 10px;
	height: 1px;
	background-color: #fff;
}

.order_item .product td,.order_item .product th {
	padding: 5px;
}
</style>
<body>
	<div class="header">
		<div class="header_menu">
			<a class="back" href="javascript:history.back();"></a> <span>我的订单</span>
			<a class="home" href="./home.htm"></a>
		</div>

	</div>

	<!-- 
	<div class="nav">
		<%String userMobile = (String)request.getAttribute("userMobile");
		
		Integer periodType = (Integer)request.getAttribute("periodType");%>
		<ul>
			<li <%=periodType==null||periodType==0?"class='active'":""%>><div><a href="./edbOrderList?periodType=0&userMobile=<%=userMobile%>">近一周订单</a></div></li>
			<li <%=periodType!=null&&periodType==1?"class='active'":""%>><a href="./edbOrderList?periodType=1&userMobile=<%=userMobile%>">近两周订单</a></li>
 		</ul>
	</div>	
	 -->

	<div class="content">
		<div class="main">

			<div class="demos">
			<form action="./edbOrderList" method="get" id="orderForm">
				<label for="queryStartDateLabel">查询日期起：</label>
				<input type="text" name="queryStartDateStr" id="queryStartDateStr" value="${queryStartDateStr}"/>
				<input type="hidden" name="userMobile" value="<%=userMobile%>" />
				<a href="javascript:void(0)" class="klh-button radius" id="submitBtn">查询</a>
			</form>
			</div>

			<div class="order">
				<%
					List<KlhEdbOrder> edbOrderList = (List<KlhEdbOrder>) request.getAttribute("edbOrderList");
					if (edbOrderList != null && edbOrderList.size() > 0) {
				%>
				<ul>

					<%
						for(KlhEdbOrder edbOrder: edbOrderList){
					%>
					<li>
						<div class="order_item radius">
							<div class="trace">
								<%=getExpressUrl(edbOrder.getExpress(), edbOrder.getExpressCoding(), edbOrder.getExpressNo())%>
								<!-- <a href="#" class="klh-button-white radius">订单跟踪</a> -->
							</div>
							<div>
								订单号：<%=edbOrder.getTransactionId()%></div>
							<div>
								订单金额：<%=edbOrder.getProTotalfee()%>元
							</div>
							<div>
								下单时间：<%=edbOrder.getTidTime()%></div>
							<!-- <div class="sperator"></div> -->
							<%
								List<KlhEdbOrderItem> orderItemList = edbOrder.getEdbOrderItemList();
												if(orderItemList!=null&&orderItemList.size()>0){
							%>
							<div class="product">
								<table border="1px" width="100%" cellspacing="0" cellpadding="0"
									style="border: 1px solid; border-bottom: 0px; border-right: 0px">
									<tr>
										<th align="center" width="68%">产品名称</th>
										<th align="center" width="12%">数量</th>
										<th align="center" width="20%">价格</th>
									</tr>
									<%
										for(KlhEdbOrderItem orderItem: orderItemList){
									%>
									<tr>
										<td align="left"><%=orderItem.getProName()%></td>
										<td align="center"><%=orderItem.getProNum()%></td>
										<%
											String price = orderItem.getSellPrice();
											if(price.endsWith("00")){
												price = price.substring(0, price.length()-2);
											}
										%>
										<td align="center"><%=price%>元</td>
									</tr>
									<%
										}
									%>
								</table>

							</div>
							<%
								}
							%>
							<!-- <div class="sperator"></div> -->
							<div>
								收件人：<%=edbOrder.getReceiverName()%></div>
							<div>
								订单状态：<%=edbOrder.getType()%></div>
							<div>
								发货状态：<%=edbOrder.getDeliveryStatus()%></div>

						</div>
					</li>
					<%
						}
					%>
				</ul>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>

<script>

$("#submitBtn").click(function (){
	$("#orderForm").submit();
});
</script>


<script type="text/javascript">
$(function () {
var currYear = (new Date()).getFullYear();
var opt={};
opt.date = {preset : 'date'};
opt.default = {
	theme: 'android-ics light', //皮肤样式
       display: 'modal', //显示方式 
       mode: 'scroller', //日期选择模式
	lang:'zh',
       startYear:currYear - 1, //开始年份
       endYear:currYear //结束年份
};

$("#queryStartDateStr").scroller($.extend(opt['date'], opt['default']));
//$("#queryStartDateStr").val('${queryStartDateStr}')
});
</script>

</html>


