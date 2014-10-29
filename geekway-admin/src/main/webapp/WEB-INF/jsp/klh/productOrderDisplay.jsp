<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>
<%@page import="java.text.*"%>

<%@ include file="../inc/include_tag.jsp" %>

<%
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>可乐惠管理平台</title>
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
						兑换订单详情
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
					<li class="active">兑换订单详情</li>
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
					1、兑换订单列表<br/>
				</p>
			</div>

			<%
			KlhProductOrder productOrder = (KlhProductOrder)request.getAttribute("productOrder");
			%>

			<form id="validate" action="<s:url value='./saveProduct'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>兑换订单详情
						</h6>
					</div>
					<div class="panel-body">
					
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">产品标题: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="title" id="title" value="${productOrder.title}"/>
								<form:hidden path="productOrder.id"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">产品描述: <span class="mandatory">*</span></label>
							<div class="col-sm-8"> 
								<div class="block-inner">
									<textarea name="description" rows="2" cols="5" class="elastic form-control" placeholder="上限100字">${productOrder.description}</textarea>
								</div>
							</div>
						</div> 
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">兑换积分: <span class="mandatory">*</span></label>
							<div class="col-sm-2"> 
								<div class="block-inner">
									<input type="text" class="form-control" name="score" id="score" value="${productOrder.score}"/>
								</div>
							</div>
						</div> 
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">数量: <span class="mandatory">*</span></label>
							<div class="col-sm-2"> 
								<div class="block-inner">
									<input type="text" class="form-control" name="num" id="num" value="${productOrder.num}"/>
								</div>
							</div>
						</div> 
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">积分: <span class="mandatory">*</span></label>
							<div class="col-sm-2"> 
								<div class="block-inner">
									<input type="text" class="form-control" name="score" id="score" value="${productOrder.score}"/>
								</div>
							</div>
						</div> 
						

						<div class="form-group">
							<label class="col-sm-2 control-label text-right">收件人: <span class="mandatory">*</span></label>
							<div class="col-sm-8"> 
								<div class="block-inner">
									<input type="text" class="form-control" name="postName" id="postName" value="${productOrder.postName}"/>
								</div>
							</div>
						</div> 
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">联系电话: <span class="mandatory">*</span></label>
							<div class="col-sm-8"> 
								<div class="block-inner">
									<input type="text" class="form-control" name="postMobile" id="postMobile" value="${productOrder.postMobile}"/>
								</div>
							</div>
						</div> 
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">邮寄地址: <span class="mandatory">*</span></label>
							<div class="col-sm-8"> 
								<div class="block-inner">
									<input type="text" class="form-control" name="postAddress" id="postAddress" value="${productOrder.postAddress}"/>
								</div>
							</div>
						</div> 
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">邮政编码: <span class="mandatory">*</span></label>
							<div class="col-sm-8"> 
								<div class="block-inner">
									<input type="text" class="form-control" name="postCode" id="postCode" value="${productOrder.postCode}"/>
								</div>
							</div>
						</div> 
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