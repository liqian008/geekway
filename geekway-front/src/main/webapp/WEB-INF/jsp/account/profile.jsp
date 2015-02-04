<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>微信JS-SDK</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Responsive Minimal Bootstrap Theme">
<meta name="keywords" content="responsive,minimal,bootstrap,theme">
<meta name="author" content="">

<!--[if lt IE 9]>
	<script src="${pageContext.request.contextPath}/js-sdk/js/html5shiv.js"></script>
    <link rel="stylesheet" href="css/ie.css" type="text/css">
	<![endif]-->

<!-- CSS Files
    ================================================== -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js-sdk/css/main.css"
	type="text/css" id="main-css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js-sdk/css/colors/aqua.css"
	type="text/css" id="main-css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js-sdk/home-2.html#"
	id="boxed">

<!-- Javascript Files
    ================================================== -->
<script src="${pageContext.request.contextPath}/js-sdk/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js-sdk/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js-sdk/js/jquery.isotope.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js-sdk/js/jquery.prettyPhoto.js"></script>
<script src="${pageContext.request.contextPath}/js-sdk/js/easing.js"></script>
<script
	src="${pageContext.request.contextPath}/js-sdk/js/jquery.ui.totop.js"></script>
<script src="${pageContext.request.contextPath}/js-sdk/js/selectnav.js"></script>
<script src="${pageContext.request.contextPath}/js-sdk/js/ender.js"></script>
<script
	src="${pageContext.request.contextPath}/js-sdk/js/jquery.lazyload.js"></script>
<script
	src="${pageContext.request.contextPath}／rs-plugin/js/jquery.themepunch.revolution.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js-sdk/js/jquery.flexslider-min.js"></script>
<script src="${pageContext.request.contextPath}/js-sdk/js/custom.js"></script>

</head>

<body>
	<div id="wrapper">
		<!-- header begin -->
		<jsp:include page="../inc/header.jsp"></jsp:include>
		<!-- header close -->

		<!-- subheader begin -->
		<div id="subheader">
			<div class="container">
				<div class="row">
					<div class="span12">
						<h1>我的账户</h1>
						<span>Some Cool Page Description.</span>
						<ul class="crumb">
							<li><a href="index.html">Home</a></li>
							<li class="sep">/</li>
							<li>Blog Right Sidebar</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- subheader close -->

		<div id="content">
			<div class="container">
				<div class="row">

					<jsp:include page="../inc/sidebar.jsp"></jsp:include>

					<div class="span8">
						<h4>查看我的账户</h4>
						<div id="comment-form-wrapper">

							<div class="comment_form_holder">
								<form id="commentform" name="form1" method="post"
									action="blog-single.html#">

									<label>邮箱 <span class="req">*</span></label> <input type="text"
										name="email" id="email" value="${_currentUser.email}"
										disabled="disabled"> <label>手机号 <span
										class="req">*</span></label> <input type="text" name="mobile"
										id="mobile" value="${_currentUser.mobile}" disabled="disabled">

									<p>
										<button class="btn btn-success" type="button">修改密码</button>
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../inc/footer.jsp"></jsp:include>
	</div>

</body>

<script>
$(document).ready(function(){
	$("#loginBtn").click(function(){
		$("#commentform").submit();
	});
});
</script>
</html>
