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
						编辑商品分类
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
					<li class="active">编辑商品分类</li>
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
					<br/>
				</p>
			</div>
			
			<%
			WxProductCategory category = (WxProductCategory)request.getAttribute("category");
			%>

			<form id="validate" action="<s:url value='./saveProductCategory'/>" method="post"  class="form-horizontal form-bordered">
				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>编辑商品分类 
						</h6>
					</div>
					<div class="panel-body">
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">分类名称: <span class="mandatory">*</span></label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name="name" id="name" value="${category.name}"/>
	                            <form:hidden path="category.id"/> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">描述: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="description" id="description" value="${category.description}"/>
							</div>
						</div>
						
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">宣传大图1:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${category.categoryPic1Url}" id="categoryPic1Link"  class="lightbox">
									<img id="categoryPic1Image" src="${category.categoryPic1Url}" width="200px" />
								</a>
								<input id="categoryPic1Url" type="hidden" name="categoryPic1Url" value="${category.categoryPic1Url}"/>
								<input type="file" name="imageFile" id="imageFile1" class="imageFile styled" imageIndex="1">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">大图链接1: <span class="mandatory">*</span></label>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="categoryLink1" id="categoryLink1" value="${category.categoryLink1}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">宣传大图2:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${category.categoryPic2Url}" id="categoryPic2Link"  class="lightbox">
									<img id="categoryPic2Image" src="${category.categoryPic2Url}" width="200px" />
								</a>
								<input id="categoryPic2Url" type="hidden" name="categoryPic2Url" value="${category.categoryPic2Url}"/>
								<input type="file" name="imageFile" id="imageFile2" class="imageFile styled" imageIndex="2">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">大图链接2: <span class="mandatory">*</span></label>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="categoryLink2" id="categoryLink2" value="${category.categoryLink2}"/>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">宣传大图3:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${category.categoryPic3Url}" id="categoryPic3Link"  class="lightbox">
									<img id="categoryPic3Image" src="${category.categoryPic3Url}" width="200px" />
								</a>
								<input id="categoryPic3Url" type="hidden" name="categoryPic3Url" value="${category.categoryPic3Url}"/>
								<input type="file" name="imageFile" id="imageFile3" class="imageFile styled" imageIndex="3">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">大图链接3: <span class="mandatory">*</span></label>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="categoryLink3" id="categoryLink3" value="${category.categoryLink3}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">宣传大图4:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${category.categoryPic4Url}" id="categoryPic4Link"  class="lightbox">
									<img id="categoryPic4Image" src="${category.categoryPic4Url}" width="200px" />
								</a>
								<input id="categoryPic4Url" type="hidden" name="categoryPic4Url" value="${category.categoryPic4Url}"/>
								<input type="file" name="imageFile" id="imageFile4" class="imageFile styled" imageIndex="4">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">大图链接4: <span class="mandatory">*</span></label>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="categoryLink4" id="categoryLink4" value="${category.categoryLink4}"/>
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
</body>


<script type="text/javascript">
$(document).ready(function(){
    $(".imageFile").change(function(){
    	var imageIndex =$(this).attr("imageIndex");
        //创建FormData对象
        var data = new FormData();
        //为FormData对象添加数据 
        data.append('image', $('#imageFile'+imageIndex)[0].files[0]);
        $.ajax({
            url:'${pageContext.request.contextPath}/upload/uploadQiniu',
            type:'POST',
            data:data,
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            success:function(responseData){
                if(responseData.result==1){
                	var imageUrl = responseData.data.uploadImageMap.original.url;
	                $('#categoryPic'+imageIndex+'Image').attr("src", imageUrl);
	                $('#categoryPic'+imageIndex+'Link').attr("href", imageUrl);
	                $('#categoryPic'+imageIndex+'Url').val(imageUrl);
                }else{
                	alert(responseData.message);
                }
            }
        });
    });
});
</script>

</html>
