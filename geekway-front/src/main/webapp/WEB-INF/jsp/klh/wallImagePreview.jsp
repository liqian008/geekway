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
<script type="text/javascript" src="<%=request.getContextPath() %>/klh/js/jquery.masonry.js"></script>

<style>
body{
height:100%;
}

.box a{
	color:#93242a;
}
.box i {
	background-image: url('images/like.png');
	width: 12px;
	height: 10px;
	margin-right: 3px;
	display: inline-block;
}
</style>

<body>
	<div class="header">
		<div class="header_menu">
			<div class="header_menu">
				<a class="back" href="javascript:history.back();"></a>
				<span>随手拍</span>
				<a class="home" href="./home"></a>
			</div>	
		</div>
	</div>

	<div class="content">
		<%
	  		KlhWallImage wallImage = (KlhWallImage)request.getAttribute("wallImage");
			if(wallImage!=null){
		%>
		<div class="logo">
			<img src="<%=wallImage.getPicUrl()%>">
		</div>
		
		<div class="box">
	  		<%
	  		if(wallImage.isHasLike()){%>
	  		<a href="javascript:void(0)" class="hasliked"><i></i><%=wallImage.getLikeCount()%>个喜欢</a>
	  		<%}else{%>
	  		<a href="javascript:void(0)" id="like" class="like" dataItem="<%=wallImage.getId()%>"><i></i><span class="likeNum"><%=wallImage.getLikeCount()%></span>个喜欢</a>
			<script>
			$("a.like").click(function(){
				var wallImageId = $(".like").attr('dataItem');
				var jsonData = {"wallImageId": wallImageId};
				$.post("<%=request.getContextPath()%>/klh/like.json", jsonData, function(data) {
					if(data.result==1){
						
						$(".like").attr("class", "hasLiked");
						//TODO like数+1
						location.reload(true);
					}else{
						alert(data.message);
					}
				 }, "json");
			});
			</script>
	  		
	  		<%} %>
	  	</div>
		<%}%>		
	</div>
	
	
</body>

<script>
$(".logo").click(function(){
	history.back();
});
</script>

</html>

