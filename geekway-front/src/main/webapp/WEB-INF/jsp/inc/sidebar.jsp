<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.model.*"%>
<%@ page import="com.bruce.geekway.constants.*"%>

<%
RegUser currentUser = (RegUser)session.getAttribute(ConstFront.CURRENT_USER); 
%>
<div id="sidebar" class="span3">

	<div class="widget widget_category">
		<h5>菜单</h5>
		<ul>
			<%if(currentUser==null){ %>
			<li><a href="${pageContext.request.contextPath}/login">登录</a> ｜ <a href="${pageContext.request.contextPath}/registe">注册</a></li>
			<li><a href="${pageContext.request.contextPath}/quickstart">快速集成微信JS-SDK</a></li>
			<%}%>
			<li><a href="${pageContext.request.contextPath}/myWxApps">管理我的微信应用</a></li>
			<%if(currentUser!=null){ %>
			<li><a href="${pageContext.request.contextPath}/myWxAppAdd">添加微信应用</a></li>
			<li><a href="${pageContext.request.contextPath}/profile">账户信息</a></li>
			<li><a href="${pageContext.request.contextPath}/logout">注销登录</a></li>
			<%} %>
		</ul>
	</div>

</div>