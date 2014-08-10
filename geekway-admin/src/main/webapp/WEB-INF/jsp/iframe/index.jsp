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
			
			<form id="textForm" role="form" action="#">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                <h6 class="panel-title"><i class="icon-page-break"></i>输入文本内容</h6></div>
	                <div class="panel-body">
				        <div class="form-group">
				            <!-- <label>文本内容:</label> -->
			            	<textarea id="text" rows="3" cols="5" name="message"
									placeholder="请输入文本内容..."
									class="elastic form-control required"></textarea>
				        </div>
				    </div>
				</div>
			</form>
			
		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
</body>


<%
Integer operation = (Integer)request.getAttribute("operation");
%>
<script>
$("#submit").click(function(){
	var text = $("#text").val();
	<%
	if(operation==null){%>
	
	<%}else if(operation==0){//关联
	%>
		if(confirm('确定要使用文本【'+text+'】的作为回复内容吗？')){
			$('#materialType', parent.document).val(0);//图文类型
			$('#materialTypeDesc', parent.document).text("文本回复");
			$('#remark', parent.document).text("文本回复: "+text);
			
			$('#replyContent', parent.document).val(text);
			$('#replyContentContainer', parent.document).show();
			
			$('#remarkContainer', parent.document).hide();
			
			//操作iframe的父元素
			$('#materialModal', parent.document).modal('hide');
		}
	<%}else if(operation==1){//回复客服消息
		String openId = (String)request.getAttribute("openId");
		%>
		if(confirm('确定要使用【'+text+'】的文本内容作为消息回复吗？')){
			//ajax post
			var replyData = {"text": text, 'openId': '<%=openId%>'};
			$.post("${pageContext.request.contextPath}/geekway/mpReplyText.json", replyData, function(responseData) {
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
		if(confirm('确定要群发文本消息【'+text+'】吗？')){
			//ajax post
			var broadcastData = {"content": text};
			$.post("${pageContext.request.contextPath}/geekway/mpBroadcastText.json", broadcastData, function(responseData) {
    			var result = responseData.result;
   				if(result==1){
   					alert("文本消息群发成功，正在返回对话群发列表页面...");
   				}else{
   					alert("文本消息群发失败，正在返回对话群发列表页面...");
   	   			}
   				parent.location.reload();
    		 }, "json");
			
		}
	<%}%>
})

$("#reset").click(function(){
	$("#textForm")[0].reset();
});
</script>

</html>

