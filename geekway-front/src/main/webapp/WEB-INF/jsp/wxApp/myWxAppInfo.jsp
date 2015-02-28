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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js-sdk/css/main.css" type="text/css" id="main-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js-sdk/css/colors/aqua.css" type="text/css" id="main-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js-sdk/home-2.html#" id="boxed">

    <!-- Javascript Files
    ================================================== -->
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.isotope.min.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.prettyPhoto.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/easing.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.ui.totop.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/selectnav.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/ender.js"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.lazyload.js"></script>
    <script src="${pageContext.request.contextPath}／rs-plugin/js/jquery.themepunch.revolution.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js-sdk/js/jquery.flexslider-min.js"></script>
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
                        <h1>查看微信应用</h1>
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
						<h4>查看我的微信应用</h4>
						<div class="alert alert-success">
							步骤2：按照要求，将文本框中的js代码粘贴到您的微信H5页面中，即可在微信中进行分享等操作了
						</div>
						
						<div id="comment-form-wrapper">

							<div class="comment_form_holder">
								<form id="commentform" name="form1" method="post"
									action="blog-single.html#">

									<label>微信应用名称 <span class="req">*</span></label> 
									<input type="text" name="name" id="name" value="${myWxApp.name}" disabled="disabled"> 
									
									<label>微信AppId<span class="req">*</span></label>
									<input type="text" name="wxAppId" id="wxAppId" value="${myWxApp.wxAppId}" readonly="readonly">

									<label>微信Secret <span class="req">*</span></label>
									<input type="text" name="wxAppSecret" id="wxAppSecret" value="${myWxApp.wxAppSecret}" readonly="readonly" >

									<div class="alert alert-info">
										集成方法：将下方对话框中的代码粘贴到您的H5页面中，即可完成微信JS-SDK的集成。完整页面代码可点击“Demo”按钮进行查看</div>
									<textarea cols="10" rows="12" name="message" id="message" readonly="readonly">
请在您都页面中引用以下JS代码：
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
(function() {
  var jssdkEle = document.createElement("script");
  jssdkEle.type = "text/javascript";
  var currentUrl = encodeURIComponent(location.href.split('#')[0]);;
  jssdkEle.src = "http://api.js-sdk.com.cn/api/wxJsConfigSrc?pageUrl="+currentUrl;
  var allJsEles = document.getElementsByTagName("script")[0]; 
  allJsEles.parentNode.insertBefore(jssdkEle, allJsEles);
})();
</script>
									</textarea>

									<p>
										<button class="btn btn-success" type="button">保 存</button>
										<button class="btn btn-info" type="reset">重 置</button>
										<button class="btn btn-inverse" type="button" id="delAppBtn">删除应用</button>
										<button class="btn btn-warning" type="button" id="demoBtn">查看完整Demo</button>
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
	$("#demoBtn").click(function(){
		//location.href="./wxJsDemo";
		window.open('./wxJsDemo');
	});
	
	$("#delAppBtn").click(function(){
		if(confirm("删除后将无法恢复，且微信H5上的JS分享等功能将全部失效，确定删除吗？")){
			if(confirm("点击确定后开始删除")){
				location.href="./delMyWxApp?id=${myWxApp.id}";
			}
		}
	})
	
});
</script>
</html>
