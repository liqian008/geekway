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


label { text-align:left;font-size: 12px; color: #4d4d4d; display: block; margin-bottom: 3px; }
label.center { text-align:center }
label.inline { line-height: 30px; margin: 0 0 12px 0; }


.preview { text-align:left; font-size: 15px; color: #2d2d2d; display: block; font-weight: 500; margin: 8px 0; }


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
			
			<div class="userProfile">
				<label>昵称 *</label>
				<div class="preview">
				<%=userProfile.getNickname()==null||"".equals(userProfile.getNickname())?"无":userProfile.getNickname()%>
				</div>
				<label>真实姓名 *</label>
				<div  class="preview">
				<%=userProfile.getRealname()==null||"".equals(userProfile.getRealname())?"无":userProfile.getRealname()%>
				</div>
				<label>手机号码 *</label>
				<div  class="preview">
				<%=userProfile.getMobile()==null||"".equals(userProfile.getMobile())?"无":userProfile.getMobile()%>
				</div>
				<label>Email *</label>
				<div  class="preview">
				<%=userProfile.getEmail()==null||"".equals(userProfile.getEmail())?"无":userProfile.getEmail()%>
				</div>
				<label>通讯地址</label>
				<div class="preview" >
				<%=userProfile.getAddress()==null||"".equals(userProfile.getAddress())?"无":userProfile.getAddress()%>
				</div>
				
				<div class="score">
					<a href="./profile" class="klh-button radius" id="previewBtn">修 改</a> 
				</div>
				
			</div>
			<div class="seperator"> </div>
			
		</div>
	</div>
		
</body>


</html>



