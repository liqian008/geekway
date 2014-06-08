<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>

<%@ include file="../inc/include_tag.jsp" %>

<%!String displayCommandType(short commandType){
	if(1==commandType){
		return "菜单关键词";
	}else if(0==commandType){
		return "文本关键词";
	}
	return "其他关键词";
} %>


<%!String displayMaterialType(short materialType){
	if(1==materialType){
		return "图文素材";
	}
	return "文本素材";
} %>



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
						关键词内容
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
					<li class="active">关键词内容</li>
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
					1、管理员可修改关键词的名称，以便于调整关键词。<br/>
				</p>
			</div>
			
			<%
			WxCommand command = (WxCommand)request.getAttribute("command");
			%>

			<form id="validate" action="<s:url value='./saveCommand'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>编辑关键词内容
						</h6>
					</div>
					<div class="panel-body">
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">关键词类型:
							</label>
							<div class="col-sm-3">
								<%
								short commandType = (short)1;
								if(command!=null&&command.getId()!=null){//<!-- 编辑状态下 -->
									commandType = command.getCommandType();
								}else{//<!-- 新增状态下 -->
									commandType = (Short)request.getAttribute("commandType");
								}%>
								<label class="control-label">
									<%=displayCommandType(commandType) %>
								</label>
								<input type="hidden" class="form-control" name="commandType" id="commandType" value="${commandType}"/>
			                    <form:hidden path="command.id"/>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">关键词名称:
							</label>
							<div class="col-sm-3">
								<%if(command!=null&&command.getCommandType()<2) {//文本接入&菜单接入%>
									<input type="text" class="form-control" name="command" id="command" value="${command.command}"/>
								<%}else{ %>
									<label class="control-label">
										${command.command}
									</label>
								<%} %>
							</div>
						</div>
						
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">状 态:
							</label>
							<div class="col-sm-4">
								<form:select path="command.status" class="select-liquid">
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
			
			<%if(command!=null&&command.getCommandType()!=3) {%>
			
			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>已匹配的素材
					</h5>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-check">
						<thead>
							<tr>
								<th>ID</th>
                                <th>类型</th>
                                <th>封面</th>
                                <th>内容</th>
                                <th class="team-links">操作</th>
							</tr>
						</thead>
						<tbody>
							<%
                           	List<WxMaterialArticle> materialList = (List<WxMaterialArticle>)request.getAttribute("materialList");
                           	if(materialList!=null&&materialList.size()>0){
                           		int i=0;
                           		for(WxMaterialArticle material: materialList){
                           			i++;
                           	%>
							<tr>
		                        <td><%=material.getId()%></td>
		                        <td><%=displayMaterialType(material.getMaterialType())%></td>
		                       	<td>
		                        	<%if(material.getMaterialType()==1){%>
	                        		<a href="<%=material.getCoverImageUrl()%>" class="lightbox">
		                        	<img src='<%=material.getCoverImageUrl()%>' class="img-media"/>
		                        	</a>
		                        	<%}else{ %>
		                        		无
		                        	<%} %>
		                        </td>
		                        <td>
		                        	<%=material.getMaterialType()==1?material.getShortContent():material.getTextReply()%>
		                        </td>
		                        <td class='text-center'>
		                        	<div class="table-controls">
		                        		<a href="./materialArticleEdit?articleId=<%=material.getId()%>"  
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="修改素材"><i class="icon-pencil3"></i></a>
		                        		<a href="javascript:void(0)"  
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="解除匹配"><i class="icon-remove3"></i></a>
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
			<%}%>
			

			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
</body>
</html>
