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
						历史消息
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
					<li class="active">对话消息</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->

			<%
			List<WxHistoryMessage> userMessageList = (List<WxHistoryMessage>)request.getAttribute("userMessageList");
			if(userMessageList!=null&&userMessageList.size()>0){
			%>
			<div class="tabbable page-tabs">
			<ul class="nav nav-tabs">
				<li class="active"><a href="javascript:void(0)" data-toggle="tab">
					<img src="${pageContext.request.contextPath}/images/demo/users/default_avatar.jpg" alt=""
						class="tab-img"> 对话消息<span class="status status-danger"></span></a></li>
			</ul> 

			<div class="block">
				<ul class="message-list">
					<li>
						<div class="panel-collapse collapse in" id="duke_aaron" style="height: auto;">
							<%
							//检查客服消息的时间间隔
							Boolean customReply = (Boolean)request.getAttribute("customReply");
							String openId = (String)request.getAttribute("openId");
							if(customReply!=null&&customReply){
							%>
							<form id="validate" action="<s:url value='./mpReplyText'/>" method="post"  class="form-horizontal form-bordered">
								<input type="hidden" name="openId" value="<%=openId%>"/>
								<textarea name="text" class="form-control" rows="3"
									cols="1" placeholder="请输入文本内容进行回复..."></textarea>
								<div class="message-controls">
									<div class="pull-right">
										<input type="submit" value="发 送" class="btn btn-warning">
									</div>
								</div>
							</form>
							<%}%>
							<div class="chat">
							<%
							for(WxHistoryMessage userHistoryMessage: userMessageList){
								if(userHistoryMessage.getInbox()!=null&&userHistoryMessage.getInbox()==0){
							%> 	
								<div class="message">
									<a class="message-img" href="javascript:void(0)"><img src="${pageContext.request.contextPath}/images/demo/users/default_avatar.jpg" alt=""></a>
									<div class="message-body">
										<%=userHistoryMessage.getContent() %>
										<%if("image".equalsIgnoreCase(userHistoryMessage.getMsgType())){%>
			                        	<br/>
			                        	<a href="javascript:void(0)" class="lightbox">
			                        	<img src="<%=userHistoryMessage.getPicUrl()%>" height="80px"/>
			                        	</a>
			                        	<%}%>
										
										<span class="attribution">发送时间: <%=DateUtil.date2YMDHMS(userHistoryMessage.getCreateTime()) %>  </span>
									</div>
								</div>
							<%}else{ %>
								<div class="message reversed">
									<a class="message-img" href="contacts.html#"><img src="${pageContext.request.contextPath}/images/demo/users/default_avatar.jpg" alt=""></a>
									<div class="message-body">
										<%=userHistoryMessage.getContent() %>
										<span class="attribution">回复时间: <%=DateUtil.date2YMDHMS(userHistoryMessage.getCreateTime()) %> </span>
									</div>
								</div>
							<%}
							}%>
							</div>
						</div>
					</li>
				</ul>
			</div>
			</div>
			<%} %>
			
			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
	
</body>
</html>
