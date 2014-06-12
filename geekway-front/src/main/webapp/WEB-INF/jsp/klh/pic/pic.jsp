<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.klh.*" %>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>可乐惠</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
		<!-- 
		<script type="text/javascript" src="js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="js/jquery.easy-pie-chart.js"></script>
		<script type="text/javascript" src="js/o-script.js"></script>
		 -->

	</head>
	<body class="o-page">
		<div id="page">
			<!-- 
			<div id="header">
				<a href="#menu"></a>
				<span id="Logo" class="svg">
					<img src="img/logo.svg" />
				</span>
				<a class="backBtn" href="javascript:history.back();"></a>
			</div>
			-->
			<div id="header">
				可乐惠上传照片
				<!-- <a class="backBtn" href="javascript:history.back();"></a> -->
			</div>
			<div id="content">
				<article id="imgContainer" style="display:none">
					<img src="" id="imgView"/><br/>
				</article>
				<input type="file" id="imgUpload" name="imgUpload" value="选择照片">
				<input type="button" id="uploadBtn" name="uploadBtn" value="上传">
			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
	
<script type="text/javascript">
	$(document).ready(function(){
	    $("#uploadBtn").click(function(){
	    	alert(123);
	        //创建FormData对象
	        var data = new FormData();
	        //为FormData对象添加数据 
	        data.append('imageFile', $('input[type=file]')[0].files[0]);  
	        $.ajax({
	            url:'/geekway-front/klh/imageUpload',
	            type:'POST',
	            data:data,
	            cache: false,
	            contentType: false,    //不可缺
	            processData: false,    //不可缺
	            success:function(responseData){
	                if(responseData.result==1){
	                	var imageUrl = responseData.data.originalImage.url;
						$("#imgContainer").show();
	                	$('#imgView').attr("src", imageUrl);
	                }else{
	                	alert(responseData.message);
	                }
	            }
	        });
	    });
	});
	</script>
</html>