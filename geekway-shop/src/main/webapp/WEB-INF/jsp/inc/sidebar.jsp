<%@ page language="java" contentType="text/html; charset=utf-8"%>

<div id="sidebar" class="page-sidebar">
	<div class="page-sidebar-scroll">
		<div class="sidebar-section">
			<p>美妞儿</p>
			<a href="javascript:void(0)" class="sidebar-close"></a>
		</div>
		<div class="sidebar-header">
			<a href="${pageContext.request.contextPath}/index" class="sidebar-logo"></a>
			<!-- 分享 
                <a href="http://www.facebook.com/enabled.labs" class="facebook-sidebar"></a>
                <a href="https://twitter.com/iEnabled" class="twitter-sidebar"></a>
                 -->
		</div>

		<div class="navigation-items">
			<div class="nav-item">
				<a href="${pageContext.request.contextPath}/products/c-1" class="home-nav">双11促销<em></em></a>
			</div>
			<div class="nav-item">
				<a href="${pageContext.request.contextPath}/products/t-1" class="home-nav">热销爆款<em></em></a>
			</div>
			<div class="nav-item">
				<a href="./#" class="features-nav submenu-deploy">韩版<em></em></a>
				<div class="nav-item-submenu" style="overflow: hidden; display: block;">
					<a href="${pageContext.request.contextPath}/products/t-1">Introduction <em class="unselected-sub-nav"></em></a>
					<a href="${pageContext.request.contextPath}/products/t-1">Typography<em class="unselected-sub-nav"></em></a>
					<a href="${pageContext.request.contextPath}/products/t-1">jQuery <em class="unselected-sub-nav"></em></a>
				</div>
			</div>
			<!-- 
			<div class="nav-item">
				<a href="./#" class="media-nav submenu-deploy">我的清单<em></em></a>
				<div class="nav-item-submenu" style="overflow: hidden; display: block;">
					<a href="onefolio.html">我的订单<em class="unselected-sub-nav"></em></a>
					<a href="widefolio.html">我的优惠券<em class="unselected-sub-nav"></em></a>
				</div> 
			</div>
			 -->
			<div class="nav-item">
				<a href="${pageContext.request.contextPath}/orders" class="home-order">我的订单<em></em></a>
			</div>
			<div class="nav-item">
				<a href="${pageContext.request.contextPath}/vouchers" class="home-voucher">我的优惠券<em></em></a>
			</div>
			<!-- 
			<div class="nav-item">
				<a href="contact.html" class="contact-nav">联系我们<em></em></a>
			</div>
			<div class="nav-item">
				<a href="./#" class="close-nav">关 闭<em class="unselected-nav"></em></a>
			</div>
			 -->
			<div class="sidebar-decoration"></div>
		</div>


		<div class="sidebar-section copyright-sidebar">
			<p>Copyright 2014. All rights reserved.</p>
		</div>
	</div>
</div>
