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
<title>可乐惠管理平台</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/charts/flot.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/charts/flot.pie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/charts/flot.orderbars.js"></script>
<!-- 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/charts/full/vertical_bars.js"></script>
-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/charts/full/pie.js"></script>
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
						投票结果
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
					<li class="active">投票结果</li>
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

			<div class="row">
				<div class="col-md-6"> 
					<!-- Vertical bars -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h6 class="panel-title">
								<i class="icon-calendar2"></i>柱状图 
							</h6>
						</div>
						<div class="panel-body">
							<div class="graph-standard" id="vertical_bars">
								
							</div>
						</div>
					</div>
					<!-- /vertical bars -->
				</div>
				
				<div class="col-md-6">
					<!-- Pie --> 
					<div class="panel panel-default"> 
					<div class="panel-heading">
						<h6 class="panel-title"><i class="icon-calendar2"></i>饼状图</h6>
					</div> 
					<div class="panel-body"> 
					<div class="graph-standard" id="pie"></div> 
					</div> </div> <!-- /pie --> 
				</div>
			</div>

			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
	
	<script>
	$(function () {
	    var previousPoint;
	 
	    var d1 = [];
	    for (var i = 0; i <= 10; i += 1)
	        d1.push([i, parseInt(Math.random() * 30)]);
	 
	    var ds = new Array();
	 
		ds.push({
	        data:d1,
	        bars: {
	            show: true, 
	            barWidth: 0.2, 
	            order: 1,
	        }
	    });
	                
	    //tooltip function
	    function showTooltip(x, y, contents, areAbsoluteXY) {
	        var rootElt = 'body';
	    
	        $('<div id="tooltip" class="chart-tooltip">' + contents + '</div>').css( {
	            top: y - 50,
	            left: x - 6,
	            opacity: 0.9
	        }).prependTo(rootElt).show();
	    };
	                
	    //Display graph
	    $.plot($("#vertical_bars"), ds, {
	        colors: ["#6db6ee", "#95c832", "#993eb7",  "#3ba3aa", "#ee7951"], 
	        grid:{
	            hoverable:true
	        }
	    });

	 
		//add tooltip event
		$("#vertical_bars").bind("plothover", function (event, pos, item) {
		    if (item) {
		        if (previousPoint != item.datapoint) {
		            previousPoint = item.datapoint;
		 
		            //delete de prГ©cГ©dente tooltip
		            $('.chart-tooltip').remove();
		 
		            var x = item.datapoint[0];
		 
		            //All the bars concerning a same x value must display a tooltip with this value and not the shifted value
		            if(item.series.bars.order){
		                for(var i=0; i < item.series.data.length; i++){
		                    if(item.series.data[i][3] == item.datapoint[0])
		                        x = item.series.data[i][0];
		                }
		            }
		 
		            var y = item.datapoint[1];
		 
		            showTooltip(item.pageX+5, item.pageY+5,x + " = " + y);
		 
		        }
		    }
		    else {
		        $('.chart-tooltip').remove();
		        previousPoint = null;
		    }
		});
	});
	
	
	//pie chart
	$(function () {
		var data = [];
		var series = Math.floor(Math.random()*10)+1;
		for( var i = 0; i<series; i++)
		{
			data[i] = { label: "Series"+(i+1), data: Math.floor(Math.random()*100)+1 }
		}
	
		$.plot($("#pie"), data, 
			{
				series: {
					pie: { 
						show: true,
						radius: 1,
						label: {
							show: true,
							radius: 2/3,
							formatter: function(label, series){
								return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
							},
							threshold: 0.1
						}
					}
				},
				legend: {
					show: false
				},
				colors: ["#ee7951", "#6db6ee", "#95c832", "#993eb7", "#3ba3aa"]
			});
	});
	
	
	
	</script>
</body>
</html>
