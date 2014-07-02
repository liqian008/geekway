<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
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

.order{
margin:30px 20px;
}

</style>
<body>
	<div class="header">
		<div class="header_menu">
			<a class="back" href="javascript:history.back();"></a>
			<span>订单查询</span>
			<a class="home" href="./home.htm"></a>
		</div>	
		
	</div>

	<div class="content">
		<div class="main">

			<div class="order">
				请输入收货人手机号码进行查询
					<form id="orderForm" action="./edbOrderList" name="orderForm">
					<input type="hidden" name="periodType" value="0">
					<div>
						<%String userMobile = (String)request.getAttribute("userMobile");%>
						<input type="text" id="userMobile"  name="userMobile" value="<%=userMobile==null?"":userMobile%>"  placeholder="请输入手机号">
					</div>
					<a href="javascript:void(0)" id="orderSubmitBtn" class="klh-button radius">查询</a>	
					</form>
			</div>
		</div>
	</div>
</body>

<script>
$("#orderSubmitBtn").click(function(){
	if(checkUserMobile()){
		$("#orderForm").submit();
	}else{
	alert("请输入正确额手机号码");
	}
})

//检查邮箱是否合法
function checkUserMobile(){
	var userMobile = $('#userMobile').val();
	if(userMobile!=''){
		return true;
	}
	return false;
}
</script>

</html>
