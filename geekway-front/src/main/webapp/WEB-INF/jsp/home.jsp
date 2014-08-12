<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

<html>
<body>
<h2>openId: <%=request.getAttribute("openId")%></h2>

关注状态：
<%
WxMpUser wxMpUser = (WxMpUser)request.getAttribute("wxMpUser");
System.out.println("wxMpUser:  "+wxMpUser);
if(wxMpUser==null){%>
	未关注
<%}else{%>
	已关注<br/>
详细资料：
	<%if(wxMpUser.getSyncStatus()!=null&&wxMpUser.getSyncStatus()==1){%>
		昵称: <%=wxMpUser.getNickname() %><br/>
		头像: <img src="<%=wxMpUser.getHeadImgUrl() %>" width="100px"><br/>
	<%}else{%>
		尚未完成同步
	<%}%>
<%}%>

</body>
</html>
