<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.geekway.model.wx.json.WxMenuBtn"%>
<%@page import="java.text.SimpleDateFormat"%>

<%!public String displayMenuName(String name, int level){
	StringBuilder sb = new StringBuilder();
	if(level>1){
		sb.append("|");
		for(int i=1;i<level;i++){
			sb.append("——");			
		}
		sb.append(" ");
	}
	sb.append(name);
	return sb.toString();
}%>




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
						菜单Code管理
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
					<li class="active">查看自定义菜单</li>
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
					1、此菜单项内容取自微信公众账户官方平台。出于微信平台的访问限制，每天访问此功能的次数不应超过100次<br/>
				</p>
			</div>
			
			<div class="tabbable page-tabs">
				<ul class="nav nav-tabs">
					<li>
						<a href="./customizeMenuList">
							<i class="icon-hammer"></i>本地自定义菜单
						</a>
					</li>
					<li class="active">
						<a href="javascript:void(0)">
							<i class="icon-table2"></i>线上自定义菜单
						</a>
					</li>
				</ul>
			
				<!-- Table view -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h5 class="panel-title">
							<i class="icon-people"></i>查看自定义菜单
						</h5>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-striped table-check">
							<thead>
								<tr>
									<th>Level</th>
	                                <th>菜单名称</th>
	                                <th>Key</th>
	                                <th>类型</th>
	                                <th>链接</th>
								</tr>
							</thead>
							<tbody>
								<%
									List<WxMenuBtn> menuButtonList = (List<WxMenuBtn>)request.getAttribute("menuButtonList");
									                           	if(menuButtonList!=null&&menuButtonList.size()>0){
									                           		int i=0;
									                           		for(WxMenuBtn menuButton: menuButtonList){ 
									                           			i++;
								%>
								<tr>
			                        <td><%=menuButton.getLevel()%>级</td>
			                        <td><%=displayMenuName(menuButton.getName(), menuButton.getLevel())%></td>
			                        <td><%=menuButton.getKey()==null?"":menuButton.getKey()%></td>
			                        <td><%=menuButton.getType()==null?"":menuButton.getType()%></td>
			                        <td>
			                        	<%
			                        	if("view".equalsIgnoreCase(menuButton.getType())){%>
			                        		<a href="<%=menuButton.getUrl()%>" target="_blank">点击</a>
			                        	<%}%>
			                        </td>
	                               </tr>
								<%}
	                           	} %>
							</tbody>
						</table>
					</div>
					
					<div class="table-footer">
						<div class="table-actions">
							<!-- 
							<label>操作:</label>
							-->
							<a href="./mpMenuDelete" class="btn btn-danger btn-xs">清空自定义菜单</a>
						</div> 
					</div>
					
				</div>
				<!-- /table view -->
			
			</div>
			

			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
</body>
</html>

