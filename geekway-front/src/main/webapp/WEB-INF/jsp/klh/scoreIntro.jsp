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
<body>
	<div class="header">
		<div class="header_menu">
			<div class="header_menu">
				<a class="back" href="javascript:history.back();"></a>
				<span>积分规则</span>
				<a class="home" href="./home"></a>
			</div>	
		</div>		
	</div>
	<div class="content">
		<div class="main main_border">
			<%
			KlhSetting klhSetting = (KlhSetting)request.getAttribute("klhSetting");
			%>
			<div class="score">
				
			<p>	1. “绑定个人资料”可获得<%=klhSetting.getBindScore() %>积分</p>
			<p>	2. “每日签到”可获得<%=klhSetting.getSignScore() %>积分</p>
			<p>	3. “随手拍”发照片可获得<%=klhSetting.getPostScore() %>积分</p>
			<p>	4. “随手拍”点赞可获得<%=klhSetting.getLikeScore() %>积分</p>
			<p>	5. 可使用积分免费兑换产品</p>
		
			</div>
		</div>
		<div style="margin-top:10px">
		<a href="./scoreHome" class="klh-button radius">获取积分</a>	
		</div>
	</div>
		
</body>

<script>

</script>

</html>



