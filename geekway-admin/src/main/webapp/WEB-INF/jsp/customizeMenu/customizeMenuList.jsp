<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.geekway.model.WxCustomizeMenu"%>
<%@page import="java.text.SimpleDateFormat"%>

<%!
public String displayMenuName(String name, int parentId){
	StringBuilder sb = new StringBuilder();
	if(parentId>1){
		sb.append("|");
		sb.append("—— ");
	}
	sb.append(name);
	return sb.toString();
}
%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Geekway微信管理平台</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/londinium-theme.min.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath}/css/styles.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/icons.min.css" rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
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
						自定义菜单管理
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
					<li class="active">自定义菜单管理</li>
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
					1、点击下方【上传自定义菜单】按钮，可将本地的自定义菜单结构上传至服务器（需先清空服务器端的自定义菜单数据）。<br/>
					2、目前自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。<br/>
					3、创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。建议测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
				</p>
			</div>
			
			<div class="tabbable page-tabs">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="javascript:void(0)">
							<i class="icon-hammer"></i>本地自定义菜单
						</a>
					</li>
					<li>
						<a href="./mpMenuList">
							<i class="icon-table2"></i>线上自定义菜单
						</a>
					</li>
				</ul>
				
				<!-- Table view -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h5 class="panel-title">
							<i class="icon-people"></i>自定义菜单管理
						</h5>
						<a href="./customizeMenuAdd"><span class="label label-danger pull-right">新增自定义菜单</span></a>
					</div>
					<div class="table-responsive">
							<table class="table table-bordered table-striped table-check">
							<thead>
								<tr>
									<th>ID</th>
	                                <th>菜单名称</th>
	                                <th>Key</th>
	                                <th>类型</th>
	                                <th>排序</th>
	                                <th>链接</th>
	                                <th class="team-links">操作</th> 
								</tr>
							</thead>
							<tbody>
								<%
	                           	List<WxCustomizeMenu> customizeMenuList = (List<WxCustomizeMenu>)request.getAttribute("customizeMenuList");
	                           	if(customizeMenuList!=null&&customizeMenuList.size()>0){
	                           		int i=0;
	                           		for(WxCustomizeMenu customizeMenu: customizeMenuList){
	                           			i++;
	                           	%>
								<tr>
			                        <td><%=i%></td>
			                        <td><%=displayMenuName(customizeMenu.getMenuName(), customizeMenu.getParentId())%></td>
			                        <td><%=customizeMenu.getMenuKey()%></td>
			                        <td><%=customizeMenu.getMenuType()%></td> 
			                        <td><%=customizeMenu.getSort()%></td>
			                        <td> 
			                        	<%
			                        	if("view".equalsIgnoreCase(customizeMenu.getMenuType())){%>
			                        		<a href="<%=customizeMenu.getUrl()%>" target="_blank">查看</a>
			                        	<%}%>
			                        </td>
			                        <td class='text-center'>
			                        	<div class="table-controls">
											<a href="./customizeMenuEdit?customizeMenuId=<%=customizeMenu.getId()%>"
												class="btn btn-link btn-icon btn-xs tip" title=""
												data-original-title="编 辑"><i class="icon-pencil3"></i></a>
											<a href="./delArticle?customizeMenuId=<%=customizeMenu.getId()%>"
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
					
					<div class="table-footer">
						<div class="table-actions">
							<a href="./mpMenuCreate" class="btn btn-danger btn-xs">上传自定义菜单</a>
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

