<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bruce.geekway.model.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>可乐惠</title>
		<link href="img/favicon.ico" rel="shortcut icon" type="image/x-icon">

		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mobile/css/style.css" />

		<script type="text/javascript" src="<%=request.getContextPath()%>/mobile/js/jquery.min.js"></script>
		<!-- 
		<script type="text/javascript" src="js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="js/jquery.easy-pie-chart.js"></script>
		<script type="text/javascript" src="js/o-script.js"></script>
		 -->

	</head>
	<body class="o-page">
		<div id="page">
			<div id="header">
				积分变更记录
				<a class="backBtn" href="javascript:history.back();"></a>
			</div>
			<div id="content">
				
				<%
				List<KlhUserScoreLog> userScoreLogList = (List<KlhUserScoreLog>)request.getAttribute("userScoreLogList");
				if(userScoreLogList!=null&&userScoreLogList.size()>0){
					int i=0;
					for(KlhUserScoreLog userScore: userScoreLogList){
						i++;
				%>	
					<article>
						<h2><%=i%>、积分变更：<%=userScore.getScoreChange()%>分</h2>
						<p>
							变更原因：<%=userScore.getReason() %>
						</p>
					</article>
				<%}
				}%>

			</div>
			<!--<div class="subFooter">Copyright 2013. All rights reserved.</div>-->
		</div>
	</body>
</html>