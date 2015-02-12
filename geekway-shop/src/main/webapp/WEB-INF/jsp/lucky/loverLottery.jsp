<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>${currentProductSku.name}【美妞儿】</title>


<link href="${pageContext.request.contextPath}/slideby/styles/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/framework.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/owl.carousel.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/owl.theme.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/swipebox.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/colorbox.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/slideby/styles/meiniu.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/jqueryui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/colorbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/snap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/contact.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/framework.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/framework.launcher.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/map.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/slideby/scripts/url.js"></script>

<style>
.change{-webkit-transition:all .7s ease-out .1s; -moz-transition:all .7s ease-out .1s; -o-transition:all .7s ease-out .1s; transition:all .7s ease-out .1s}
#lbox{position:absolute;top:0;left:0; width:100%; max-width:600px; max-height:600px;background-color:#aaa;}
        .changelbox{position:absolute;top:0;left:0;width:18%;height:18%; background-color:#333;-webkit-transition:all .7s ease-out .1s; -moz-transition:all .7s ease-out .1s; -o-transition:all .7s ease-out .1s; transition:all .7s ease-out .1s}
#inbox{position:absolute;top:0;left:0; width:100%; max-width:600px; max-height:600px;z-index:2;}
.random_current {background-color:#fff;}
.changeinbox{position:absolute;top:0;left:0;width:18%;height:18%; }
</style>

<jsp:include page="../inc/baiduAsyncStat.jsp"></jsp:include>

</head>

<body>

<div class="all-elements">

    <div id="content" class="page-content">
    	<div class="page-header">
            <p class="bread-crumb">${product.name}</p>
        </div>
        <div class="content-header">
        	<a href="${pageContext.request.contextPath}/index" class="content-logo"></a>
        </div>
        
        <div class="content"> 
			
			<!-- <div class="decoration"></div>
			<div class="container no-bottom">
            	<div class="section-title">
                	<h4>［美妞儿］红包发放</h4>
                </div>
                <p>
					xxxx
                </p>
            </div> -->
            
            <div class="decoration"></div>
            <div class="container">
                <div id="lotteryContainer">
                	<!--奖品图标区-->
	                <div id="lbox" class="change">
	                
	　　             </div>
	                <!--奖品图标区结束-->
	                <!--光圈-->
	                <div id="inbox" class="change">
	                
	　　             </div>
                </div>
            </div>
			<div class="container">
                <a href="javascript:void(0)" id="lotterySubmitBtn" class="button-big button-green button-fullscreen">开始抽奖</a>
            </div>
            
            <div class="decoration"></div> 
            
            <jsp:include page="../inc/footer.jsp"></jsp:include>
            
        </div>
    </div>  
</div>

</body>

<script>
$("#lotterySubmitBtn").click(function(){
	$(this).text("抽奖中...");
	moneyGo();
})

	function moneyGo() {
		var r = new Racing({
			index : 1, //起始位置
			arr_length : 10, //lbox的数目
			endCycle : 8, //转动圈数
			EndIndex : 1
		//停住的位置
		});
	}
	window.onload = function() {
		var s = new space({
			wh : 600, //box的 宽度
			num : 10, //lbox的 数目
			isFirst : true
		//是否为第一次加载
		});
		s.show();

	};
	window.onresize = function() {
		var sc = new space({
			wh : 600, //box的 宽度
			num : 0, //lbox的 数目 不是第一次加载可为0
			isFirst : false
		//是否为第一次加载
		});
		sc.show();
	};
	/*=====================
	 * 名称：space
	 * 功能：页面大小调整
	 * 参数：奖池最大宽度,跑马灯个数,是否是第一次加载
	 =======================*/
	function space(config) {
		var w = $(window).width();//获取屏幕宽度
		var h = $(window).height();//获取屏幕高度
		this.boxNum = config.num;//跑马灯个数
		this.maxw = config.wh;//奖池最大宽度
		this.isFirst = config.isFirst;
		//是否是第一次加载
		w >= this.maxw ? this.lboxH = this.maxw : this.lboxH = w;
		h > this.lboxH ? this.lboxT = (h - this.lboxH) / 2 : this.lboxT = 0;
		w >= this.maxw ? this.lboxL = (w - this.lboxH) / 2 : this.lboxL = 0;
	}

	space.prototype.show = function() {
		$("#lotteryContainer").css("height", this.lboxH);
		$("#lbox").css("height", this.lboxH);
		$("#lbox").css("top", this.lboxT);
		$("#lbox").css("left", this.lboxL);
		$("#inbox").css("height", this.lboxH);
		$("#inbox").css("top", this.lboxT);
		$("#inbox").css("left", this.lboxL);
		this.showBox();
	}

	space.prototype.showBox = function() {
		var r = 41;
		//目标园的半径    32 + 一半的box长度
		var d = 6.18 / this.boxNum;
		//相邻box相差的角度
		var bTop = 0;
		//初始位置top
		var bLeft = 0;
		//初始位置left
		if (this.isFirst) {
			for (var i = this.boxNum; i > 0; i--) {
				console.log(i);
				bTop = r + r * Math.sin(d * i + 3.14);
				bLeft = r + r * Math.cos(d * i + 3.14);
				$("#lbox").append("<div id='lo_" + i + "' class='changelbox'></div>");
				$("#inbox").append("<div id='in_" + i + "' class='changeinbox'></div>");
				$("#lo_" + i).css("top", bTop + "%");
				$("#lo_" + i).css("left", bLeft + "%");
				$("#in_" + i).css("top", bTop + "%");
				$("#in_" + i).css("left", bLeft + "%");
			}
		}
	}
	/*=====================
	 * 名称：Racing
	 * 功能：跑马机实现
	 * 参数：起始位置，跑马灯个数，转动圈数（建议3圈以上），结束位置
	 =======================*/
	function Racing(config) {
		var index = config.index, //起始位置
		arr_length = config.arr_length, //跑马灯个数
		endCycle = config.endCycle, //转动圈数
		EndIndex = config.EndIndex, //结束位置
		prevIndex = arr_length, //前一位置
		Time = setInterval(Star, 400), //起始速度
		cycle = 0, //当前圈数
		flag = false, //结束转动标志
		quick = 0, //加速位标识
		slow = 0//减速位标识;

		function Star() {
			//跑马灯变速
			if (flag == false) {
				//走五格开始加速
				if (quick == endCycle) {
					clearInterval(Time);
					Speed = 50;
					(EndIndex - (endCycle - 1)) > 0 ? slow = (EndIndex - (endCycle - 1))
							: slow = ((endCycle - 2) + EndIndex);
					Time = setInterval(Star, Speed);
				}
				//跑N圈减速
				if (cycle == endCycle - 1 && index == slow) {
					clearInterval(Time);
					Speed = 300;
					flag = true;
					//触发结束
					Time = setInterval(Star, Speed);
				}
			}

			//转满1圈index归1
			if (index > arr_length) {
				index = 1;
				cycle++;
			}

			if (flag == true && index == EndIndex) {
				quick = 0;
				clearInterval(Time);
				$("#lotterySubmitBtn").text("您中奖了");
			}
			$("#in_" + index).addClass('random_current');
			//设置当前选中样式
			if (index > 1)
				prevIndex = index - 1;
			else {
				prevIndex = arr_length;
			}
			$("#in_" + prevIndex).removeClass('random_current');
			//取消上次选择样式
			index++;
			quick++;
		}

	}
</script>

</html>
