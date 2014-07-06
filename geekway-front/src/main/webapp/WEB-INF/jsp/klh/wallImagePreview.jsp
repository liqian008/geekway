<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.klh.*" %>


<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

<title>可乐惠</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/klh/css/klh.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/klh/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/klh/js/jquery.masonry.js"></script>

<style>
body{
height:100%;
}
</style>

<body>
	<div class="header">
		<div class="header_menu">
			<div class="header_menu">
				<a class="back" href="javascript:history.back();"></a>
				<span>随手拍</span>
				<a class="home" href="./home.htm"></a>
			</div>	
		</div>
	</div>

	<div class="content">
		<div class="logo">
			<img src="<%=request.getAttribute("imgUrl")%>">
		</div>
		
	</div>
</body>

<script>
$("body").click(function(){
	history.back();
});
</script>

</html>

