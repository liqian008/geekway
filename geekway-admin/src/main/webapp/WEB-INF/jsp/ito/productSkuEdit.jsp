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
					1、xxxxxxxxxx<br/>
				</p>
			</div>

			<%
			ItoSku productSku = (ItoSku)request.getAttribute("productSku");
			ItoProduct product = (ItoProduct)request.getAttribute("product");
			
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
						
						<input type="hidden" name="id" id="id" value="${productSku.id}" readonly="readonly"/>
						<input type="hidden" name="productId" id="productId" value="${product.id}" readonly="readonly"/>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品信息: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">
									${product.title}
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">SKU图片:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${productSku.skuPicUrl}" id="cover-image-link"  class="lightbox">
									<img id="cover-image" src="${productSku.skuPicUrl}" width="200px" />
								</a>
								<!-- 
								<input id="cover-image-url" type="hidden" name="skuPicUrl" value="${productSku.skuPicUrl}"/>
								<input type="file" name="imageFile" id="cover-image-file" class="styled">
								 -->
							</div>
						</div>
						
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">SKU名称: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">
									${productSku.name}
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">SKU: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<label class="control-label">
									${productSku.propertiesName}
								</label>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">SKU信息: <span class="mandatory">*</span>
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
								<input type="text" class="form-control" name="num" id="num" value="${productSku.num}"/>
								<span class="label label-primary label-block">库存(个)</span>
							</div>
						</div>
						
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">支付宝产品链接: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="alipaySkuUrl" value="${productSku.alipaySkuUrl}" >
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">口袋通产品链接: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="koudaitongSkuUrl" value="${productSku.koudaitongSkuUrl}" >
							</div>
						</div>
						
						
						<div class="form-actions text-right">
							<input type="reset" value="重 置" class="btn btn-danger">
							<input type="submit" value="提 交" class="btn btn-primary">
						</div>
					</div>
					
				</div>
				
			</form>
			
			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>SKU图片列表
					</h5>
					<a href="./skuImageAdd?productId=${product.id}&skuId=${productSku.id}"><span class="label label-info pull-right">添加新图片</span></a>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-check">
						<thead>
							<tr>
								<th>ID</th>
                                <th>图片</th>
                                <th>标题</th>
                                <th>排序</th>
                                <th class="team-links">操作</th>
							</tr>
						</thead>
						<tbody>
							<%
                           	List<ItoSkuImage> skuImageList = (List<ItoSkuImage>)request.getAttribute("skuImageList");
                           	if(skuImageList!=null&&skuImageList.size()>0){
                           		int i=0;
                           		for(ItoSkuImage skuImage: skuImageList){
                           			i++;
                           	%>
							<tr>
		                        <td><%=skuImage.getId()%></td>
		                        <td>
	                        		<a href="<%=skuImage.getSkuPicUrl()%>" class="lightbox">
		                        	<img src='<%=skuImage.getSkuPicUrl()%>' class="img-media"/>
		                        	</a>
		                        </td>
		                        <td><%=skuImage.getTitle()%></td>
		                        <td><%=skuImage.getSort()%></td>
		                        <td class='text-center'>
		                        	<div class="table-controls">
		                        		<a href="./skuImageEdit?skuImageId=<%=skuImage.getId()%>"  
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="编 辑"><i class="icon-pencil3"></i></a>
		                        		<a href="./delSkuImage?productId=${productSku.productId}&skuId=${productSku.id}&skuImageId=<%=skuImage.getId()%>"  
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="删 除"><i class="icon-remove3"></i></a>
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
