<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.wx.pay.*" %>

<!DOCTYPE html>
<html>
<body>
	<%=session.getAttribute("userAccessToken")%>
	<div class="WCPay">
		<a id="getBrandWCPayRequest" href="./buy"><h1
				class="title">点击购买</h1></a>
	</div>

</body>
</html>