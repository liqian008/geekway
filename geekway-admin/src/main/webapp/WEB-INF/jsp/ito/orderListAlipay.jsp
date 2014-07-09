<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.geekway.model.ItoProductOrder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.utils.*"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ITO管理平台</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/londinium-theme.min.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath}/css/styles.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/icons.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/1.10.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/charts/sparkline.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/uniform.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/select2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/inputmask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/autosize.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/inputlimit.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/listbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/multiselect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/tags.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/switch.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/forms/uploader/plupload.full.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/forms/uploader/plupload.queue.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/daterangepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/fancybox.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/prettify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/moment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/jgrowl.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/datatables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/colorpicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/fullcalendar.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/timepicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/collapsible.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/application.js"></script>
</head>
<body class="sidebar-wide">

	<jsp:include page="../inc/header.jsp"></jsp:include>

	<!-- Page container -->
	<div class="page-container">

		<jsp:include page="../inc/leftSidebar.jsp"></jsp:include>

		<!-- Page content -->
		<div class="page-content">
			<!-- Page header -->
			<div class="page-header">
				<div class="page-title">
					<h3>
						订单管理
						<!-- 
						<small>Headings, lists, code, pre etc. </small>
						 -->
					</h3>
				</div>
			</div>
			<!-- /page header -->
			<!-- Breadcrumbs line -->
			<div class="breadcrumb-line">
				<ul class="breadcrumb">
					<li><a href="javascript:void(0)">首页</a></li>
					<li class="active">订单管理</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->

			<div class="callout callout-info fade in">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h5>功能介绍：</h5>
				<p>
					1、此页数据为支付宝订单列表<br/>
					1、支付宝的发货管理务必要在<a href="http://www.alipay.com">支付宝网站</a>进行操作，否则无法完成支付宝的流程<br/>
				</p>
			</div>


			<div class="tabbable page-tabs">
				<ul class="nav nav-tabs">
					<li>
						<a href="./orderListSelf">
							<i class="icon-hammer"></i>APP支付订单管理
						</a>
					</li>
					<li class="active">
						<a href="javascript:void(0)">
							<i class="icon-table2"></i>支付宝订单管理
						</a>
					</li>
				</ul>


				<!-- Table view -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h5 class="panel-title">
							<i class="icon-people"></i>订单管理
						</h5>
					</div>
					<div class="datatable-media">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th class="text-center">序号</th>
									<th>商品</th>
	                                <th>SKU</th>
	                                <th>价格</th>
	                                <th>姓名</th>
	                                <th>地址</th>
	                                <th>类型</th>
	                                <th>状态</th>
	                                <th class="team-links">操作</th>
								</tr>
							</thead>
							<tbody>
								<%
	                           	List<ItoProductOrder> orderList = (List<ItoProductOrder>)request.getAttribute("orderList");
	                           	if(orderList!=null&&orderList.size()>0){
	                           		int i=0;
	                           		for(ItoProductOrder order: orderList){
	                           			i++;
	                           	%>
								<tr>
			                        <td><%=i%></td>
			                        <td title="SN：<%=order.getOrderSn()%>"><%=order.getTitle()%></td>
			                        <td><%=order.getSkuName()%></td>
			                        <td><%=order.getTotalPrice()%>元</td>
			                        <td><%=order.getPostAddress()%></td>
			                        <td>支付宝</td>
			                        <td>已下单</td>
			                        <td class='text-center'>
			                        	<div class="table-controls">
			                        	
											<a href="./orderDisplay?orderSn=<%=order.getOrderSn()%>"
												class="btn btn-link btn-icon btn-xs tip" title=""
												data-original-title="查 看"><i class="icon-envelop"></i></a> 
										</div>
									</td>
	                               </tr>
								<%}
	                           	} %>
							</tbody>
						</table>
					</div>
				</div>
				<!-- /table view -->
			</div>

			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
</body>
</html>

