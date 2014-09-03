<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.klh.*" %>

<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/klh//css/klh.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/klh//js/jquery.js"></script>

<style>

.score{
margin: 30px 20px 30px 40px;
text-align:left;
}

.score p{
font-size:13px;
color: darkred;
}

</style>

<%
KlhUserProfile userProfile = (KlhUserProfile)session.getAttribute("sessionUserProfile");
%>

<body>
	<div class="header">
		<div class="header_menu">
			<div class="header_menu">
				<a class="back" href="javascript:history.back();"></a>
				<span>个人信息</span>
				<a class="home" href="./home"></a>
			</div>	
		</div>		
	</div>
	<div class="content">
		<div class="main main_border">

			<div class="score">
			<p>	昵称: <%=userProfile.getNickname()%></p>
			<p>	姓名: <%=userProfile.getRealname()%></p>
			<p>	地址: <%=userProfile.getAddress()%></p>
			<p>	Email: <%=userProfile.getEmail()%></p>

			</div>
		</div>
	</div>
		
</body>

<script>

</script>

</html>



