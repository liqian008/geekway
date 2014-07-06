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
margin: 0px 0px;
text-align:center;
}

.seperator {
padding: 0px;
margin: 6px 10px;
height: 1px;
background-color: darkred;
}

.score h5{
margin: 10px 0px;
}

</style>
<body>
	<div class="header">
		<div class="header_menu">
			<div class="header_menu">
				<a class="back" href="javascript:history.back();"></a>
				<span>我的积分</span>
				<a class="home" href="./home.htm"></a>
			</div>	
		</div>		
	</div>
	<div class="content">
		<div class="main">
			<%
			KlhProduct productInfo = (KlhProduct)request.getAttribute("productInfo");
			if(productInfo!=null){
			%>
			<div class="score">
				<img src="<%=productInfo.getProductPicUrl() %>" width="100%">
				<h5><%=productInfo.getTitle()%></h5>
				<h5><%=productInfo.getScore()%>分</h5>
			</div>
			<div class="seperator"> </div>
			<div class="score">
				<a href="javascript:void(0)" class="klh-button radius lock">兑换</a>
			</div>
			<%} %>
		</div>
	</div>
</body>

<script>
$(".lock").click(function(){
	alert("因可乐惠系统未开放用户注册，此功能暂时予以屏蔽");
})

</script>

</html>