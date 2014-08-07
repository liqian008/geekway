<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>

<%@ include file="../inc/include_tag.jsp" %>


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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/uploader/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/uploader/plupload.queue.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor/config.js"></script>
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
						微信消息管理
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
					<li class="active">微信消息管理</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->
			
			<div class="tabbable page-tabs">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="javascript:void(0)">
							<i class="icon-hammer"></i>今日消息记录
						</a>
					</li>
					<li>
						<a href="./mpMenuList">
							<i class="icon-table2"></i>全部消息记录
						</a>
					</li>
				</ul>
				
				<!-- Table view -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h5 class="panel-title">
							<i class="icon-people"></i>消息列表
						</h5>
					</div>
					<div class="table-responsive">
							<table class="table table-bordered table-striped table-check">
							<thead>
								<tr>
									<th>ID</th>
	                                <th>用户</th>
	                                <th>消息</th>
	                                <th>发送时间</th>
	                                <th class="team-links">操作</th> 
								</tr>
							</thead>
							<tbody>
								<%
								List<WxHistoryMessage> historyMessageList = (List<WxHistoryMessage>)request.getAttribute("historyMessageList");
								if(historyMessageList!=null&&historyMessageList.size()>0){
	                           		int i=0;
	            					for(WxHistoryMessage historyMessage: historyMessageList){
	                           			i++;
	                           	%>
								<tr>
			                        <td><%=i%></td>
			                        <td>
			                        	<a href="javascript:void(0)" class="lightbox">
			                        	<%if(historyMessage.getMpUser()!=null){%>
			                        	<img src="<%=historyMessage.getMpUser().getHeadImgUrl()%>" class="img-media"/>
			                        	<%}%>
			                        	</a>
			                        </td> 
			                        <td>
			                        	<%=historyMessage.getContent()%>
			                        	<%if("image".equalsIgnoreCase(historyMessage.getMsgType())){%>
			                        	<a href="javascript:void(0)" class="lightbox">
			                        	<img src="<%=historyMessage.getPicUrl()%>" class="img-media"/>
			                        	</a>
			                        	<%}%>
			                        </td>
			                        <td><%=DateUtil.date2YMDHMS(historyMessage.getSendTime())%></td> 
			                        <td class='text-center'>
			                        	<div class="table-controls">
			                        		<%String link = "./historyMessageDialog?openId="+historyMessage.getOpenId();%>
											<a href="<%=link%>"
												class="btn btn-link btn-icon btn-xs tip" title=""
												data-original-title="回 复"><i class="icon-bubble3"></i></a>
											
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
				
			</div>
			
			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
	
</body>
</html>
