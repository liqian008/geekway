<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>

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
<body class="full-width">
 
	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">
			
			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>选择单图文素材
					</h5>
				</div>
				<div class="table-responsive">
						<table class="table table-bordered table-striped table-check">
						<thead>
							<tr>
								<th>序号</th>
                                <th>类型</th>
                                <th>封面</th>
                                <th>内容</th>
                                <th>预览</th>
                                <th>状态</th>
                                <th class="team-links">操作</th> 
							</tr>
						</thead>
						<tbody>
							<%
                           	List<WxMaterialArticle> materialArticleList = (List<WxMaterialArticle>)request.getAttribute("materialArticleList");
                           	if(materialArticleList!=null&&materialArticleList.size()>0){
                           		int i=0;
                           		for(WxMaterialArticle materialArticle: materialArticleList){
                           			i++;
                           	%>
							<tr>
								<td><%=i%></td>
								<td>单图文</td>
								<td><a href="<%=materialArticle.getCoverImageUrl()%>"
									class="lightbox"> <img
										src='<%=materialArticle.getCoverImageUrl()%>'
										class="img-media" />
								</a></td>
								<td><%=materialArticle.getShortContent()%></td>
								<td>
									<%
										String meterialLink = ProductUtil.getArticleLink(materialArticle.getId());
									%>
									<a href="<%=meterialLink%>" target="_blank">预览</a>
								</td>
								<td>正常</td>
								<td class='text-center'>
									<div class="table-controls">
										<a
											href="javascript:pick(<%=materialArticle.getId()%>, '<%=materialArticle.getShortContent()%>')"
											class="material-picker btn btn-link btn-icon btn-xs tip"
											title="" data-original-title="选择"><i class="icon-heart2"></i></a>
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
		<!-- /page content -->
	</div>
	<!-- /page container -->
</body>


<%
Integer operation = (Integer)request.getAttribute("operation");
%>
<script>
function pick(materialId, materialRemark){
	<%
	if(operation==null){%>
	
	<%}else if(operation==0){//关联
	%>
		if(confirm('确定要选择【'+materialRemark+'】的单图文素材吗？')){
			$('#materialType', parent.document).val(1);//图文类型
			$('#materialId', parent.document).val(materialId);
			$('#materialTypeDesc', parent.document).text("单图文素材");
			$('#remark', parent.document).text("单图文素材名称: "+materialRemark);
			
			$('#replyContentContainer', parent.document).hide();
			$('#remarkContainer', parent.document).show();
			
			//操作iframe的父元素
			$('#materialModal', parent.document).modal('hide');
		}
	<%}else if(operation==1){//回复客服消息
		String openId = (String)request.getAttribute("openId");
		%>
		if(confirm('确定要使用【'+materialRemark+'】的单图文素材作为消息回复吗？')){
			//ajax post
			var replyData = {"materialId": materialId, 'openId': '<%=openId%>'};
			$.post("${pageContext.request.contextPath}/geekway/mpReplyArticle.json", replyData, function(responseData) {
    			var result = responseData.result;
   				if(result==1){
   					alert("客服消息回复成功，正在返回对话消息列表...");
   				}else{
   					alert("客服消息回复失败，正在返回对话消息列表...");
   				}
   				parent.location.reload();
    		 }, "json"); 
			
		}
	<%}else if(operation==2){//系统群发消息
		%>
		if(confirm('确定要群发单图文消息【'+materialRemark+'】吗？')){
			//ajax post
			var broadcastData = {"materialId": materialId};
			$.post("${pageContext.request.contextPath}/geekway/mpBroadcastArticle.json", broadcastData, function(responseData) {
    			var result = responseData.result;
   				if(result==1){
   					alert("单图文消息群发成功，正在返回对话群发列表页面...");
   				}else{
   					alert("单图文消息群发失败，正在返回对话群发列表页面...");
   	   			}
   				parent.location.reload();
    		 }, "json");
			
		}
	<%}%>
}
</script>

</html>

