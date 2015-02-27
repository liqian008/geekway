<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>
<%@ page import="org.apache.commons.lang3.*" %>

<%!
int getMatchResult(WxWebUser wxWebUser1, WxWebUser wxWebUser2){
	if(wxWebUser1!=null&&StringUtils.isNotBlank(wxWebUser1.getOpenId())&&wxWebUser2!=null&&StringUtils.isNotBlank(wxWebUser2.getOpenId())){
		String openId1=wxWebUser1.getOpenId();
		String openId2=wxWebUser2.getOpenId();
		if(openId1!=null&&openId2!=null){
			List<String> openIdList = Arrays.asList(openId1, openId2);
			Collections.sort(openIdList);
			int hashcode = Math.abs((openId1+openId2).hashCode());
			return hashcode%14;
		}
	}
	return 0;
}
%>

<%
WxWebUser wxWebUser1 = (WxWebUser)request.getAttribute("wxWebUser1");
WxWebUser wxWebUser2 = (WxWebUser)request.getAttribute("wxWebUser2");
int luckyNumber = getMatchResult(wxWebUser1, wxWebUser2);

String[][] descriptions = new String[][]{
		new String[]{"99","天生一对，佳偶天成。你们是如此的匹配，如此和谐，往往是你刚想说“可惜”他已经开始叹惜。你们性格相合，有共同的爱好，品味一致。你们是别人眼中的金童玉女，走到一起是天经地义的事。"},
		new String[]{"95","你们会是众人眼中的神仙眷侣，怎么看怎么般配，很有传说中的夫妻相。温情和默契却能让彼此的心越走越近。"},
		new String[]{"88","你们在看到对方的第一眼就深深地被吸引，并立刻爱上对方，接着就是干柴烈火般的热。她崇拜他，同样她也令他着迷。还等什么，立刻拿起手中的电话拨打：400-820-3333吧！"},
		new String[]{"80","此屌丝已经对你暗恋已久，却一直都无法与产生交集，其实你也被他吸引，如果还在苦恼怎么打开局面的话，请给他一个修电脑的机会吧。。。"},
		new String[]{"85","你们都受过感情的伤，你们都一样脆弱而敏感。同样的经历与伤痛，让彼此更容易靠近。你们的感情在相互疗伤中一点一点加深，你们的心痊愈了，爱情也圆满了。"},
		new String[]{"82","一个似水，平和，内殓。一个如火，热情，洒脱。两个性情迥异的人一样可以找到共同点，一样可以谱写爱曲。"},
		new String[]{"78","你们太熟悉彼此，太了解对方了，所以很难擦出火花。女汉子一样的性格导致你们的恋爱和搞基一样，其实做朋友也很不错。"},
		new String[]{"68","你是风儿我是渣，屌丝想逆袭也没那么简单的，差距这么大，还是早点推到的好。"},
		new String[]{"75","共同的爱好让你们惺惺相惜，八卦使你们更加了解对方。但一定要管好嘴巴哦，莫愁前路无知己，谁都可能弄死你。。。"},
		new String[]{"99","青梅竹马，两小无猜。你们从穿开裆裤时就在一起。一起进幼儿园，一起上小学，一起闯祸后对老师撒谎。直到大学一起洗澡，你捡起了肥皂。。。"},
		new String[]{"90","太阳光芒万丈却不及蜡烛只为一个人光亮。"},
		new String[]{"80","她喜欢你，你却爱着男人。她默默的陪在你身边，将这份心意藏在心底，她是你最好的朋友，也是你唯一的朋友。"},
		new String[]{"88","一样的发型和服装、我只愿意和你撞衫。"},
		new String[]{"72","我住长江头,君住长江尾;日日思君不见君,共饮长江水——距离不但产生美，也产生小三。"},
		new String[]{"75","距离不是难题，心的距离才是利器；时间不是苦药，爱的时间才是真谛。"}
		};

%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>匹配结果 - 【美妞儿】</title>

<link href="${pageContext.request.contextPath}/slideby/styles/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/framework.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/meiniu.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/jqueryui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/framework.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/url.js"></script>


<style>
.responsive-image{
border-radius:10px;
}

.matchResultContainer{
display:none;
border:dotted #7798cb;
border-radius:10px;
padding:12px;
}

.matchResultContainer h4{
color:#7798cb
}
</style>

<jsp:include page="../../inc/jssdk.jsp"></jsp:include> 
<jsp:include page="../../inc/baiduAsyncStat.jsp"></jsp:include>

</head>

<body>

<div class="all-elements">

    <div id="content" class="page-content">
    	<div class="page-header">
    		<a href="#" class="deploy-sidebar"></a>
            <p class="bread-crumb">情侣匹配度结果</p> 
            <a href="javascript:void(0)" class="deploy-refresh"></a>
        </div>
        <div class="content-header">
        	<a href="http://mp.weixin.qq.com/s?__biz=MjM5Njc5NzEzNw==&mid=203236144&idx=1&sn=2e433cd14b5b877c9a9f608f9d906ad2#rd" class="content-logo"></a>
        </div>
        
        <div class="content"> 
			
			<div class="decoration"></div>
			<div class="container no-bottom">
            	<div class="big-notification blue-notification"> 
					<h4>［${wxWebUser1.nickname}］与［${wxWebUser2.nickname}］情侣匹配度结果</h4>
					<p>
						“发送给朋友”或“分享到朋友圈”，可与好友进行匹配度测试！<br/>
						更多有趣内容，请点击关注<a href="http://mp.weixin.qq.com/s?__biz=MjM5Njc5NzEzNw==&mid=203236144&idx=1&sn=2e433cd14b5b877c9a9f608f9d906ad2#rd" style="color:#efe;font-size:14px"><strong>［美妞儿］</strong></a>公众账号</strong>
					</p>
				</div>
            </div>
            
			<div class="container no-bottom">
            	<div class="one-half center-text">
					<img class="responsive-image" src="${wxWebUser1.headImgUrl}">
					<h2>${wxWebUser1.nickname}</h2>
				</div>
				<div class="center-text">
					<img class="responsive-image" src="${wxWebUser2.headImgUrl}">
					<h2>${wxWebUser2.nickname}</h2>
				</div>
			</div>
			<div class="decoration"></div>
			<div class="container">
	        	<div id="result0" class="matchResultContainer container">
					<h4>匹配度 <%=descriptions[luckyNumber][0]%>分</h4>
					<h4><%=descriptions[luckyNumber][1]%></h4>
					<p style="margin-bottom:0px">友情提醒：给出的测试结果仅供参考，并不能作为您选择伴侣或者您择偶的标准.</p>
				</div>
				<a href="./loverMatchIntro" id="matchSubmitBtn" class="button-big button-green button-fullscreen">重新开始游戏</a>
	        
	        </div>
            <div class="decoration"></div>
            
            <jsp:include page="../../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>

</body>

<script>
$("#result0").show();

	wx.ready(function() {
		wx.onMenuShareAppMessage({//分享给朋友
			title : "［${wxWebUser1.nickname}］与［${wxWebUser2.nickname}］情侣匹配度结果 - 【美妞儿】",
			desc : "分数：<%=descriptions[luckyNumber][0]%>，<%=descriptions[luckyNumber][1]%>",
			link : "http://wx.meiniur.com/activities/loverMatchResult?openIds=${wxWebUser1.openId}&openIds=${wxWebUser2.openId}",
			imgUrl :  "${_currentWxUser.headImgUrl}",
			trigger : function(res) {
			},
			success : function(res) {
			},
			cancel : function(res) {
			},
			fail : function(res) {
			}
		});
		
		wx.onMenuShareTimeline({//朋友圈分享
			title : "［${wxWebUser1.nickname}］与［${wxWebUser2.nickname}］情侣匹配度结果 - 【美妞儿】",
			desc : "分数：<%=descriptions[luckyNumber][0]%>，<%=descriptions[luckyNumber][1]%>",
			link : "http://wx.meiniur.com/activities/loverMatchResult?openId1=${wxWebUser1.openId}&openId2=${wxWebUser2.openId}",
			imgUrl :  "${_currentWxUser.headImgUrl}",
			trigger : function(res) {
			},
			success : function(res) {
			},
			cancel : function(res) {
			},
			fail : function(res) {
			}
		});
	});
</script>

</html>
