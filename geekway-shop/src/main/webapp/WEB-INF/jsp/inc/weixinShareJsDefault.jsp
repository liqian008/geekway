<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.foundation.model.share.*" %>
<%@ page import="com.bruce.geekway.utils.*" %>


<%
GenericSharedInfo.WxSharedInfo wxSharedInfo = WxShareUtil.WX_SHARE_INFO_DEFAULT;
System.out.println("title: "+wxSharedInfo.getTitle());
System.out.println("desc: "+wxSharedInfo.getContent());
System.out.println("url: "+wxSharedInfo.getIconUrl());
System.out.println("link: "+wxSharedInfo.getLink());
%>
<script>

// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    // 发送给好友
    WeixinJSBridge.on('menu:share:appmessage', function(argv){
    	WeixinJSBridge.invoke('sendAppMessage',{
            "title": '<%=wxSharedInfo.getTitle()%>',
            "desc": '<%=wxSharedInfo.getContent()%>',
        	"img_url": '<%=wxSharedInfo.getIconUrl()%>',
            "link": '<%=wxSharedInfo.getLink()%>'
        	//"img_height": imageHeight,
            //"img_width": imageWidth
        }, function(res) {//分享给朋友成功后的回调
        	if(res.err_msg=="send_app_msg:ok"){
        		alert("发送给朋友成功");
        	}
        });
    });
    // 分享到朋友圈
    WeixinJSBridge.on('menu:share:timeline', function(argv){
    	WeixinJSBridge.invoke('shareTimeline',{
    		"title": '<%=wxSharedInfo.getTitle()%>',
            "desc": '<%=wxSharedInfo.getContent()%>',
        	"img_url": '<%=wxSharedInfo.getIconUrl()%>',
            "link": '<%=wxSharedInfo.getLink()%>'
        	//"img_height": imageHeight,
            //"img_width": imageWidth
        }, function(res) {//分享给朋友成功后的回调
        	if(res.err_msg=="share_timeline:ok"){
        		alert("分享到朋友圈成功");
        	}
        });
    });
}, false);
</script>