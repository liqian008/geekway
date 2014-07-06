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
margin: 30px 20px;
text-align:center;
}

.score p{
font-size:13px;
color: darkred;
}

.seperator {
padding: 0px;
margin: 6px 10px;
height: 1px;
background-color: darkred;
}


.score ul{
}

.score ul li{
float:left;
width:50%;
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
		<div class="main main_border">

			<div class="score">
				<h4>您总共获得的可乐惠积分</h4>
				<h4>xxx分</h4>
				<h4>您目前拥有的可乐惠积分</h4>
				<h4>xxx分</h4>
				<a href="javascript:void(0)" class="klh-button radius lock">签 到</a>
			</div>
			<div class="seperator"> </div>
			<div class="score">
				<ul>
					<li><a href="javascript:void(0)" class="klh-button radius lock">兑换记录</a></li>
					<li><a href="./scoreProductList" class="klh-button radius">兑换礼品</a></li>
				</ul>
				<div class="clearfix"></div>
			</div>
			
		</div>
	</div>
</body>

<script>
$(".lock").click(function(){
	alert("因可乐惠系统未开放用户注册，此功能暂时予以屏蔽");
})

</script>

</html>

