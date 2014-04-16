<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.WxDefaultReply"%>

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
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery/1.10.2/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="../js/plugins/charts/sparkline.min.js"></script>
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
<script type="text/javascript"
	src="../js/plugins/forms/uploader/plupload.full.min.js"></script>
<script type="text/javascript"
	src="../js/plugins/forms/uploader/plupload.queue.min.js"></script>
<script type="text/javascript"
	src="../js/plugins/forms/wysihtml5/wysihtml5.min.js"></script>
<script type="text/javascript"
	src="../js/plugins/forms/wysihtml5/toolbar.js"></script>
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
						默认回复
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
					<li class="active">默认回复</li>
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
				<p>系统在匹配不到关键词的情况下将使用默认回复，请务必填写完整！</p>
			</div>
			
			<%
			WxDefaultReply defaultReply = (WxDefaultReply)request.getAttribute("defaultReply");
			%>

			<form id="validate" action="<s:url value='./saveDefaultReply'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>编辑默认回复
						</h6>
					</div>
					<div class="panel-body">
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">订阅默认回复: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
	                            <form:hidden path="defaultReply.id"/>
								<textarea name="subscribeReply" rows="3" cols="5" class="elastic form-control" placeholder="上限200字">${defaultReply.subscribeReply}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">文本默认回复: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
								<textarea name="textReply" rows="3" cols="5" class="elastic form-control" placeholder="上限200字">${defaultReply.textReply}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">图片默认回复: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
								<textarea name="imageReply" rows="3" cols="5" class="elastic form-control" placeholder="上限200字">${defaultReply.imageReply}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">语音默认回复: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
	                          	<textarea name="voiceReply" rows="3" cols="5" class="elastic form-control" placeholder="上限200字">${defaultReply.voiceReply}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">按钮事件默认回复: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
								<textarea name="menuClickReply" rows="3" cols="5" class="elastic form-control" placeholder="上限200字">${defaultReply.menuClickReply}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">LBS默认回复: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
	                            <textarea name="locationReply" rows="3" cols="5" class="elastic form-control" placeholder="上限200字">${defaultReply.locationReply}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">视频默认回复: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-8">
	                            <textarea name="videoReply" rows="3" cols="5" class="elastic form-control" placeholder="上限200字">${defaultReply.videoReply}</textarea>
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
</html>
