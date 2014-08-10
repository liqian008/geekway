<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.geekway.model.WxBroadcast"%>
<%@page import="java.text.SimpleDateFormat"%>

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
						群发消息管理
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
					<li class="active">群发消息管理</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->
			
			<%
			Boolean isSenior = (Boolean)request.getAttribute("isSenior");
			%>
			
			<div class="callout callout-info fade in">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h5>功能介绍：</h5>
				<p>
					1、该接口暂时仅提供给已微信认证的服务号.<br/>
					2、无论在公众平台网站上，还是使用接口群发，用户每月只能接收4条群发消息，多于4条的群发将对该用户发送失败。<br/>
					3、管理后台支持3种类型的群发广播： 文本广播 / 图文广播 / 图片广播。
				</p>
			</div>

			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>群发消息管理
					</h5>
					<a id="imageMaterial" class="modal-trigger" href="javascript:void(0)"><span class="label label-primary pull-right">群发图片消息</span></a>
					<a id="newsMaterial"  class="modal-trigger" href="javascript:void(0)"><span class="label label-danger pull-right">群发多图文消息</span></a>
					<a id="articleMaterial"  class="modal-trigger" href="javascript:void(0)"><span class="label label-info pull-right">群发单图文消息</span></a>
					<a id="textMaterial"  class="modal-trigger" href="javascript:void(0)"><span class="label label-success pull-right">群发文本消息</span></a>
				</div>
				<div class="datatable-media">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>序号</th>
								<th>消息类型</th>
                                <th>群发状态</th>
                                <th>发送数</th>
                                <th>成功</th>
                                <th>失败</th>
                                <th class="team-links">操 作</th> 
							</tr>
						</thead>
						<tbody>
							<%
                           	List<WxBroadcast> broadcastList = (List<WxBroadcast>)request.getAttribute("broadcastList");
                           	if(broadcastList!=null&&broadcastList.size()>0){
                           		int i=0;
                           		for(WxBroadcast broadcast: broadcastList){
                           			i++;
                           	%>
							<tr>
		                        <td><%=i%></td>
		                        <td><%=broadcast.getMessageType()%></td>
		                        <td><%=broadcast.getStatus()%></td>
		                        <td><%=broadcast.getFilterCount()%>人</td>
		                        <td><%=broadcast.getSentCount()%>人</td>
		                        <td><%=broadcast.getErrorCount()%>人</td>
		                        <td class='text-center'>
		                        	<div class="table-controls">
										<a href="./delBroadcast?broadcastId=<%=broadcast.getId()%>"
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="取 消"><i class="icon-remove3"></i></a>
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
			
			<!-- <div class="callout callout-danger fade in">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h5>抱歉：</h5>
				<p> 
					1、该接口暂时仅提供给已微信认证的服务号，您可以访问<a href="http://mp.weixin.qq.com/" target="_blank">微信公众平台系统</a>，使用其中的群发功能。<br/>
				</p>
			</div> -->
			
			
			<!-- Modal -->
			<div id="materialModal" class="modal fade" tabindex="-1" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="modalTitle"><i class="icon-accessibility"></i>请选择素材</h4>
						</div>
						<div class="modal-body with-padding">
							<iframe id="materialIframe" src="./modalTest" width="100%" height="360px" frameborder="no" border="0" allowtransparency="yes"></iframe>
							
						</div>
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
int operation = 2;//匹配command
%>
$(".modal-trigger").click(function(){
	var materialUrl = "";
	var modalTitle = "请选择素材";
	if(this.id=='textMaterial'){
		modalTitle = "请输入文本内容";	
		$("#materialIframe").attr("height", "240px");
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

