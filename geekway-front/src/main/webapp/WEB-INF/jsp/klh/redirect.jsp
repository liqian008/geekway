<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.constants.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.klh.*" %>

<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

<title>可乐惠</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/klh/css/klh.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/klh/js/jquery.js"></script>

<style>

.order{
margin:30px 20px;
}

</style>


<%
String contextPath = "";
%>

<%
String prompt = (String) request.getAttribute(ConstFront.REDIRECT_PROMPT);
String redirectUrl = (String) request.getAttribute(ConstFront.REDIRECT_URL);
if(prompt==null||"".equals(prompt)){
	prompt = "正在为您转入后续页面，请稍候…";
}
if(redirectUrl==null||"".equals(redirectUrl)){
	redirectUrl = contextPath + "/";
}
%>



<body>
	<div class="header">
		<div class="header_menu">
			<a class="back" href="javascript:history.back();"></a>
			<span>系统提示</span>
			<a class="home" href="./home.htm"></a>
		</div>	
		
	</div>

	<div class="content">
		<div class="main">

			<div class="order">
				<h5>
                	<%=prompt%><br/>
                </h5>
			</div>
		</div>
	</div>
</body>

<script language='Javascript'>
setTimeout("location.href='<%=redirectUrl%>'", 2000);
</script>

</html>
