<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.WxEventCode"%>
<%@page import="com.bruce.geekway.model.WxCodeModule"%>

<%@ include file="../inc/include_tag.jsp" %>


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
						代码内容
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
					<li class="active">代码内容</li>
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
					回复类型支持两种方式： <br/>
					1、固定文本的回复方式<br/>
					2、图文集的回复方式（需事先创建相应的文章模块以做关联）
				</p>
			</div>
			
			<%
			WxEventCode eventCode = (WxEventCode)request.getAttribute("eventCode");
			%>

			<form id="validate" action="<s:url value='./saveEventCode'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>编辑代码内容
						</h6>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">事件类型:
							</label>
							<div class="col-sm-3">
								<form:select path="eventCode.eventType" class="form-control">
									<form:option value="1"  label="文本请求事件"/>
									<form:option value="2"  label="按钮点击事件"/>
									<form:option value="3"  label="新用户关注事件"/>
								</form:select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">事件Code:
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name="eventCode" id="eventCode" value="${eventCode.eventCode}"/>
	                             <form:hidden path="eventCode.id"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">回复类型:
							</label>
							<div class="col-sm-2">
								<form:select path="eventCode.displayType" class="form-control">
									<form:option value="1"  label="文本回复"/>
									<form:option value="2"  label="数据模块"/>
								</form:select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">回复固定文本:
							</label>
							<div class="col-sm-6">
								<textarea name="replyContent" rows="3" cols="5" class="elastic form-control" placeholder="上限200字">${eventCode.replyContent}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">数据模块描述:
							</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="moduleDesc" id="moduleDesc" value="${eventCode.moduleDesc}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">关联数据模块:
							</label>
							<div class="col-sm-10">
								<%
                               	List<WxCodeModule> codeModuleList = (List<WxCodeModule>)request.getAttribute("codeModuleList");
                               	
                               	if(codeModuleList!=null&&codeModuleList.size()>0){
                               	%>
                               	<div class="block-inner">
                               	<%
                               	for(WxCodeModule codeModule: codeModuleList){
                               	%>
                               		<label class="radio-inline radio-info">
										<input class="styled" type="radio" name="moduleId" id="moduleId_<%=codeModule.getId()%>" value="<%=codeModule.getId()%>" <%=codeModule.getId().equals(eventCode.getModuleId())?"checked='checked'":""%>/>
										<%=codeModule.getModuleName()%>
									</label>
                               	<%}%>
                               	</div> 
                               	<%}%>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">微信展示条数: <span class="mandatory">*</span></label>
							<div class="col-sm-1">
								<input type="text" class="form-control" name="rowLimit" id="rowLimit" value="${eventCode.rowLimit}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">状 态:
							</label>
							<div class="col-sm-4">
								<form:select path="eventCode.status" class="select-liquid">
									<form:option value="0"  label="禁用"/>
									<form:option value="1"  label="启用"/>
								</form:select>
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
