<%@page import="java.util.Map.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
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
						商品SKU数据
					</h3>
				</div>
			</div>
			<!-- /page header -->
			<!-- Breadcrumbs line -->
			<div class="breadcrumb-line">
				<ul class="breadcrumb">
					<li><a href="javascript:void(0)">首页</a></li>
					<li class="active">修改商品SKU数据</li>
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
					1、商品SKU详情<br/>
					2、商品SKU图片尺寸应为1976 × 1536，透明背景，且大小应尽量控制在200K以内<br/>
				</p>
			</div>

			<%
			WxProductSku productSku = (WxProductSku)request.getAttribute("productSku");
			WxProduct product = (WxProduct)request.getAttribute("product");
			%>

			<form id="validate" action="<s:url value='./saveSku'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>修改商品SKU数据
						</h6>
					</div>
					<div class="panel-body">
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品信息: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">
									${product.name}
									<input type="hidden" name="id" id="id" value="${productSku.id}" readonly="readonly"/>
									<input type="hidden" name="productId" id="productId" value="${product.id}" readonly="readonly"/>
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品大图1:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${productSku.skuPic1Url}" id="skuPic1Link"  class="lightbox">
									<img id="skuPic1Image" src="${productSku.skuPic1Url}" width="200px" />
								</a>
								<input id="skuPic1Url" type="hidden" name="skuPic1Url" value="${productSku.skuPic1Url}"/>
								<input type="file" name="imageFile" id="imageFile1" class="imageFile styled" imageIndex="1">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品大图2:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${productSku.skuPic2Url}" id="skuPic2Link"  class="lightbox">
									<img id="skuPic2Image" src="${productSku.skuPic2Url}" width="200px" />
								</a>
								<input id="skuPic2Url" type="hidden" name="skuPic2Url" value="${productSku.skuPic2Url}"/>
								<input type="file" name="imageFile" id="imageFile2" class="imageFile styled" imageIndex="2">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品大图3:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${productSku.skuPic3Url}" id="skuPic3Link"  class="lightbox">
									<img id="skuPic3Image" src="${productSku.skuPic3Url}" width="200px" />
								</a>
								<input id="skuPic3Url" type="hidden" name="skuPic3Url" value="${productSku.skuPic3Url}"/>
								<input type="file" name="imageFile" id="imageFile3" class="imageFile styled" imageIndex="3">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品大图4:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${productSku.skuPic4Url}" id="skuPic4Link"  class="lightbox">
									<img id="skuPic4Image" src="${productSku.skuPic4Url}" width="200px" />
								</a>
								<input id="skuPic4Url" type="hidden" name="skuPic4Url" value="${productSku.skuPic4Url}"/>
								<input type="file" name="imageFile" id="imageFile4" class="imageFile styled" imageIndex="4">
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品名称: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="name" id="name" value="${product.name}"/>
								<form:hidden path="product.id"/>
							</div>
						</div>
						
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">SKU名称: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<label class="control-label">
									${productSku.name}
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">SKU KEY: <span class="mandatory">*</span></label>
							<div class="col-sm-8">
								<label class="control-label">
									${productSku.propertiesName}
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">SKU基本信息: <span class="mandatory">*</span>
							</label>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="originPrice" id="price" value="${productSku.originPrice}"/>
								<span class="label label-info label-block">原价(元)</span>
							</div>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="price" id="price" value="${productSku.price}"/>
								<span class="label label-danger label-block">现价(元)</span>
							</div>
							
							<%-- <div class="col-sm-2">
								<input type="text" class="form-control" name="postFee" id="postFee" value="${productSku.postFee}"/>
								<span class="label label-success label-block">运费(元)</span>
							</div> --%>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="stock" id="stock" value="${productSku.stock}"/>
								<span class="label label-primary label-block">库存(个)</span>
							</div>
						</div>
						
						 
						<div class="form-actions text-right">
							<input type="reset" value="重 置" class="btn btn-danger">
							<input type="submit" value="提 交" class="btn btn-primary">
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
	    $(".imageFile").change(function(){
	    	var imageIndex =$(this).attr("imageIndex");
	    	alert(imageIndex);
	        //创建FormData对象
	        var data = new FormData();
	        //为FormData对象添加数据 
	        data.append('productImage', $('#imageFile'+imageIndex)[0].files[0]);
	        $.ajax({
	            url:'/geekway-admin/product/imageUpload',
	            type:'POST',
	            data:data,
	            cache: false,
	            contentType: false,    //不可缺
	            processData: false,    //不可缺
	            success:function(responseData){
	                if(responseData.result==1){
	                	var imageUrl = responseData.data.uploadImageMap.original.url;
		                $('#skuPic'+imageIndex+'Image').attr("src", imageUrl);
		                $('#skuPic'+imageIndex+'Link').attr("href", imageUrl);
		                $('#skuPic'+imageIndex+'Url').val(imageUrl);
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
