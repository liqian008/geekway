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
                        <h1>快速开始</h1>
                        <!-- <span>Some Cool Page Description.</span>
                        <ul class="crumb">
                            <li><a href="index.html">Home</a></li>
                            <li class="sep">/</li>
                            <li>Blog Right Sidebar</li>
                        </ul> -->
                    </div>
                </div>
            </div>
        </div>
        <!-- subheader close -->

        <div id="content">
            <div class="container">
                <div class="row">

                    <jsp:include page="./inc/sidebar.jsp"></jsp:include>

					<div class="span8 ">
						<h4>快速添加微信应用</h4>
						<div id="comment-form-wrapper">

							<div class="alert alert-warning">
								<strong>提醒：</strong> 添加微信应用后，会为您创建一个新用户——如果您之前曾提交过，请先 <a
									href="./login" class="btn btn-small btn-primary">登录</a>
							</div>

							<div class="comment_form_holder">
								<form id="commentform" name="form1" method="post"
									action="./quickstartGo">

									<label>邮箱（登录用户名） <span class="req">*</span></label>
                                    <input type="text" name="email" id="email" width="80%">
                                    <div id="error_email" class="error">邮箱输入有误</div>

                                    <label>登录密码 <span class="req">*</span></label>
                                    <input type="password" name="password" id="password" width="80%">
                                    <div id="error_password" class="error">密码输入有误</div>

                                    <label>确认密码 <span class="req">*</span></label>
                                    <input type="password" name="rePassword" id="rePassword" width="80%">
                                    <div id="error_repassword" class="error">确认密码输入有误</div>


									<label>联系人手机号 <span class="req">*</span></label>
                                    <input type="text" name="mobile" id="mobile">
                                    <div id="error_mobile" class="error">手机号输入有误</div>

									<label>微信应用名称 <span class="req">*</span></label> 
									<input type="text" name="name" id="name" value="${myWxApp.name}">
									<div id="error_name" class="error">应用名称输入有误</div>
									
										
									<div class="alert alert-error">
										为了生成微信所需的JsTicket，我们需要您提供“微信AppId和Secret”，并严格为您保密 <a href="javascript:void(0)" class="btn btn-small btn-primary">如何获取？</a>
									</div>	
									<label>微信AppId<span class="req">*</span></label>
									<input type="text" name="wxAppId" id="wxAppId" value="${myWxApp.wxAppId}">
									<div id="error_wxAppId" class="error">微信AppId输入有误</div>
									
									<label>微信Secret <span class="req">*</span></label>
									<input type="text" name="wxAppSecret" id="wxAppSecret" value="${myWxApp.wxAppSecret}" >
									<div id="error_wxAppSecret" class="error">微信Secret输入有误</div>
									
									<p>
										<button class="btn btn-success" type="submit">保 存</button>
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
	
	$('#rePassword').blur(function(){
		checkPassword();
	});
	

	$('#mobile').blur(function(){
		checkMobile();
	});
	
	
	$('#name').blur(function(){
		checkName();
	});
	
	$('#wxAppId').blur(function(){
		checkWxAppId();
	});
	
	$('#wxAppSecret').blur(function(){
		checkWxAppSecret();
	});
	
	$('#commentform').submit(function(e){
		var emailValid = checkEmail();
		var passwordValid = checkPassword();
		var mobileValid = checkMobile();
		var nameValid = checkName();
		var wxAppIdValid = checkWxAppId();
		var wxAppSecretValid = checkWxAppSecret();
		if(emailValid&&passwordValid&&mobileValid&&nameValid&&wxAppIdValid&&wxAppSecretValid){
			return true;
		}else{
			return false;
		}
	});
});



//检查名称是否合法
function checkName(){
	var name = $("#name").val();
	if(name==''){
		$('#error_name').text('微信应用名称不能为空').show();
		return false;
	}else{//输入正确
		$('#error_name').hide();
		return true;
	}
}


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
	var rePasswordVal = $('#rePassword').val();
	
	var passwordRegex = /^(\w){6,20}$/;
	if(passwordVal==''){
		$('#error_password').text('密码不能为空').show();
		return false;
	}else if(!passwordRegex.test(passwordVal)){//检查正则匹配
		$('#error_password').text('密码格式不符合规范').show();
		return false;
	}else if(passwordVal!=rePasswordVal){//检查正则匹配
		$('#error_password').hide();
		$('#error_repassword').text('密码与确认密码不匹配').show();
		return false;
	}else{
		$('#error_password').hide();
		$('#error_repassword').hide();
		
		return true;
	}
}



//检查mobile是否合法
function checkMobile(){
	var mobile = $("#mobile").val();
	if(mobile==''){
		$('#error_mobile').text('手机号不能为空').show();
		return false;
	}else{//输入正确
		$('#error_wxAppId').hide();
		return true;
	}
}

//检查wxAppId是否合法
function checkWxAppId(){
	var wxAppId = $("#wxAppId").val();
	if(wxAppId==''){
		$('#error_wxAppId').text('微信AppId不能为空').show();
		return false;
	}else{//输入正确
		$('#error_wxAppId').hide();
		return true;
	}
}

//检查wxAppSecret是否合法
function checkWxAppSecret(){
	var wxAppSecret = $("#wxAppSecret").val();
	if(wxAppSecret==''){
		$('#error_wxAppSecret').text('微信AppSecret不能为空').show();
		return false;
	}else{//输入正确
		$('#error_wxAppSecret').hide();
		return true;
	}
}
</script>
</html>
