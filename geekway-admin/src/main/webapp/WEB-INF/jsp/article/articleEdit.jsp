<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.WxArticle"%>

<%@ include file="../inc/include_tag.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Londinium - premium responsive admin template by Eugene
	Kopyov</title>
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../css/londinium-theme.min.css" rel="stylesheet"
	type="text/css">
<link href="../css/styles.min.css" rel="stylesheet" type="text/css">
<link href="../css/icons.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery/1.10.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/plugins/charts/sparkline.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/uniform.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/select2.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/inputmask.js"></script>
<script type="text/javascript" src="../js/plugins/forms/autosize.js"></script>
<script type="text/javascript"
	src="../js/plugins/forms/inputlimit.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/listbox.js"></script>
<script type="text/javascript" src="../js/plugins/forms/multiselect.js"></script>
<script type="text/javascript" src="../js/plugins/forms/validate.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/tags.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/switch.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/uploader/plupload.full.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/uploader/plupload.queue.min.js"></script>

<script type="text/javascript"
	src="../plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="../js/ckeditor/config.js"></script>

<script type="text/javascript"
	src="../js/plugins/interface/daterangepicker.js"></script>
<script type="text/javascript"
	src="../js/plugins/interface/fancybox.min.js"></script>
<script type="text/javascript" src="../js/plugins/interface/prettify.js"></script>
<script type="text/javascript" src="../js/plugins/interface/moment.js"></script>
<script type="text/javascript"
	src="../js/plugins/interface/jgrowl.min.js"></script>
<script type="text/javascript"
	src="../js/plugins/interface/datatables.min.js"></script>
<script type="text/javascript"
	src="../js/plugins/interface/colorpicker.js"></script>
<script type="text/javascript"
	src="../js/plugins/interface/fullcalendar.min.js"></script>
<script type="text/javascript"
	src="../js/plugins/interface/timepicker.min.js"></script>
<script type="text/javascript"
	src="../js/plugins/interface/collapsible.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/application.js"></script>
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
						文章内容
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
					<li class="active">文章内容</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->
			
			<div class="callout callout-info fade in">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h5>Wide left sidebar layout</h5>
				<p>Page layout with left aligned wide sidebar, with right
					aligned icons and 4 level navigation.</p>
			</div>

			<!-- WYSIWYG editor inside panel -->
			<!-- 
			<div class="panel panel-default">
				<div class="panel-heading">
					<h6 class="panel-title">
						<i class="icon-pencil"></i>内容
					</h6>
				</div>
				<div class="panel-body">
					<form action="wysiwyg.html#" role="form">
						<div class="block-inner">
							<textarea class="editor" placeholder="请输入内容 ..."></textarea>
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
			WxArticle article = (WxArticle)request.getAttribute("article");
			%>

			<form id="validate" action="<s:url value='./saveArticle'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>编辑文章内容
						</h6>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">标 题: <span class="mandatory">*</span></label>
							
							<div class="col-sm-6">
								<input type="text" class="form-control" name="title" id="title" value="${article.title}"/>
	                            <form:hidden path="article.id"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">短标题: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="shortTitle" id="shortTitle" value="${article.shortTitle}"/>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label text-right">封面图链接:<span class="mandatory">*</span>
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="coverImageUrl" id="coverImageUrl" value="${article.coverImageUrl}"/>
							
								<!-- 
								<img src="http://www.jinwanr.com.cn/staticFile/image/20140306/medium/100012_bb6d4f45aacd9b97a91063cda17cd3b3.jpg" width="160px"/>
								<input type="file" class="styled">
								 -->
							</div> 
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label text-right">文章内容概要: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-10"> 
								<div class="block-inner">
									<textarea name="shortContent" rows="3" cols="5" class="limited form-control" placeholder="上限100字">${article.shortContent}</textarea>
								</div>
							</div>
						</div>
						 
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">文章内容详情: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-10"> 
								<div class="block-inner">
									<textarea class="ckeditor" name="content" id="content">
										${article.content}
									</textarea>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">状 态: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<form:select path="article.status" class="select-liquid">
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
