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
margin: 30px 0px;
text-align:center;
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
width:33%;
}

.score ul li div{
padding: 10px;
overflow:hidden;
}

.score ul li img{
width:120px;
height:90px;
}

.score p{
color:#000;
font-size:14px;
margin:2px 0px;
}

</style>
<body>
	<div class="header">
		<div class="header_menu">
			<div class="header_menu">
				<a class="back" href="javascript:history.back();"></a>
				<span>积分产品</span>
				<a class="home" href="./home.htm"></a>
			</div>	
		</div>		
	</div>
	<div class="content">
		<div class="main">
			<div class="score">
				<ul>
					<%
					List<KlhProduct> productList = (List<KlhProduct>)request.getAttribute("productList");
					if(productList!=null&&productList.size()>0){
						for(KlhProduct product: productList){
					%>
					<li>
						<div style="padding: 0px 10px">
							<a href="./scoreProductInfo?productId=<%=product.getId()%>">
								<img src="<%=product.getProductPicUrl()%>" width="100%">
								<p><%=product.getTitle() %></p>
								<p><%=product.getScore() %>分</p>
							</a>
						</div>
					</li>
					<%} 
					}%>
				</ul>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
		
</body>

<script>
</script>

</html>

