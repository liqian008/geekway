<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.klh.*" %>

<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

<title>可乐惠</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/klh//css/klh.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/klh//js/jquery.js"></script>

<style>
#notification{
margin:10px;
text-align:center;
}

.score{
margin:20px;
text-align:center;
}


label { text-align:left;font-size: 12px; color: #4d4d4d; cursor: pointer; display: block; font-weight: 500; margin-bottom: 3px; }
label.center { text-align:center }
label.inline { line-height: 30px; margin: 0 0 12px 0; }

input[type="text"]{
width:100%;
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
		<div class="main">
			
			<%
			String flag = (String)request.getAttribute("flag");
			boolean updated = "updated".equals(flag); 
			%>
			
			<div id="notification" <%=updated?"":"style='display:none'"%>>
				<div style="padding: 30px 0px">
					个人资料修改成功 
				</div>
				<div style="margin-top:10px">
					<a href="./profile" id="okBtn" class="klh-button radius">OK</a>	
				</div> 
			</div>
			
			<form action="./updateProfile" method="post" name="profileForm">
			
			<div class="userProfile" <%=updated?"style='display:none'":""%>>
				<label>昵称</label>
				<input type="text" id="nickname" name="nickname" value="<%=userProfile.getNickname()==null?"":userProfile.getNickname()%>">
				<label>真实姓名</label>
				<input type="text" id="realname" name="realname" value="<%=userProfile.getRealname()==null?"":userProfile.getRealname()%>">
				<label>手机号码</label>
				<input type="text" id="mobile" name="mobile" value="<%=userProfile.getMobile()==null?"":userProfile.getMobile()%>">
				<label>Email</label>
				<input type="text" id="email" name="email" value="<%=userProfile.getEmail()==null?"":userProfile.getEmail()%>">
				<label>通讯地址</label>
				<input type="text" id="address" name="address" value="<%=userProfile.getAddress()==null?"":userProfile.getAddress()%>">
				
				<div class="score">
					<a href="javascript:void(0)" class="klh-button radius" id="submitBtn">修改</a>
				</div>
				
			</div>
			<div class="seperator"> </div>
			
			</form>
		</div>
	</div>
		
</body>

<script>

$("#submitBtn").click(function (){
	document.forms[0].submit();
});


//$("#okBtn").click(function (){
//	$("#notification").hide();
	//$(".userProfile").show();
//});

</script>

</html>



