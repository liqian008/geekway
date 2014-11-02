<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>

<%@ include file="../inc/include_tag.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>后台管理平台</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/uploader/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/uploader/plupload.queue.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/daterangepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/fancybox.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/prettify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/moment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/jgrowl.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/datatables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/colorpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/fullcalendar.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/timepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/collapsible.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/application.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
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
						订单详情
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
					<li class="active">订单详情</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->
			
			<!-- <div class="callout callout-info fade in">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h5>功能介绍</h5>
				<p>
					1、订单详情<br/>
				</p>
			</div> -->


			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>订单商品
					</h5>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-check">
						<thead>
							<tr>
								<th>ID</th>
                                <th>商品</th>
                                <th>名称</th>
                                <th>单价(元)</th>
                                <th>数量(个)</th>
                                <th>合计(元)</th>
							</tr>
						</thead>
						<tbody>
							<%
                           	List<WxProductOrderItem> productOrderItemList = (List<WxProductOrderItem>)request.getAttribute("productOrderItemList");
                           	if(productOrderItemList!=null&&productOrderItemList.size()>0){
                           		int i=0;
                           		for(WxProductOrderItem productOrderItem: productOrderItemList){
                           			i++;
                           	%>
							<tr>
		                        <td><%=i%></td>
		                        <td><%=productOrderItem.getProductName()%></td>
		                        <td><%=productOrderItem.getProductName()%></td>
		                        <td><%=productOrderItem.getItemFee()%></td>
		                        <td><%=productOrderItem.getAmount()%></td>
		                        <td><%=productOrderItem.getTotalFee()%></td>
                             </tr>
                             <%}
                             }%>
						</tbody>
					</table>
				</div>
			</div>
			<!-- /table view -->

			<form id="validate" action="<s:url value='#'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>订单详情
						</h6>
					</div>
					<div class="panel-body">
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">买家: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">${order.userOpenId}</label>
								&nbsp;<a href="${pageContext.request.contextPath}/" class="btn btn-primary">查看资料</a>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">系统订单号: <span class="mandatory">*</span></label>
							<div class="col-sm-6">
								<label class="control-label">${order.outTradeNo}</label>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">订单金额: <span class="mandatory">*</span>
							</label>
							
							
							<div class="col-sm-2">
								<label class="control-label">${order.totalFee}</label>
								<span class="label label-success label-block">总金额(元)</span>
							</div>
							
							<div class="col-sm-2">
								<label class="control-label">${order.productFee}</label>
								<span class="label label-danger label-block">商品(元)</span>
							</div>
							
							<div class="col-sm-2">
								<label class="control-label">${order.transportFee}</label>
								<span class="label label-info label-block">运费(元)</span>
							</div>
							
							
							<div class="col-sm-2">
								<label class="control-label">${order.discountFee}</label>
								<span class="label label-success label-block">折扣(元)</span>
							</div>
							
							<div class="col-sm-2">
								<label class="control-label">${order.voucherFee}</label>
								<span class="label label-primary label-block">优惠券(元)</span>
							</div>
							
							
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">发货信息: <span class="mandatory">*</span>
							</label>
							
							<div class="col-sm-3">
								<label class="control-label">${order.postName}</label>
								<span class="label label-success label-block">收件人</span>
							</div>
							
							<div class="col-sm-3">
								<label class="control-label">${order.postMobile}</label>
								<span class="label label-danger label-block">手机号</span>
							</div>
							
							<div class="col-sm-2">
								<label class="control-label">${order.postCode}</label>
								<span class="label label-primary label-block">邮编</span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">邮寄地址: <span class="mandatory">*</span></label>
							<div class="col-sm-3">
								<label class="control-label">xxxxxxx</label>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">发货信息: <span class="mandatory">*</span>
							</label>
							
							<div class="col-sm-3">
								<input type="text" class="form-control" name="postType" id="postType" value="${order.postType}">
								<span class="label label-success label-block">快递类型</span>
							</div>
							
							<div class="col-sm-3">
								<input type="text" class="form-control" name="postSn" id="postSn" value="${order.postSn}">
								<span class="label label-danger label-block">快递单号</span>
							</div>
							
						</div>
						
						
						<div class="form-actions text-right">
							<a href="${pageContext.request.contextPath}/order/deliveryInfo?outTradeNo=${notify.outTradeNo}" class="btn btn-info">确认发货</a>
						</div>
					</div>
				</div>
			</form>
			
			
		
			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
	
	<script type="text/javascript">
	$(document).ready(function(){
	   
	});
	</script>
	
</body>
</html>
