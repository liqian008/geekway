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
<title>ITO管理平台</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/londinium-theme.min.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath}/css/styles.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/icons.min.css" rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
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
					1、xxxxxxxxxx<br/>
				</p>
			</div>

			<%
			ItoProductOrder order = (ItoProductOrder)request.getAttribute("order");
			%>

			<form id="validate" action="<s:url value='./saveOrder'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>查看订单信息
						</h6>
					</div>
					<div class="panel-body">
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品ID: <span class="mandatory">*</span></label>
							<div class="col-sm-2">
								<input type="text" class="form-control" name="productId" id="productId" value="${order.productId}" readonly="readonly"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品名称: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="title" id="title" value="${order.title}" readonly="readonly"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品SN: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="outId" id="outId" value="${order.outId}" readonly="readonly"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品描述: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
								<textarea name="description" rows="3" cols="5" class="elastic form-control" placeholder="上限1000字">${order.description}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品大图:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${order.orderPicUrl}" id="cover-image-link"  class="lightbox">
									<img id="cover-image" src="${order.orderPicUrl}" width="200px" />
								</a>
							</div> 
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">价格信息: <span class="mandatory">*</span>
							</label>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="totalPrice" id="totalPrice" value="${order.totalPrice}"/>
								<span class="label label-danger label-block">总价(元)</span>
							</div>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="price" id="price" value="${order.price}"/>
								<span class="label label-info label-block">单价(元)</span>
							</div>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="num" id="num" value="${order.num}"/>
								<span class="label label-primary label-block">数量(个)</span>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品SKU_ID: <span class="mandatory">*</span></label>
							<div class="col-sm-2">
								<input type="text" class="form-control" name="skuId" id="skuId" value="${order.skuId}" readonly="readonly"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品SKU信息: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="sku" id="sku" value="${order.sku}" readonly="readonly"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">支付类型: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="payType" id="payType" value="${order.payType}" readonly="readonly"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">支付状态: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="payStatus" id="payStatus" value="${order.payStatus}" readonly="readonly"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">运单号: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="postSn" id="postSn" value="${order.postSn}" readonly="readonly"/>
							</div>
						</div>
						
						
						<div class="form-actions text-right">
							<input type="reset" value="重 置" class="btn btn-danger">
							<input type="submit" value="下一步" class="btn btn-primary">
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
	    $("#cover-image-file").change(function(){
	        //创建FormData对象
	        var data = new FormData();
	        //为FormData对象添加数据 
	        data.append('imageFile', $('input[type=file]')[0].files[0]);  
	        $.ajax({
	            url:'/geekway-admin/geekway/imageUpload',
	            type:'POST',
	            data:data,
	            cache: false,
	            contentType: false,    //不可缺
	            processData: false,    //不可缺
	            success:function(responseData){
	                if(responseData.result==1){
	                	var imageUrl = responseData.data.originalImage.url;
		                $('#cover-image').attr("src", imageUrl);
		                $('#cover-image-link').attr("href", imageUrl);
		                $('#cover-image-url').val(imageUrl);
	                }else{
	                	alert(responseData.message);
	                }
	            }
	        });
	    });
	});
	</script>
	
</body>
</html>
