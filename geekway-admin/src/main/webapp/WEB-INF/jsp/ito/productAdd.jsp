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
						商品详情
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
					<li class="active">商品详情</li>
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

			<!-- WYSIWYG editor inside panel -->
			<!-- 
			<div class="panel panel-default">
				<div class="panel-heading">
					<h6 class="panel-title">
						<i class="icon-pencil"></i>详情
					</h6>
				</div>
				<div class="panel-body">
					<form action="wysiwyg.html#" role="form">
						<div class="block-inner">
							<textarea class="editor" placeholder="请输入详情 ..."></textarea>
						</div>
						<div class="form-actions text-right">
							<button type="submit" class="btn btn-danger">Cancel</button>
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</form>
				</div>
			</div>
			-->
			<!-- /WYSIWYG editor inside panel -->

			<%
			ItoProduct product = (ItoProduct)request.getAttribute("product");
			%>

			<form id="validate" action="<s:url value='./saveProduct'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>编辑商品详情
						</h6>
					</div>
					<div class="panel-body">
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品名称: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<form:hidden path="product.id"/>
								<input type="text" class="form-control" name="title" id="title" value="${product.title}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品SN: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="outId" id="outId" value="${product.outId}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品描述: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-6">
								<%-- 
								<input type="text" class="form-control" name="description" id="description" value="${product.description}"/>
								--%>
								<textarea name="description" rows="3" cols="5" class="elastic form-control" placeholder="上限1000字">${product.description}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品大图:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${product.productPicUrl}" id="cover-image-link"  class="lightbox">
									<img id="cover-image" src="${product.productPicUrl}" width="200px" />
								</a>
								<input id="cover-image-url" type="hidden" name="productPicUrl" value="${product.productPicUrl}"/>
								<input type="file" name="imageFile" id="cover-image-file" class="styled">
							</div> 
						</div>
						
						<!-- 
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">单品基础信息: <span class="mandatory">*</span>
							</label>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="originPrice" id="originPrice" value="${product.originPrice}"/>
								<span class="label label-info label-block">原价(元)</span>
							</div>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="price" id="price" value="${product.price}"/>
								<span class="label label-danger label-block">现价(元)</span>
							</div>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="postFee" id="postFee" value="${product.postFee}"/>
								<span class="label label-success label-block">运费(元)</span>
							</div>
							
							<div class="col-sm-2">
								<input type="text" class="form-control" name="num" id="num" value="${product.num}"/>
								<span class="label label-primary label-block">库存(个)</span>
							</div>
						</div>
						 -->
						 
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">外部购买链接: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-6"> 
								<input type="text" class="form-control" id="buyUrl" name="buyUrl" value="<%=product==null||product.getId()==null?"":product.getBuyUrl()%>"/>
							</div> 
						</div>
						
						<%
						List<ItoSkuProp> skuPropList = (List<ItoSkuProp>)request.getAttribute("skuPropList");
						if(skuPropList!=null){
							for(ItoSkuProp skuProp: skuPropList){
								int skuPropId = skuProp.getId();
						%>
						<div class="form-group" id="sku-config">
							<label class="col-sm-2 control-label text-right"><%=skuProp.getName()%>: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-6">
								<%
								List<ItoSkuPropValue> skuPropValueList = (List<ItoSkuPropValue>)request.getAttribute("skuPropValueList");
								if(skuPropValueList!=null&&skuPropValueList.size()>0){
									for(ItoSkuPropValue skuPropValue : skuPropValueList){
										if(skuPropId==skuPropValue.getSkuPropId()){
								%>
									<div class="checkbox-inline checkbox-info">
										<label>
											<input class="styled" type="checkbox" name="productSkuValueIds" value="<%=skuPropValue.getId()%>" />
											<%=skuPropValue.getName()%>
										</label>
									</div>
									<%}
									}
								}%>
							</div> 
						</div>
						<%}
						}%>
						
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
