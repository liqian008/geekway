<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>

<%@ include file="../inc/include_tag.jsp" %>


<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>


<%!String displayPayType(Short payType){
	if(payType!=null&&1==payType){
		return "支付宝支付";
	}
	return "APP支付";
} %>


<%!String displayPayStatus(Short payStatus){
	if(payStatus!=null&&10==payStatus){
		return "支付宝已下单";
	}else if(payStatus!=null&&11==payStatus){
		return "支付宝已支付";
	}else if(payStatus!=null&&0==payStatus){
		return "APP已下单";
	}
	return "支付状态错误";
} %>

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
			
			<div class="callout callout-info fade in">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h5>功能介绍</h5>
				<p>
					1、订单详情页<br/>
				</p>
			</div>

			<%
			ItoProductOrder productOrder = (ItoProductOrder)request.getAttribute("productOrder");
			%>

			<form id="validate" action="<s:url value='#'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>查看订单信息
						</h6>
					</div>
					<div class="panel-body">
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">订单号: <span class="mandatory">*</span></label>
							<div class="col-sm-2">
								<label class="control-label">
									${productOrder.orderSn}
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品ID: <span class="mandatory">*</span></label>
							<div class="col-sm-2">
								<label class="control-label">
									${productOrder.productId}
								</label>
								<input type="hidden" name="productId" id="productId" value="${productOrder.productId}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品名称: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">
									${productOrder.title}
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品SN: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">
									${productOrder.outId}
								</label>
							</div>
						</div>
						
						<%-- <div class="form-group">
							<label class="col-sm-2 control-label text-right">商品描述: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
								<label class="control-label">
									${productOrder.description}
								</label>
							</div>
						</div> --%>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">价格信息: <span class="mandatory">*</span>
							</label>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="totalPrice" id="totalPrice" value="${productOrder.totalPrice}" readonly="readonly"/>
								<%-- <label class="control-label">
									${productOrder.totalPrice}
								</label> --%>
								<span class="label label-danger label-block">总价(元)</span>
							</div>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="price" id="price" value="${productOrder.price}" readonly="readonly"/>
								<%-- <label class="control-label">
									${productOrder.price}
								</label> --%>
								 <span class="label label-info label-block">单价(元)</span>
							</div>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="num" id="num" value="${productOrder.num}" readonly="readonly"/>
								<%-- <label class="control-label">
									${productOrder.num}
								</label> --%>
								<span class="label label-primary label-block">数量(个)</span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品SKU信息: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<label class="control-label">
								${productOrder.skuName}
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">支付类型: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<label class="control-label">
									<%=displayPayType(productOrder.getPayType()) %>
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">支付状态: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<label class="control-label">
									<%=displayPayStatus(productOrder.getPayStatus()) %>
								</label>
							</div>
						</div>
						
						<%
						//if(productOrder.getPayType()!=null&&productOrder.getPayType()==0){
						%>	
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">收件人: <span class="mandatory">*</span></label>
							<div class="col-sm-2">
								<label class="control-label">
									${productOrder.postName}
								</label>
							</div>
							<label class="col-sm-2 control-label text-right">收件人电话: <span class="mandatory">*</span></label>
							<div class="col-sm-3">
								<label class="control-label">
									${productOrder.postMobile}
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">收件人邮编: <span class="mandatory">*</span></label>
							<div class="col-sm-2">
								<label class="control-label">
									${productOrder.postCode}
								</label>
							</div>
							<label class="col-sm-2 control-label text-right">收件人Email: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">
									${productOrder.postEmail}
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">收件人地址: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<label class="control-label">
									${productOrder.postAddress}
								</label>
							</div>
						</div>
						<%//}%>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">下单时间: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">
									<%=sdf.format(productOrder.getCreateTime())%>
								</label>
							</div>
						</div>
						
						<%-- <div class="form-group">
							<label class="col-sm-2 control-label text-right">运单号: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="postSn" id="postSn" value="${productOrder.postSn}" readonly="readonly"/>
							</div>
						</div> --%>
						
						
						<!-- <div class="form-actions text-right">
							<input type="reset" value="重 置" class="btn btn-danger">
							<input type="submit" value="提 交" class="btn btn-primary">
						</div> -->
					</div>
					
					
					
				</div>
				
			</form>

			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
	
</body>
</html>
