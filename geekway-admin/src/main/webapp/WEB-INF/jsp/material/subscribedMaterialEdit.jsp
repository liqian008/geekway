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
<title>后台管理系统</title>
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
        src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ckeditor/config.js"></script>
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
						用户关注素材内容
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
					<li><a href="index.html">首页</a></li>
					<li class="active">用户关注素材内容</li>
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
					图文素材支持Html可视化编辑，需注意以下几点： <br/>
					1、在可视化编辑器中上传图片后，需手动将图片尺寸改为100%<br/>
				</p>
			</div>

			<%
			WxMaterialArticle subscribedMaterial = (WxMaterialArticle)request.getAttribute("subscribedMaterial");
			%>

			<form id="validate" action="<s:url value='./saveSubscribedMaterial'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>编辑图文内容
						</h6>
					</div>
					<div class="panel-body">
						
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">应用场景: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
							<form:select path="subscribedMaterial.subscribeStatus" class="select-liquid">
								<form:option value="1"  label="用户首次关注时有效"/>
								<form:option value="2"  label="用户重复关注时有效"/>
							</form:select>
							<form:hidden path="subscribedMaterial.id"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">图文标题: <span class="mandatory">*</span></label>
							
							<div class="col-sm-6">
								<input type="text" class="form-control" name="title" id="title" value="${subscribedMaterial.title}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">图文短标题: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="shortTitle" id="shortTitle" value="${subscribedMaterial.shortTitle}"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label text-right">图文封面图:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<!-- 
								<input type="text" class="form-control" name="coverImageUrl" id="coverImageUrl" value="${subscribedMaterial.coverImageUrl}"/>
								<img src="http://www.jinwanr.com.cn/staticFile/image/20140306/medium/100012_bb6d4f45aacd9b97a91063cda17cd3b3.jpg" width="160px"/>
								-->
								<a href="${subscribedMaterial.coverImageUrl}" id="cover-image-link"  class="lightbox">
									<img id="cover-image" src="${subscribedMaterial.coverImageUrl}" width="200px" />
								</a>
								<input id="cover-image-url" type="hidden" name="coverImageUrl" value="${subscribedMaterial.coverImageUrl}"/>
								<input type="file" name="imageFile" id="cover-image-file" class="styled">
							</div> 
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label text-right">图文内容概要: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8"> 
								<div class="block-inner">
									<textarea name="shortContent" rows="2" cols="5" class="elastic form-control" placeholder="上限100字">${subscribedMaterial.shortContent}</textarea>
								</div>
							</div>
						</div>
						 
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">图文内容详情: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-10"> 
								<div class="block-inner">
									<textarea class="ckeditor" name="content" id="content">
										${subscribedMaterial.content}
									</textarea>
								</div>
							</div>
						</div>
						
						<%if(subscribedMaterial!=null&&subscribedMaterial.getId()!=null){%>
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">图文链接: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-6">
								<label class="control-label">
									<label class="control-label">
									<%
									String meterialLink = ArticleLinkUtil.getArticleLink(subscribedMaterial.getId());%>
									<%=meterialLink%>
									<a href="<%=meterialLink%>" target="_blank">预览</a>
								</label>
								</label>
							</div>
						</div>
						<%}%>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">状 态: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<form:select path="subscribedMaterial.status" class="select-liquid">
									<form:option value="0"  label="禁用"/>
									<form:option value="1"  label="启用"/>
								</form:select>
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
	    $("#cover-image-file").change(function(){
	        //创建FormData对象
	        var data = new FormData();
	        //为FormData对象添加数据 
	        data.append('imageFile', $('input[type=file]')[0].files[0]);  
	        $.ajax({
	            url:'${pageContext.request.contextPath}/geekway/imageUpload',
	            type:'POST',
	            data:data,
	            cache: false,
	            contentType: false,    //不可缺
	            processData: false,    //不可缺
	            success:function(responseData){
	                if(responseData.result==1){
	                	var imageUrl = responseData.data.mediumImage.url;
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
	
	<script type="text/javascript">
	CKEDITOR.replace( 'content', {
		toolbar :
            [
					['Source', 'newPage'],
	//图片    flash    表格       水平线            表情       特殊字符        分页符
	                ['Image', 'Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	//超链接  取消超链接 锚点
	                ['Link','Unlink','Anchor'],
	// 数字列表          实体列表            减小缩进    增大缩进
	                ['NumberedList','BulletedList','-','Outdent','Indent'],
	//左对 齐             居中对齐          右对齐          两端对齐
	                ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	'/',
	//加粗     斜体，     下划线      穿过线      下标字        上标字
    ['Bold','Italic','Underline','Strike','Subscript','Superscript'],
	// 样式       格式      字体    字体大小 
	                ['Styles','Format','Font','FontSize'],
	//文本颜色     背景颜色
	                ['TextColor','BGColor'],
	//全屏           显示区块
	                ['Maximize', 'ShowBlocks','-']
             ]
         }
    );
   </script>
</body>
</html>
