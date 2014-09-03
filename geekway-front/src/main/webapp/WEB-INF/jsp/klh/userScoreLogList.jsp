<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.klh.*" %>

<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

<title>可乐惠</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/klh/css/klh.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/klh/js/jquery.js"></script>

<style>
.header{
 
height:40px;
padding-bottom:0px;
}

.nav{
background-color:#c6c0b4;
margin:0px;
height:40px;
}
 
.nav ul{
color:#93242a;
margin:0px;
padding:0px;
list-style:none;
}

.nav ul li{
float:left;
width:50%;
text-align:center;
line-height:40px;
}

.nav ul li.active{
border-bottom:2px solid;
}

.nav ul li a{
color:#93242a;
}

.nav ul li div{
border-right:1px solid #fff;
}

.order_item{
background-color:#c6c0b4;
font-size: 14px;
padding:5px; 
}

.order_item div{
padding: 4px 10px;
color: #fff;
text-align:left
}

.order_item .product{
font-size:14px;
}

.order_item .trace{
float:right;
}


.order_item div.sperator{
padding: 0px;
margin: 6px 10px;
height:1px;
background-color:#fff;
}

.order_item .product td, .order_item .product th{
padding: 5px;
}

</style>


<%SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); %>

<body>
	<div class="header">
		<div class="header_menu">
			<a class="back" href="javascript:history.back();"></a>
			<span>积分变更记录</span>
			<a class="home" href="./home"></a>
		</div>	
		
	</div>
	
	<div class="content">
		<div class="main">
			<div class="order">
				
				<ul>
					<%
					List<KlhUserScoreLog> userScoreLogList = (List<KlhUserScoreLog>)request.getAttribute("userScoreLogList");
					if(userScoreLogList!=null&&userScoreLogList.size()>0){
						for(KlhUserScoreLog scoreLog: userScoreLogList){
					%>
					<li>
					
						<div class="order_item radius">
							<div class="product">		
								<%=scoreLog.getReason() %>，操作时间：<%=sdf.format(scoreLog.getCreateTime()) %>
							</div>
						</div>
					</li>
					<%} 
					}%>
				</ul>
			</div>
		</div>
	</div>
</body>

<script>

</script>

</html>


