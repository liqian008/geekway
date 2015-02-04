<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>
<%@page import="com.bruce.geekway.model.paging.*"%>
<%@page import="com.bruce.foundation.admin.utils.*"%>

<%@ include file="../inc/include_tag.jsp" %>

<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

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
						我的应用列表
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
					<li><a href="${pageContext.request.contextPath}/home/index">首页</a></li>
					<li class="active">我的微信应用列表</li>
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
				</p>
			</div>
			
			
			<form id="validate" action="<s:url value='./myWxAppList'/>" method="post" >
				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>条件筛选
						</h6>
					</div>
					<div class="panel-body"> 
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">
									<label>应用名称:</label><input type="text" name="name" placeholder="支持模糊匹配" class="form-control" value="${name}"> 
								</div>
							</div>
						</div>
					
						<div class="form-actions text-center">
							<input type="submit" value="查 询" class="btn btn-primary btn-sm"> 
							<input type="reset" value="重 置" class="btn btn-default btn-sm">
						</div>
					</div>
				</div>
			</form>
			
			 
			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>我的应用列表
					</h5>
					<a href="./myWxAppAdd"><span class="label label-danger pull-right">添加微信应用</span></a>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped dataTable">
						<thead>
							<tr>
								<th>ID</th>
                                <th>名称</th>
                                <th>描述</th>
                                <th>状态</th>
                                <th>创建时间</th>
                                <th class="team-links">操作</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<WxApp> appList = (List<WxApp>)request.getAttribute("appList");
                           	if(appList!=null&&appList.size()>0){
                           		for(WxApp app: appList){
                           	%>
							<tr>
								<td><%=app.getId()%></td>
		                        <td><%=app.getName()%></td>
		                        <td><%=app.getDescription()%></td>
		                        <td><%=app.getStatus()==(short)1?"启用":"禁用"%></td>
		                        <td><%=sdf.format(app.getCreateTime())%></td>
		                        <td class='text-center'>
		                        	<div class="table-controls">
										<a href="./myWxAppEdit?appId=<%=app.getId()%>"
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="查 看"><i class="icon-pencil3"></i></a>
										<a href="../product/myProductPaging?appId=<%=app.getId()%>"
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="查看道具商品"><i class="icon-tree3"></i></a>
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
