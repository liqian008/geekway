<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="com.bruce.geekway.model.klh.*" %>


<%!public String getLiActive(int currentTab, Integer tabType){
	if(tabType!=null){
		if(currentTab==tabType){
			return " class='active' ";
		}
	}
	return "";
} %>

<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

<title>可乐惠</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/klh/css/klh.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/klh/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/klh/js/jquery.masonry.js"></script>

<style>
 
.content ul li{
float:left;
width:33.33%;
background:#c6c0b4;
height:30px;
line-height:30px;
}

.content ul li div{
border-right:1px solid #f7f3f0;
}

.active{
	background:#93242a !important;
}


.container-fluid {
  padding: 0px;
}
.box {
  padding:0px 5px;
  margin-bottom: 20px;
  float: left;
  width: 46%;
  color:#93242a;
  font-size:12px;
}

.box a{
	color:#93242a;
}

.box img {
  max-width: 100%
}
.box div{
	height:12px;
	line-height:12px;
}
.box i {
  background-image: url('images/like.png');
  width: 12px;
  height: 10px;
  margin-right:3px;
  display:inline-block;
}
</style>

<body>
	<div class="header">
		<div class="header_menu">
			<div class="header_menu">
				<a class="back" href="javascript:history.back();"></a>
				<span>照片墙</span>
				<a class="home" href="./home.htm"></a>
			</div>	
		</div>
	</div>

	<div class="content">
		<div class="logo">
			<img src="./images/wall.jpg">
		</div>
		
		<div class="nav">
			<ul>
				<%Integer tabType = (Integer)request.getAttribute("tabType"); %>
				<li <%=getLiActive(0, tabType)%>><div><a href="./latestWallImages">最新发布</a></div></li>
				<li <%=getLiActive(1, tabType)%>><div><a href="./hotestWallImages">热度排序</a></div></li>
				<li>
					<div>
					<a href="./newImage">上传照片</a>
					</div>
				</li>
	 		</ul>
	 		<div class="clearfix"></div>
		</div>	

		<div class="main">
			
			<%
			List<KlhWallImage> wallImageList = (List<KlhWallImage> ) request.getAttribute("wallImageList");
			if(wallImageList!=null){
			%>
			<div id="masonry" class="container-fluid">
			<%for(KlhWallImage wallImage: wallImageList) {%>
			  <div class="box">
			  	<img src="<%=wallImage.getThumbPicUrl()%>">
			  	<div style="float:left">
			  		<%if(wallImage.isHasLike()){%>
			  		<a href="javascript:void(0)" class="hasliked"><i></i><%=wallImage.getLikeCount()%>个喜欢</a>
			  		<%}else{%>
			  		<a href="javascript:void(0)" class="like" dataItem="<%=wallImage.getId()%>"><i></i><span class="likeNum"><%=wallImage.getLikeCount()%></span>个喜欢</a>
			  		<%} %>
			  	</div>
			  	<div style="float:right"><%=wallImage.getNickname()%></div>
			  </div>
			<%} %>
			</div>
			<%} %>
		</div>
	</div>
</body>

<script>
$(function(){
    var $container = $('#masonry');
    $container.imagesLoaded( function(){
        $container.masonry({
            itemSelector : '.box',
            gutterWidth : 0,
            isAnimated: true,
        });
    });
});

$("body").delegate('a.like', 'click', function(){
	var itemLikeEle = $(this);
	var wallImageId = itemLikeEle.attr('dataItem');
	var jsonData = {"wallImageId": wallImageId};
	$.post("<%=request.getContextPath()%>/klh/like.json", jsonData, function(data) {
		if(data.result==1){
			itemLikeEle.attr("class", "hasLiked");
			//TODO like数+1
			location.reload(true);
		}else{
			alert(data.message);
		}
	 }, "json");
});

</script>

</html>

