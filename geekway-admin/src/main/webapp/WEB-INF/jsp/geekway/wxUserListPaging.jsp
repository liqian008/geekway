<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>
<%@page import="com.bruce.foundation.enumeration.*"%>
<%@page import="com.bruce.foundation.model.paging.*"%>
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
						用户管理 
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
					<li class="active">用户管理</li>
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
					1、用户资料的同步策略为每小时一次。即新关注的用户资料，一小时内可以同步回来。<br/>
					2、点击【查看】按钮，可查看用户详情<br/>
				</p>
			</div>

			
			
			<form id="validate" action="<s:url value='./wxUserPaging'/>" method="post" >
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
									<label>用户:</label>
									<input type="text" name="user" placeholder="支持模糊匹配" class="form-control">
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
						<i class="icon-people"></i>用户管理
					</h5>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped dataTable">
						<thead>
							<tr>
								<th>序号</th>
								<th>头像</th>
								<th>状态</th>
								<th>昵称</th>
								<th>OPENID</th>
								<th>区域</th>
                               <!--  <th>关注时间</th> -->
                                <th class="team-links">操作</th>
							</tr>
						</thead>
						<tbody>
							<%
							PagingResult<WxUser> pagingResult = (PagingResult<WxUser>)request.getAttribute("wxUserPagingData");
							List<WxUser> wxUserList = pagingResult.getPageData();
							if(wxUserList!=null&&wxUserList.size()>0){
                           		int i=0;
                           		for(WxUser wxUser: wxUserList){
                           			boolean synced = wxUser.getSyncStatus()>0;
                           			i++;
                           	%>
						
							<tr>
		                        <td><%=i%></td>
		                        <td>
		                        	<%if(synced){ %>
	                        		<a href="javascript:void(0)" class="lightbox">
		                        	<img src="<%=wxUser.getHeadImgUrl()%>" class="img-media"/>
		                        	</a>
		                        	<%}%>
		                        </td>
		                        <td><%=wxUser.getSubscribeStatus()==1?"已关注":"已取消"%></td>
		                        <td><%=synced?wxUser.getNickname():"详细信息未同步"%></td>
		                        <td><%=wxUser.getOpenId()%></td>
		                        <td>
		                        	<%if(synced){%>
		                        	<%=wxUser.getCountry()+"-"+wxUser.getProvince()+"-"+wxUser.getProvince()%>
		                        	<%}%>
		                        </td>
		                        <td class='text-center'>
		                        	<div class="table-controls">
		                        		<%String link = "./historyMessageDialog?openId="+wxUser.getOpenId();%>
											<a href="<%=link%>"
												class="btn btn-link btn-icon btn-xs tip" title=""
												data-original-title="回 复"><i class="icon-bubble3"></i></a>
		                        		<%if(synced){%>
										<a href="./wxUserInfo?wxUserId=<%=wxUser.getId()%>"
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="查看"><i class="icon-pencil3"></i></a> 
										<%} %>
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