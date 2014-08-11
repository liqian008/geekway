<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>

<%@ include file="../inc/include_tag.jsp" %>

<%!String displayCommandType(short commandType){
	if(1==commandType){
		return "菜单配置关键词";
	}else if(0==commandType){
		return "文本输入关键词";
	}
	return "其他关键词";
} %>


<%!String displayMaterialType(Short materialType){
	if(materialType!=null){
		if(0==materialType){
			return "文本素材";
		}else if(1==materialType){
			return "单图文素材";
		}else if(2==materialType){
			return "多图文素材";
		}else if(3==materialType){
			return "图片素材";
		}else if(4==materialType){
			return "语音素材";
		}
	}
	return "未选择";
} %>



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
					1、管理员可修改关键词的名称，以便于调整返回的数据。<br/>
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
							<label class="col-sm-2 control-label text-right">关键词类型: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
							<form:select path="command.commandType" class="select-liquid">
								<form:option value="0"  label="文本输入关键字"/>
								<form:option value="1"  label="菜单配置关键字"/>
							</form:select>
							<form:hidden path="command.id"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">关键词:
							</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name="command" id="command" value="${command.command}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">素材类型:
							</label>
							<div class="col-sm-10">
								<label class="control-label">
									<span id="materialTypeDesc"><%=displayMaterialType(command.getMaterialType()) %></span>&nbsp;
									<form:hidden path="command.materialType"/>
									<form:hidden path="command.materialId"/> 
								</label>
							</div>
						</div>
						
						<div id="replyContentContainer" class="form-group" <%=command.getMaterialType()!=null&&command.getMaterialType()==0?"'":"style='display:none'"%>>
							<label class="col-sm-2 control-label text-right">文本回复内容:
							</label>
							<div class="col-sm-8">
								<textarea id="replyContent" name="replyContent" rows="3" cols="5" class="form-control" placeholder="上限100字">${command.replyContent}</textarea>
							</div> 
						</div>
						
						<div id="remarkContainer" class="form-group" <%=command.getMaterialType()!=null&&command.getMaterialType()==0?"style='display:none'":""%>>
							<label class="col-sm-2 control-label text-right">备注:
							</label>
							<div class="col-sm-8">
								<textarea id="remark" name="remark" rows="3" cols="5" class="elastic form-control" placeholder="上限100字">${command.remark}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">变更素材内容:
							</label>
							<div class="col-sm-10">
								<label class="control-label">
									<a href="javascript:void(0)" id="textMaterial" class="modal-trigger">
									<span class="label label-success">文本素材</span>
									</a>
									
									<a href="javascript:void(0)" id="articleMaterial" class="modal-trigger">
									<span class="label label-info">单图文素材</span>
									</a>
									
									<a href="javascript:void(0)" id="newsMaterial" class="modal-trigger">
									<span class="label label-danger">多图文素材</span>
									</a>
									
									<a href="javascript:void(0)" id="imageMaterial" class="modal-trigger">
									<span class="label label-primary">图片素材</span>
									</a>
									
									<a href="javascript:void(0)" id="voiceMaterial" class="modal-trigger">
									<span class="label label-info">语音素材</span>
									</a>
								</label>
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
			
			<!-- Modal -->
			<div id="materialModal" class="modal fade" tabindex="-1" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="modalTitle"><i class="icon-accessibility"></i>请选择素材</h4>
						</div>
						<div class="modal-body with-padding">
							<!-- 
							<p>One fine body&hellip;</p>
							-->
							
							<iframe id="materialIframe" src="./modalTest" width="100%" height="360px" frameborder="no" border="0" allowtransparency="yes"></iframe>
							
						</div>
						<!-- 
						<div class="modal-footer">
							<button class="btn btn-warning" data-dismiss="modal">关 闭</button>
							<button class="btn btn-primary">确 认</button>
						</div>
						 -->
					</div>
				</div>
			</div>
			<!-- /modal -->
			
			<jsp:include page="../inc/footer.jsp"></jsp:include>
			
		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
</body>



<script>

<%
int operation = 0;//匹配command
%>
$(".modal-trigger").click(function(){
	var materialUrl = "";
	var modalTitle = "请选择素材";
	if(this.id=='textMaterial'){
		modalTitle = "请输入文本内容";	
		materialUrl = "./iframePickTextMaterial?operation=<%=operation%>";
	}else if(this.id=='articleMaterial'){
		modalTitle = "请选择单图文素材";
		materialUrl = "./iframePickArticleMaterial?operation=<%=operation%>";
	}else if(this.id=='newsMaterial'){
		modalTitle = "请选择多图文素材";
		materialUrl = "./iframePickNewsMaterial?operation=<%=operation%>";
	}else if(this.id=='imageMaterial'){
		modalTitle = "请选择图片素材";
		materialUrl = "./iframePickImageMaterial?operation=<%=operation%>";
	}else if(this.id=='voiceMaterial'){
		modalTitle = "请选择语音素材";
		materialUrl = "./iframePickVoiceMaterial?operation=<%=operation%>";
	}
	$("#modalTitle").text(modalTitle);
	$("#materialIframe").attr("src", materialUrl);
	$("#materialModal").modal();
})
</script>

</html>
