<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>
<%@page import="com.bruce.foundation.util.*"%>
<%@page import="com.bruce.foundation.admin.utils.*"%>
<%@page import="com.bruce.foundation.model.paging.*"%>


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
					<li><a href="${pageContext.request.contextPath}/home/index">首页</a></li>
					<li class="active">对话消息</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->

			<%
			WxUser chatUser = (WxUser)request.getAttribute("chatUser");
			String openId = chatUser.getOpenId();
			//String accountDefaultAvatar = (String)request.getAttribute("accountDefaultAvatar");
			%>
			
			
			<%
			//检查客服消息的时间间隔
			Boolean replyDenied = (Boolean)request.getAttribute("replyDenied");
			
			if(replyDenied!=null&&!replyDenied){
			%>
			
			<form id="validate" action="<s:url value='./mpReplyText'/>" method="post"  class="form-horizontal form-bordered">
				<input type="hidden" name="openId" value="<%=openId%>"/>
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">选择要回复的素材:
							</label>
							<div class="col-sm-10">
								<label class="control-label">
									<a href="javascript:void(0)" id="textMaterial" class="modal-trigger">
									<span class="label label-success">回复文字</span>
									</a>
									
									<a href="javascript:void(0)" id="articleMaterial" class="modal-trigger">
									<span class="label label-info">回复单图文</span>
									</a>
									
									<a href="javascript:void(0)" id="newsMaterial" class="modal-trigger">
									<span class="label label-danger">回复多图文</span>
									</a>
									<!-- 
									暂时不支持以下格式类型
									<a href="javascript:void(0)" id="imageMaterial" class="modal-trigger">
									<span class="label label-primary">回复图片</span>
									</a>
									
									<a href="javascript:void(0)" id="voiceMaterial" class="modal-trigger">
									<span class="label label-info">回复语音</span>
									</a>
									 -->
								</label> 
							</div>
						</div>
						<!-- 
						<div class="form-actions text-right">
							<input type="reset" value="重 置" class="btn btn-danger">
							<input type="submit" value="提 交" class="btn btn-primary">
						</div>
						 -->
					</div>
				</div>
			</form>
			<%}else{%>
				
			<%}%>
			
			
			<%
			PagingResult<WxHistoryMessage> pagingResult = (PagingResult<WxHistoryMessage>)request.getAttribute("historyMessagePagingData");
			List<WxHistoryMessage> userMessageList = pagingResult.getPageData();
			if(userMessageList!=null&&userMessageList.size()>0){
			%>
			<ul class="message-list">
				<li>
					<div class="panel-collapse collapse in" id="duke_aaron" style="height: auto;">
						
						<div class="chat">
						<%
						for(WxHistoryMessage userHistoryMessage: userMessageList){
							if(userHistoryMessage.getInbox()!=null&&userHistoryMessage.getInbox()==0){
						%> 	
							<div class="message">
								<a class="message-img" href="javascript:void(0)"><img src="${chatUser.headImgUrl}" alt=""></a>
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
								<a class="message-img" href="contacts.html#"><img src="${accountDefaultAvatar}" alt=""></a>
								<div class="message-body">
									<%=userHistoryMessage.getContent() %>
									<%if("news".equalsIgnoreCase(userHistoryMessage.getMsgType())){%>
		                        	<br/>
		                        	<a href="javascript:void(0)" class="lightbox">
		                        	<img src="<%=userHistoryMessage.getPicUrl()%>" height="80px"/>
		                        	</a>
		                        	<%}%>
									<span class="attribution">回复时间: <%=DateUtil.date2YMDHMS(userHistoryMessage.getCreateTime()) %> </span>
								</div>
							</div>
						<%}
						}%>
						</div>
					</div>
					
					<div class="datatable-footer">
					<%=PaginatorUtil.buildPageingHtml(pagingResult, 5)%>
					</div> 
				</li>
			</ul>
				
			<%}%>
			
			<!-- Modal with remote path -->
			<div id="materialModal" class="modal fade" tabindex="-1" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="modalTitle"><i class="icon-accessibility"></i>请选择要回复的素材</h4>
						</div>
						<div class="modal-body with-padding">
							<!-- 
							<p>One fine body&hellip;</p>
							-->
							
							<iframe id="materialIframe" src="./modalTest" width="100%" height="360px" frameborder="no" border="0" allowtransparency="yes"></iframe>
							
						</div>
					</div>
					
					
					
				</div>
			</div><!-- /modal with remote path -->
			
			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
	
</body>

<script>
<%
int operation = 1;//发送客服消息
%>
$(".modal-trigger").click(function(){
	var materialUrl = "";
	var modalTitle = "请选择要回复的素材";
	if(this.id=='textMaterial'){
		modalTitle = "请输入要回复的文本内容";	
		materialUrl = "./iframePickTextMaterial?operation=<%=operation%>&openId=<%=openId%>";
	}else if(this.id=='articleMaterial'){
		modalTitle = "请选择要回复的单图文";
		materialUrl = "./iframePickArticleMaterial?operation=<%=operation%>&openId=<%=openId%>";
	}else if(this.id=='newsMaterial'){
		modalTitle = "请选择要回复的多图文";
		materialUrl = "./iframePickNewsMaterial?operation=<%=operation%>&openId=<%=openId%>";
	}else if(this.id=='imageMaterial'){
		modalTitle = "请选择要回复的图片";
		materialUrl = "./iframePickImageMaterial?operation=<%=operation%>&openId=<%=openId%>";
	}else if(this.id=='voiceMaterial'){
		modalTitle = "请选择要回复的语音";
		materialUrl = "./iframePickVoiceMaterial?operation=<%=operation%>&openId=<%=openId%>";
	}
	$("#modalTitle").text(modalTitle);
	$("#materialIframe").attr("src", materialUrl);
	$("#materialModal").modal();
})
</script>
</html>
