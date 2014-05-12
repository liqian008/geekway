<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>

<%@ include file="../inc/include_tag.jsp" %>

<%!String displayCommandType(short commandType){
	if(1==commandType){
		return "文本请求指令";
	}else if(2==commandType){
		return "菜单点击指令";
	}else if(3==commandType){
		return "新用户关注指令";
	}
	return "类型错误";
} %>

<%!String displayMaterialType(Short materialType){
	if(materialType!=null){
		if(1==materialType){ 
			return "文本";
		}else if(2==materialType){
			return "单图文";
		}else if(3==materialType){
			return "多图文";
		}
	}
	return "其他类型";
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
						指令内容
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
					<li class="active">指令内容</li>
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
					1、回复类型目前支持图文素材（均需事先创建相应的素材以做关联） <br/>
					2、 <br/>
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
							<i class="icon-bubble4"></i>编辑指令内容
						</h6>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">接入指令类型:
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
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">接入指令:
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name="command" id="command" value="${command.command}"/>
	                            <form:hidden path="command.id"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">回复类型:
							</label>
							<div class="col-sm-2">
							<%
							if(command==null||command.getId()==null){//<!-- 新增状态下 -->
							%>
								<form:select path="command.materialType" class="form-control">
									<form:option value="1"  label="文本回复"/>
									<form:option value="2"  label="单图文素材"/>
									<form:option value="3"  label="多图文素材"/>
								</form:select>
							<%}else{%>
								<label class="control-label">
									<%=displayMaterialType(command.getMaterialType()) %>
								</label>
							<%}%>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">内 容:
							</label>
							<div class="col-sm-2">
								<label class="control-label">
									<%
									WxMaterialArticle materialArticle = (WxMaterialArticle)request.getAttribute("materialArticle");
									if(materialArticle!=null){
									%>
									<%=materialArticle.getShortTitle()%>
									<%}%>
									
									<%
									WxMaterialNews materialNews = (WxMaterialNews)request.getAttribute("materialNews");
									if(materialNews!=null){
									%>
									<%=materialNews.getName()%>
									<%}%>
									&nbsp;<a data-toggle="modal" role="button" href="#table_modal">查看</a>
								</label>
							</div>
						</div>
						
						<%
						if(command!=null&&command.getId()!=null){//<!-- 编辑状态下 -->
							if(command.getMaterialType()==1){//图文方式，可以文本回复
						%>
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">文本回复:
							</label>
							<div class="col-sm-6">
								<textarea name="replyContent" rows="3" cols="5" class="elastic form-control" placeholder="上限100字">${command.replyContent}</textarea>
							</div>
						</div> 
						<%}
						}%>
						
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


			<!-- Modal with table -->
			<div id="table_modal" class="modal fade" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">
								<i class="icon-accessibility"></i>素材列表
							</h4>
						</div>
						<div class="modal-body with-padding">
							<div class="panel panel-default">
								<table class="table table-bordered table-striped datatable-selectable">
									<thead>
										<tr>
											<th>选择</th>
											<th>封面</th>
											<th>短标题</th>
										</tr>
									</thead>
									<tbody>
										<%
										List<WxMaterialArticle> materialArticleList = (List<WxMaterialArticle>)request.getAttribute("materialArticleList");
										if(materialArticleList!=null&&materialArticleList.size()>0){
											for(WxMaterialArticle article: materialArticleList){
										%>
										<tr>
											<td>
												<input type="radio" class="styled" name="mapArticleId">
											</td>
											<td>
												<a href="<%=article.getCoverImageUrl()%>" class="lightbox">
					                        	<img src='<%=article.getCoverImageUrl()%>' class="img-media"/>
					                        	</a>
											</td>
											<td><%=article.getShortTitle()%></td>
										</tr>
										<%}
										}%>
									</tbody>
								</table>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn btn-warning" data-dismiss="modal">
								<i class="icon-cancel-circle"></i> 关 闭
							</button>
							<button class="btn btn-primary">
								<i class="icon-checkmark-circle"></i> 确 定
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- /modal with table -->


			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
</body>
</html>
