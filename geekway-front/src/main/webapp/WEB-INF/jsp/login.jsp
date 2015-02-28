<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.constants.*" %>
<%@ page import="java.util.*" %>

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
        <jsp:include page="./inc/header.jsp"></jsp:include>
        <!-- header close -->

        <!-- subheader begin -->
        <div id="subheader">
            <div class="container">
                <div class="row">
                    <div class="span12">
                        <h1>用户登录</h1>
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

                    <jsp:include page="./inc/sidebar.jsp"></jsp:include>

                    <div class="span8">
                    	<%
                    	String redirectUrl = (String)request.getAttribute(ConstFront.REDIRECT_URL);
                     	if(redirectUrl!=null&&!"".equals(redirectUrl.trim())){
                    	%>
                        <h4>使用该功能需要先登录</h4> 
                        <%}else{ %>
                        <h4>用户登录</h4>
                        <%} %>
                        <div id="comment-form-wrapper">

                            <div class="alert alert-warning">
                                成功登录后可管理我的微信应用
                            </div>

                            <div class="comment_form_holder">
                                 <form id="commentform" name="form1" method="post" action="./login">
                                 	<%
                                 	if(redirectUrl!=null&&!"".equals(redirectUrl.trim())){
                                 	%>
									<input type="hidden" name="<%=ConstFront.REDIRECT_URL%>" id="<%=ConstFront.REDIRECT_URL%>" value="<%=redirectUrl%>">
                                    <%}%>
                                    <label>邮箱（登录用户名） <span class="req">*</span></label>
                                    <input type="text" name="email" id="email" width="80%">
                                    <div id="error_email" class="error">邮箱输入有误</div>

                                    <label>登录密码 <span class="req">*</span></label>
                                    <input type="password" name="password" id="password" width="80%">
                                    <div id="error_password" class="error">密码输入有误</div>

                                    <p>
                                        <button id="loginBtn" class="btn btn-success" type="submit">登 录</button>
                                        <button class="btn btn-info" type="reset">重 置</button>
                                    </p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="./inc/footer.jsp"></jsp:include>
        

    </div>
    
</body>


<script>
$(document).ready(function(){
	$('#email').blur(function(){
		checkEmail();
	});
	
	$('#password').blur(function(){
		checkPassword();
	});
	
	$('#commentform').submit(function(e){
		var emailValid = checkEmail();
		var passwordValid = checkPassword();
		if(emailValid&&passwordValid){
			return true;
		}else{
			return false;
		}
	});
});

//检查邮箱是否合法
function checkEmail(){
	var email = $("#email").val();
	//邮箱地址
	var emailRegex =  /^([a-z0-9A-Z]+[-|_|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
	if(email==''){
		$('#error_email').text('邮箱不能为空').show();
		return false;
	}else if(!emailRegex.test(email)){//检查正则匹配
		$('#error_email').text('邮箱格式不符合规范').show();
		return false;
	}else{//输入正确
		$('#error_email').hide();
		return true;
	}
}

//检查密码是否合法
function checkPassword(){
	var passwordVal = $('#password').val();
	if(passwordVal==''){
		$('#error_password').text('密码不能为空').show();
		return false;
	}else{
		$('#error_password').hide();
		return true;
	}
}
</script>
</html>
