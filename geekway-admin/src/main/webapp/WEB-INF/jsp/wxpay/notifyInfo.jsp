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
						微信支付通知详情
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
					<li class="active">微信支付通知详情</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->
			
			<div class="callout callout-info fade in">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h5>功能介绍</h5>
				<p>
					1、发货时间限制:虚拟、服务类24小时内,实物类72小时内。请在收到支付通知后按时发货。
					2、若平台在规定时间内没有收到,将视作发货超时处理。<br/>
				</p>
			</div>


			<form id="validate" action="<s:url value='#'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>查看微信支付通知
						</h6>
					</div>
					<div class="panel-body">
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">支付ID: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">${notify.notifyId}</label>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">买家信息: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-3">
								<label class="control-label">${notify.openId}</label>
								<a href="${pageContext.request.contextPath}" target="_blank"> 
									<span class="label label-success">查看资料</span> 
								</a>
								<span class="label label-success label-block">用户ID</span>
							</div>
							
							<div class="col-sm-2">
								<label class="control-label">${notify.isSubsuribed}</label>
								<span class="label label-primary label-block">关注状态</span>
							</div>
						</div>
						
						
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">交易信息: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<label class="control-label">${notify.transactionId}</label>
								<span class="label label-success label-block">微信交易ID</span>
							</div>
							
							<div class="col-sm-4">
								<label class="control-label">${notify.outTradeNo}</label>
								<span class="label label-primary label-block">订单号</span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">支付时间: <span class="mandatory">*</span></label>
							<div class="col-sm-6">
								<label class="control-label">${notify.timeEnd}</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">交易金额: <span class="mandatory">*</span>
							</label>
							
							
							<div class="col-sm-2">
								<label class="control-label">${notify.totalFee}</label>
								<span class="label label-success label-block">总金额(元)</span>
							</div>
							
							<div class="col-sm-2">
								<label class="control-label">${notify.productFee}</label>
								<span class="label label-danger label-block">商品(元)</span>
							</div>
							
							<div class="col-sm-2">
								<label class="control-label">${notify.transportFee}</label>
								<span class="label label-info label-block">运费(元)</span>
							</div>
							
							
							<div class="col-sm-2">
								<label class="control-label">${notify.discount}</label>
								<span class="label label-success label-block">折扣(元)</span>
							</div>
							
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">说明: <span class="mandatory">*</span></label>
							<div class="col-sm-6">
								<label class="control-label">${notify.attach}</label>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">支付机构信息: <span class="mandatory">*</span>
							</label>
							
							<div class="col-sm-2">
								<label class="control-label">微信</label>
								<span class="label label-success label-block">支付机构类型</span>
							</div>
							
							<div class="col-sm-2">
								<label class="control-label">人民币</label>
								<span class="label label-danger label-block">feeType</span>
							</div>
							
							<div class="col-sm-5">
								<label class="control-label">${notify.bankBillno}</label>
								<span class="label label-primary label-block">银行流水号</span>
							</div>
							
						</div>
						
						<div class="form-actions text-right">
							<a href="${pageContext.request.contextPath}/order/orderInfo?outTradeNo=${notify.outTradeNo}" class="btn btn-info">查看订单</a> 
						<!-- 
							<input type="submit" value="提 交" class="btn btn-primary">
						 -->
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
