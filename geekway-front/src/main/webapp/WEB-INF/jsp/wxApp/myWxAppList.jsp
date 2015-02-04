<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.WxApp" %>


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
                        <h1>微信应用</h1>
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

                    <div class="span9">
                        <h4>微信应用管理</h4>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <td>ID</td>
                                    <td>应用名称</td>
                                    <td>微信AppId</td>
                                    <td>操作</td>
                                </tr>
                            </thead>
                            <tbody>
                            	<%
                            	List<WxApp> myWxAppList = (List<WxApp>)request.getAttribute("myWxAppList");
                            	if(myWxAppList!=null&&myWxAppList.size()>0){
                            		int i=0;
                            		for(WxApp wxApp: myWxAppList){
                            			i++;
                            	%>
                                <tr>
                                    <td><%=i%></td>
                                    <td><%=wxApp.getName()%></td>
                                    <td><%=wxApp.getWxAppId()%></td>
                                    <td>
                                        <a href="./myWxAppInfo?id=<%=wxApp.getId()%>">查看</a>
                                        <!-- 
                                        &nbsp;|<a href="javascript:void(0)" class="delAppBtn" dateItem="<%=wxApp.getId()%>">删除</a>
                                        &nbsp;|<a href="#">JS权限</a>
                                         -->
                                    </td>
                                </tr>
                                <%}
                            	}%>
                               
                            </tbody>
                        </table>

                        <button id="addWxApp" type="button" class="btn btn-success">添加微信应用</button>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../inc/footer.jsp"></jsp:include>

    </div>
    
</body>

<script>
$(document).ready(function(){
	$("#addWxApp").click(function(){
		location.href="./myWxAppAdd";
	});
	
	$("body").delegate('a.delAppBtn', 'click', function(){
		var delAppBtn = $(this);
		var appId = delAppBtn.attr('dataItem');
		if(confirm("删除后将无法恢复，确定删除吗？")){
			location.href="./delMyWxApp?id="+appId;
		}
	});
});
</script>
</html>
