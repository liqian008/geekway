<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import=" com.bruce.geekway.model.wx.pay.*" %>
<%@ page import=" com.bruce.geekway.utils.*" %>

<html>
<body>
<%
WxOrderAddressJsObj orderAddressJsObj = (WxOrderAddressJsObj)request.getAttribute("orderAddressJsObj");
if(orderAddressJsObj!=null){
%>
<%=JsonUtil.gson.toJson(orderAddressJsObj) %>

<input type="button" name="getAddressBtn" value="getAddress" onclick="getAddress()"/>
<script>
function getAddress(){
	alert("choose");
	//获取微信共享地址
	WeixinJSBridge.invoke('editAddress',{
		"appId" : "<%=orderAddressJsObj.getAppId()%>",
		"scope" : "<%=orderAddressJsObj.getScope()%>",
		"signType" : "<%=orderAddressJsObj.getSignType()%>",
		"addrSign" : "<%=orderAddressJsObj.getAddrSign()%>",
		"timeStamp" : "<%=orderAddressJsObj.getTimeStamp()%>",
		"nonceStr" : "<%=orderAddressJsObj.getNonceStr()%>" 
		},function(res){
			//若res 中所带的返回值不为空,则表示用户选择该返回值作为收货地址。否则若返回空,则表示用户取消了这一次编辑收货地址。
			alert(res);
			alert(res.proviceFirstStageName);
			alert(res.addressCitySecondStageName);
			//document.form1.address1.value =res.proviceFirstStageName;
			//document.form1.address2.value =res.addressCitySecondStageName;
			//document.form1.address3.value =res.addressCountiesThirdStageName;
			//document.form1.detail.value = res.addressDetailInfo;
			//document.form1.phone.value = res.telNumber;
		}
	);
}
</script>
<%}%>
</body>
</html>
