<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.geekway.model.WxMaterialArticle"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.utils.*"%>


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
						关注素材管理
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
					<li class="active">关注素材管理</li>
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
					1、管理员可针对【首次关注】和【重复关注】的用户，配置不同的素材，以适应不同场景下的需求。<br/>
					2、此模块下的素材，仅支持图文。如果想使用【纯文本】方式进行回复，需要把此处素材清空，在<a href="./defaultReply">【默认回复管理】</a>中进行配置即可。<br/>
				</p>
			</div>

			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>关注素材管理
					</h5>
					<a href="./subscribedMaterialAdd"><span class="label label-info pull-right">新增关注素材</span></a>
				</div> 
				<div class="datatable-media">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>序号</th>
                                <th>封面</th>
                                <th>内容</th>
                                <th>场景</th>
                                <th class="team-links">操作</th>
							</tr>
						</thead>
						<tbody>
							<%
                           	List<WxMaterialArticle> articleList = (List<WxMaterialArticle>)request.getAttribute("subscribedMaterialList");
                           	if(articleList!=null&&articleList.size()>0){
                           		int i=0;
                           		for(WxMaterialArticle article: articleList){
                           			i++;
                           	%>
							<tr>
		                        <td><%=i%></td>
		                        <td>
	                        		<a href="<%=article.getCoverImageUrl()%>" class="lightbox">
		                        	<img src='<%=article.getCoverImageUrl()%>' class="img-media"/>
		                        	</a>
		                        </td>
		                        <td>
		                        	<%=article.getShortContent()%>
		                        </td>
		                        <td><%=article.getSubscribeStatus()==1?"首次关注":"重复关注"%></td> 
		                        <td class='text-center'>
		                        	<div class="table-controls">
		                        	
										<a href="./subscribedMaterialEdit?articleId=<%=article.getId()%>"
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="编 辑"><i class="icon-pencil3"></i></a> 
										<a href="./delSubscribedMaterial?articleId=<%=article.getId()%>" 
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
