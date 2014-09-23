<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.geekway.utils.*" %>


<div id="userLoadingContainer" class="container center-text">
	<a href="javascript:void(0)" id="userLoadingBtn" class="button button-turqoise">用户资料加载中...</a>
</div>

<%
String userOpenId = null;
Cookie[] cookieArray = request.getCookies();
if(cookieArray!=null&&cookieArray.length>0){
	for(Cookie cookie: cookieArray){
		if("weixin_user_id".equals(cookie.getName())){
			userOpenId = cookie.getValue();
			break;
		}
	}
}

if (userOpenId == null) {//用户未登录，无法访问
	//使用微信oauth登录，获取userOpenId
	String oauthUrl = WxMpUtil.buildWeixinOauthUrl(0, "","");
	//ajax方式获取U色人openId
%>
<script>
$.post('<%=oauthUrl%>', function(data) {
	var result = data.result;
	if(result==1){//请求成功
		//获取userOpenId
		$("#userLoadingContainer").hide();
	}
})
</script>
<%}%>


