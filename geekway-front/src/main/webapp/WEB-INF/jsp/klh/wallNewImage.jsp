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
.content ul li{
float:left;
width:33.33%;
background:#ababab;
height:30px;
line-height:30px;
}

.content ul li div{
border-right:1px solid #f7f3f0;
}

.active{
	background:#93242a !important;
}
</style>

<body>
	<div class="header">
		<div class="header_menu">
			<div class="header_menu">
				<a class="back" href="javascript:history.back();"></a>
				<span>照片墙</span>
				<a class="home" href="./home"></a>
			</div>	
		</div>		
	</div>

	<div class="content">
		<div class="logo">
			<img src="./images/wall.jpg">
		</div>
		
		<div class="nav">
			<ul>
				<li><div><a href="./latestWallImages">最新发布</a></div></li>
				<li><div><a href="./hotestWallImages">热度排序</a></div></li>
					<li class="active">
						<div>
						<a href="javascript:void(0)">上传照片</a>
						</div>
					</li>
	 		</ul>
		</div>	

		<div class="main">
			
 			<div id="uploadContainer">
				<p>请选择要上传的图片</p>	
 				<input type="file" id="wallImagePic" name="wallImagePic">
 				<style>
 				input[type="file"]::-webkit-file-upload-button {position: absolute; left:80px;width: 90px;height: 31px;overflow: hidden;line-height: 99em;background:url(./images/upload.png) no-repeat 0 0;border: 0 none;z-index: 2;}
 				</style>
 			</div>
		</div>
	</div>
</body>

<script>

$("#wallImagePic").change(function(){

    //创建FormData对象
    var data = new FormData();
    //为FormData对象添加数据 
    data.append('imageFile', $('input[type=file]')[0].files[0]);  
    $.ajax({
        url:'<%=request.getContextPath()%>/klh/imageUpload',
        type:'POST',
        data:data,
        cache: false,
        contentType: false,    //不可缺
        processData: false,    //不可缺
        success:function(responseData){
            if(responseData.result==1){
            	var imageUrl = responseData.data.mediumImage.url;
				//$("#uploadContainer").hide();
            	//$('#imgView').attr("src", imageUrl);
            	//$('#picUrl').val(imageUrl);
            	//$('#thumbPicUrl').val(imageUrl);
				//$("#publishContainer").show();
				location.href="./wallPubImage?picUrl="+imageUrl+"&thumbPicUrl="+imageUrl;
            }else{
            	alert(responseData.message);
            }
        }
    });
});

$("#submitBtn").click(function(){
	$("#wallImageForm").submit();
});

</script>

</html>

