<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<script>
$(".deploy-refresh").click(function(){
	var currentUrl = window.location.href;
	var refreshUrl = delQueryStr(currentUrl, "code");
	alert("currentUrl: "+currentUrl);
	alert("refreshUrl: "+refreshUrl);
	location.href = refreshUrl;
	//location.reload(false);
})
</script>