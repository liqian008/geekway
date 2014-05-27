<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>
<%@page import="java.text.*"%>

<%@ include file="../inc/include_tag.jsp" %>

<%
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>可乐惠管理平台</title>
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
						投票详情
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
					<li><a href="javascript:void(0)">首页</a></li>
					<li class="active">投票详情</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->
			
			<div class="callout callout-info fade in">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h5>功能介绍</h5>
				<p>
					1、xxxxxxxxxx<br/>
				</p>
			</div>

			<%
			KlhVote vote = (KlhVote)request.getAttribute("vote");
			%>

			<form id="validate" action="<s:url value='./saveVote'/>" method="post"  class="form-horizontal form-bvoteed">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>投票详情
						</h6>
					</div>
					<div class="panel-body">
						
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">起始时间: <span class="mandatory">*</span></label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name="startTime" id="startTime" value="${vote.startTime}" placeholder="格式为: 2014-01-01 23:59:59"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">结束时间: <span class="mandatory">*</span></label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name="endTime" id="endTime" value="${vote.endTime}" placeholder="格式为: 2014-01-01 23:59:59"/>
							</div> 
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">投票标题: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="title" id="title" value="${vote.title}"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">投票描述: <span class="mandatory">*</span></label>
							<div class="col-sm-8"> 
								<div class="block-inner">
									<textarea name="description" rows="2" cols="5" class="elastic form-control" placeholder="上限100字">${vote.description}</textarea>
								</div>
							</div>
						</div> 
						
						<div class="form-actions text-right">
							<input type="reset" value="重 置" class="btn btn-danger">
							<input type="submit" value="确 认" class="btn btn-primary">
						</div>
					</div>
					
				</div>
			</form>
			
			<!-- Table view -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title">
						<i class="icon-people"></i>投票选项列表
					</h5>
					<a href="./voteOptionAdd?voteId=${vote.id}"><span class="label label-info pull-right">添加投票选项</span></a>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-check">
						<thead>
							<tr>
								<th>ID</th>
                                <th>图片</th>
                                <th>标题</th>
                                <th>排序</th>
                                <th class="team-links">操作</th>
							</tr>
						</thead>
						<tbody>
							<%
                           	List<KlhVoteOption> voteOptionList = (List<KlhVoteOption>)request.getAttribute("voteOptionList");
                           	if(voteOptionList!=null&&voteOptionList.size()>0){
                           		int i=0;
                           		for(KlhVoteOption voteOption: voteOptionList){
                           			i++;
                           	%>
							<tr>
		                        <td><%=voteOption.getId()%></td>
		                        <td>
	                        		<a href="<%=voteOption.getPicUrl()%>" class="lightbox">
		                        	<img src='<%=voteOption.getThumbPicUrl()%>' class="img-media"/>
		                        	</a>
		                        </td>
		                        <td><%=voteOption.getTitle()%></td>
		                        <td><%=voteOption.getSort()%></td>
		                        <td class='text-center'>
		                        	<div class="table-controls">
		                        		<a href="./voteOptionEdit?voteOptionId=<%=voteOption.getId()%>"  
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="编 辑"><i class="icon-pencil3"></i></a>
		                        		<a href="./delVoteOption?voteId=${vote.id}&voteOptionId=<%=voteOption.getId()%>"  
											class="btn btn-link btn-icon btn-xs tip" title=""
											data-original-title="删 除"><i class="icon-remove3"></i></a>
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
			
			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
	
	<script type="text/javascript">
	$(document).ready(function(){
	    $("#cover-image-file").change(function(){
	        //创建FormData对象
	        var data = new FormData();
	        //为FormData对象添加数据 
	        data.append('imageFile', $('input[type=file]')[0].files[0]);  
	        $.ajax({
	            url:'/geekway-admin/geekway/imageUpload',
	            type:'POST',
	            data:data,
	            cache: false,
	            contentType: false,    //不可缺
	            processData: false,    //不可缺
	            success:function(responseData){
	                if(responseData.result==1){
	                	var imageUrl = responseData.data.originalImage.url;
		                $('#cover-image').attr("src", imageUrl);
		                $('#cover-image-link').attr("href", imageUrl);
		                $('#cover-image-url').val(imageUrl);
	                }else{
	                	alert(responseData.message);
	                }
	            }
	        });
	    });
	});
	</script>
	
</body>
</html>
