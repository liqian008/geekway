<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>
<%@page import="com.bruce.foundation.enumeration.*"%>
<%@page import="com.bruce.foundation.model.paging.*"%>
<%@page import="com.bruce.foundation.admin.utils.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>后台管理系统</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/londinium-theme.min.css" rel="stylesheet" type="text/css">
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
						Tag商品关联管理
					</h3>
				</div>
			</div>
			<!-- /page header -->
			<!-- Breadcrumbs line -->
			<div class="breadcrumb-line">
				<ul class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/home/index">首页</a></li>
					<li class="active">Tag商品关联管理</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->

			
			<form id="validate" action="<s:url value='./saveProduct'/>" method="post"  class="form-horizontal form-bordered">
				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>Tag商品关联信息
						</h6>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">Tag名称:
							</label>
							<div class="col-sm-4">
	                            <label class="control-label"><span class="label label-info">${productTag.name}</span></label> 
							</div>
						</div>
						
					</div>
				</div>
			</form>
			
			
			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>商品Tag管理
					</h5> 
					<a href="./tagProductSetAdd?tagId=${productTag.id}"><span class="label label-danger pull-right">关联商品</span></a>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped dataTable">
						<thead>
							<tr>
								<th>ID</th>
                                <th>图片</th>
                                <th>名称</th>
                                <th>置顶</th>
                                <th class="team-links">操作</th>
							</tr>
						</thead>
						<tbody>
							<%
								PagingResult<Product> pagingResult = (PagingResult<Product>)request.getAttribute("productPagingData");
												List<Product> productList = pagingResult.getPageData(); 
							                           	if(productList!=null&&productList.size()>0){
							                           		for(Product product: productList){
							%>
						
							<tr>
								<td><%=product.getId()%></td>
		                        <td>
		                        	<a href="<%=product.getCoverPicUrl()%>" class="lightbox">
		                        	<img src='<%=product.getCoverPicUrl()%>' class="img-media"/>
		                        	</a>
		                        </td>
		                        <td title="SN：<%=product.getOutId()%>"><%=product.getName()%></td>
		                        <td>
									<a href="./topProduct?tagId=${productTag.id}&productId=<%=product.getId()%>"> 
									<span class="label label-success">置顶</span> 
									</a>
								</td>
		                        <td class='text-center'>
		                        	<div class="table-controls">
		                        		<a href="./removeProductTag?tagId=${productTag.id}&productId=<%=product.getId()%>"  
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="移除关联"><i class="icon-link2"></i></a>
									</div>
								</td>
							</tr>
							<%}
                           	} %>
						</tbody>
					</table>
					
					<div class="datatable-footer">
					<%=PaginatorUtil.buildPageingHtml(pagingResult, 5)%>
					</div> 
					 
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

