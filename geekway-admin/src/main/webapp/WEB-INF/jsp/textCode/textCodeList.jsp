<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.geekway.model.WxTextCode"%>
<%@page import="java.text.SimpleDateFormat"%>



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
						代码管理
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
					<li class="active">代码管理</li>
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

			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>代码管理
					</h5>
					<a href="./textCodeAdd"><span class="label label-danger pull-right">新增代码</span></a>
				</div>
				<div class="datatable-media">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>ID</th>
                                <th>代码内容</th>
                                <th>展示类型</th>
                                <th>内容</th>
                                <th>状态</th>
                                <th class="team-links">操作</th> 
							</tr>
						</thead>
						<tbody>
							<%
                           	List<WxTextCode> textCodeList = (List<WxTextCode>)request.getAttribute("textCodeList");
                           	if(textCodeList!=null&&textCodeList.size()>0){
                           		int i=0;
                           		for(WxTextCode textCode: textCodeList){
                           			i++;
                           	%>
							<tr>
		                        <td><%=i%></td>
		                        <td><%=textCode.getKeyCode()%></td>
		                        <td><%=textCode.getDisplayType()==1?"文本回复":"数据模块"%></td>
		                        <td><%=textCode.getDisplayType()==1?textCode.getReplyContent():"【模块】"+textCode.getModuleDesc()%></td>
		                        <td>正常</td>
		                        <td class='text-center'>
		                        	<div class="table-controls">
										<a href="./textCodeEdit?textCodeId=<%=textCode.getId()%>"
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="编 辑"><i class="icon-pencil3"></i></a>
										<a href="./delArticle?textCodeId=<%=textCode.getId()%>"
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="删除"><i class="icon-remove3"></i></a>
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
</body>
</html>

