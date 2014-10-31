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
margin: 0px 0px;
text-align:center;
}

.seperator {
padding: 0px;
margin: 6px 10px;
height: 1px;
background-color: darkred;
}

.score h5{
margin: 10px 0px;
}

.address h5{
margin: 10px 0px;
text-align:left;
}

label { text-align:left;font-size: 12px; color: #4d4d4d; cursor: pointer; display: block; font-weight: 500; margin-bottom: 3px; }
label.center { text-align:center }
label.inline { line-height: 30px; margin: 0 0 12px 0; }

input[type="text"]{
width:100%;
}
</style>
<body>
	<div class="header">
		<div class="header_menu">
			<div class="header_menu">
				<a class="back" href="javascript:history.back();"></a>
				<span>积分产品</span>
				<a class="home" href="./home"></a>
			</div>	
		</div>		
	</div>
	<div class="content">
		<div class="main">
			
			<%
			KlhUserProfile userProfile = (KlhUserProfile)session.getAttribute("sessionUserProfile");
			KlhProduct productInfo = (KlhProduct)request.getAttribute("productInfo");
			if(productInfo!=null){
			%>
				<div class="score">
					<img src="<%=productInfo.getProductPicUrl() %>" width="100%">
					<h5>产品名称: <%=productInfo.getTitle()%></h5>
					<h5>积分: <%=productInfo.getScore()%>分</h5>
					<h5>剩余数量: <%=productInfo.getLeftNum()%>个</h5>
				</div>
				<div class="seperator"> </div>
			
				<%
				Integer userScore = (Integer)request.getAttribute("userScore");
				boolean applyAbility = false;
				String reason = "无法兑换";
				if(productInfo.getLeftNum()==null||productInfo.getLeftNum()<=0){
					reason = "商品数量不足，无法兑换";
				}else if(productInfo.getScore()!=null&&userScore!=null&&productInfo.getScore()>userScore){
					reason = "用户积分不足，无法兑换";
				}else{
					applyAbility = true;
				}
				if(applyAbility){
				%>
				<form action="./productApply" method="post" name="applyForm"> 
					<input type="hidden" name="productId" value="<%=productInfo.getId() %>">
					<div class="address" style="">
							<label>收件人姓名</label>
							<input type="text" id="postName" name="postName" value="<%=userProfile.getRealname()==null?"":userProfile.getRealname()%>">
							<label>手机号码</label>
							<input type="text" id="postMobile" name="postMobile" value="<%=userProfile.getMobile()==null?"":userProfile.getMobile()%>">
							<label>收货地址</label>
							<input type="text" id="postAddress" name="postAddress" value="<%=userProfile.getAddress()==null?"":userProfile.getAddress()%>">
							<label>邮政编码</label>
							<input type="text" id="postCode" name="postCode" value="">
					</div>
					<div class="score">
						<a href="javascript:void(0)" class="klh-button radius" id="submitBtn">兑换</a>
					</div>
				</form>
				<%}else{%>
				<div class="score">
					<h5><%=reason%></h5>
				</div>
				<%}%>
			<%}%>
		</div>
	</div>
</body>

<script>

$("#submitBtn").click(function (){
	var realname = $('#postName').val();
	var mobile = $('#postMobile').val();
	var address = $('#postAddress').val();
	
	if(realname==''){
		alert('收件人不能为空');
		return;
	}
	
	var mobileReg = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	if(mobile==''){
		alert('手机号不能为空');
		return;
	}
	if(!mobileReg.test(mobile)){
		alert('手机号不符合规范');
		return;
	}
	
	if(address==''){
		alert('收件人地址不能为空');
		return;
	}
	
	if(confirm("确定兑换该商品吗？")){
		document.forms[0].submit();
	}else{
		return false;
	}
});
</script>

</html>