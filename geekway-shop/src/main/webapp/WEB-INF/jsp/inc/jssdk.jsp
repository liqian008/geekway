<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
(function() {
  var jssdkEle = document.createElement("script");
  jssdkEle.type = "text/javascript";
  var currentUrl = encodeURIComponent(location.href.split('#')[0]);;
  jssdkEle.src = "${pageContext.request.contextPath}/api/wxJsConfigSrc?pageUrl="+currentUrl;
  var allJsEles = document.getElementsByTagName("script")[0]; 
  allJsEles.parentNode.insertBefore(jssdkEle, allJsEles);
})();
</script>