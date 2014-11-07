<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>

<%@ include file="../inc/include_tag.jsp" %>

<%!public String getCheckedString(int id, List<Integer> idList){
	if(idList!=null&&idList.size()>0){
		if(idList.contains(id)){
			return "checked=\"checked\"";
		}
	}
	return "";
}%>

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
					1、商品系列详情<br/>
				</p>
			</div>

			<%
			WxProduct product = (WxProduct)request.getAttribute("product"); 
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
							<label class="col-sm-2 control-label text-right">商品大图1:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${product.productPic1Url}" id="productPic1Link"  class="lightbox">
									<img id="productPic1Image" src="${product.productPic1Url}" width="200px" />
								</a>
								<input id="productPic1Url" type="hidden" name="productPic1Url" value="${product.productPic1Url}"/>
								<input type="file" name="imageFile" id="imageFile1" class="imageFile styled" imageIndex="1">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品大图2:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${product.productPic2Url}" id="productPic2Link"  class="lightbox">
									<img id="productPic2Image" src="${product.productPic2Url}" width="200px" />
								</a>
								<input id="productPic2Url" type="hidden" name="productPic2Url" value="${product.productPic2Url}"/>
								<input type="file" name="imageFile" id="imageFile2" class="imageFile styled" imageIndex="2">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品大图3:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${product.productPic3Url}" id="productPic3Link"  class="lightbox">
									<img id="productPic3Image" src="${product.productPic3Url}" width="200px" />
								</a>
								<input id="productPic3Url" type="hidden" name="productPic3Url" value="${product.productPic3Url}"/>
								<input type="file" name="imageFile" id="imageFile3" class="imageFile styled" imageIndex="3">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品大图4:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<a href="${product.productPic4Url}" id="productPic4Link"  class="lightbox">
									<img id="productPic4Image" src="${product.productPic4Url}" width="200px" />
								</a>
								<input id="productPic4Url" type="hidden" name="productPic4Url" value="${product.productPic4Url}"/>
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
							<label class="col-sm-2 control-label text-right">商品SN: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="outId" id="outId" value="${product.outId}"/>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品描述: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-10"> 
								<div class="block-inner">
									<textarea class="ckeditor" name="description" id="description">
										${product.description}
									</textarea>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">参数规格: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-6">
								<textarea name="param" rows="3" cols="5" class="elastic form-control" placeholder="上限1000字">${product.param}</textarea>
							</div>
						</div>
						
						<%
						List<WxSkuProp> skuPropList = (List<WxSkuProp>)request.getAttribute("skuPropList");
						if(skuPropList!=null){
							for(WxSkuProp skuProp: skuPropList){
								int skuPropId = skuProp.getId();
						%>
						<div class="form-group">
							<label class="col-sm-2 control-label text-right"><%=skuProp.getName()%>: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-6">
								<%
								List<Integer> productSkuValueIdList = (List<Integer>)request.getAttribute("productSkuValueIdList");
								
								List<WxSkuPropValue> skuPropValueList = (List<WxSkuPropValue>)request.getAttribute("skuPropValueList");
								if(skuPropValueList!=null&&skuPropValueList.size()>0){
									for(WxSkuPropValue skuPropValue : skuPropValueList){
										if(skuPropId==skuPropValue.getSkuPropId()){
								%>
									<div class="checkbox-inline checkbox-info">
										<label>
											<input class="styled" type="checkbox" name="productSkuValueIds" value="<%=skuPropValue.getId()%>" <%=getCheckedString(skuPropValue.getId(), productSkuValueIdList)%> disabled="disabled"/>
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
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">状 态: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<form:select path="product.status" class="select-liquid">
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
		                $('#productPic'+imageIndex+'Image').attr("src", imageUrl);
		                $('#productPic'+imageIndex+'Link').attr("href", imageUrl);
		                $('#productPic'+imageIndex+'Url').val(imageUrl);
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
