<%@ page language="java" contentType="text/html; charset=utf-8"%>


<div id="recommendProductsContainer" class="container">
	<div class="section-title">
		<h4>猜您喜欢</h4>
	</div>
	<!-- 
           <p class="quote-item">
           	<img src="http://jinwanr.qiniudn.com/image/20140930/1_fb315d11ac58521c025082df3e0c4fff.jpg">
               ${product.name}
               <em>
原 价：&nbsp;<span id='originPrice' class='text-highlight highlight-red'><del>3</del></span>元
现 价：&nbsp;<span id='price' class='text-highlight highlight-green'>2</span>元
</em>
           </p>
            -->
</div>
<div class="decoration"></div>


<script>
	recommendProducts();
	function recommendProducts() {
		//置为数据加载状态
		var paramData = {};
		$.post('${pageContext.request.contextPath}/recommendProducts.json',
			paramData, function(data) {
				var result = data.result;
				if (result == 1) {
					$("#recommendProductsContainer").append(
							data.data.html);
				}
		})
	}
</script>