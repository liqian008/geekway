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
				<a class="home" href="./home.htm"></a>
			</div>	
		</div>		
	</div>
	<div class="content">
		<div class="main main_border">

			<div class="score">
				<a href="./scoreHome" class="klh-button radius">获取积分</a>	
<p>	1. 注册可获得50积分；</p>
<p>	2. 填写调查问卷可获得50积分；</p>
<p>	3. 签到可获得5分积分；</p>
<p>	4. 分享到朋友圈可获得20分积分；</p>
<p>	5. “微论坛”发帖可获得20分积分；</p>
<p>	6. “微论坛”回复可获得5分积分；</p>
<p>	7. “随手拍”发照片可获得20分积分；</p>
<p>	8. “随手拍”点赞可获得5分积分；</p>
<p>	9. 推荐好友关注可乐惠可获得20积分<br/>
（每个会员最多可推荐5人）；</p>
<p>	10.购物获得积分，1元得1积分。</p>

			</div>
		</div>
	</div>
		
</body>

<script>

</script>

</html>



