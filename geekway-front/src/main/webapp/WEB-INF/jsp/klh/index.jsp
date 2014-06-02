<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<body>

	<%
	String authorizeUrl = (String)request.getAttribute("authorizeUrl");
	if(authorizeUrl!=null){
		response.sendRedirect(authorizeUrl); 
	}
	%>
</body>
</html>