package com.bruce.geekway.utils;

import java.util.List;

import com.bruce.foundation.util.DateUtil;
import com.bruce.geekway.model.Product;
import com.bruce.geekway.model.ProductOrder;
import com.bruce.geekway.model.ProductSku;
import com.bruce.geekway.model.ProductVoucher;

/**
 * html工具，用于ajax
 * 
 * @author liqian TODO 条件允许的话，最好改用freemarker，更灵活
 */
public class HtmlBuildUtils {
	
	/**
	 * 构造产品列表的html
	 * 
	 * @param productSkuList
	 * @return
	 */
	public static String buildFallLoadProductHtml(List<Product> productList) {
		// TODO freemarker template
		if (productList != null && productList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (Product product : productList) {
				i++;
				sb.append(buildProductItemHtml(product, i % 2));
			}
			return sb.toString();
		}
		return "";
	}

	private static String buildProductItemHtml(Product product, int mod) {
		String lastColumnCss = mod == 0 ? " last-column" : ""; 
		if (product != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<div class='portfolio-item-thumb one-half " + lastColumnCss + "'>");
			sb.append("<a href='" + ShopLinkUtil.getProductLink4Mobile(product.getId()) + "'>");
			sb.append("<img class='responsive-image' src='" + UploadUtil.getQiniuResizeImageUrl(product.getCoverPicUrl(), 300, 0) + "'");
			sb.append("</a>");
			sb.append("<h4>"+product.getName()+"</h4>");
			sb.append("<ul id='choose'>");
			sb.append("<li>原 价：&nbsp;<span id='price' class='text-highlight highlight-dark'><del>" + product.getOriginPrice() + "</del></span>元</li>");
			sb.append("<li>现 价：&nbsp;<span id='price' class='text-highlight highlight-red'>" + product.getPrice() + "</span>元</li>");
			sb.append("</ul>");
			sb.append("</div>");
			return sb.toString();
		}
		return "";
	}
	
	
	
	/**
	 * 构造产品列表的html
	 * 
	 * @param productSkuList
	 * @return
	 */
	@Deprecated
	public static String buildFallLoadProductSkuHtml(List<ProductSku> productSkuList) {
		// TODO freemarker template
		if (productSkuList != null && productSkuList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (ProductSku productSku : productSkuList) {
				i++;
				sb.append(buildProductSkuItemHtml(productSku, i % 2));
			}
			return sb.toString();
		}
		return "";
	}

	
	@Deprecated
	private static String buildProductSkuItemHtml(ProductSku productSku, int mod) {
		String lastColumnCss = mod == 0 ? " last-column" : ""; 

		if (productSku != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<div class='portfolio-item-thumb one-half " + lastColumnCss + "'>");
			sb.append("<a href='" + ShopLinkUtil.getProductLink4Mobile(productSku.getProductId()) + "'>");
//			sb.append("<img class='responsive-image' src='" + UploadUtil.getQiniuResizeImageUrl(productSku.getSkuPic1Url(), 300, 0) + "'");
			sb.append("</a>");
			sb.append("<h4>"+productSku.getName()+"</h4>");
			sb.append("<ul id='choose'>");
			sb.append("<li>原 价：&nbsp;<span id='price' class='text-highlight highlight-dark'><del>" + productSku.getOriginPrice() + "</del></span>元</li>");
			sb.append("<li>现 价：&nbsp;<span id='price' class='text-highlight highlight-red'>" + productSku.getPrice() + "</span>元</li>");
			sb.append("</ul>");
			sb.append("</div>");
			return sb.toString();
		}
		return "";
	}
	
	
	/**
	 * 构造优惠券的html
	 * @param productSkuList
	 * @return
	 */
	public static String buildFallLoadVouchersHtml(List<ProductVoucher> productVoucherList) {
		// TODO freemarker template
		if (productVoucherList != null && productVoucherList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (ProductVoucher productVoucher : productVoucherList) {
				i++;
				sb.append(buildVoucherItemHtml(productVoucher));
			}
			return sb.toString();
		}
		return "";
	}
	
	private static String buildVoucherItemHtml(ProductVoucher productVoucher) {
		if (productVoucher != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<div>");
			sb.append("<p class='quote-item'>");
			sb.append("优惠券信息： 编号 "+productVoucher.getVoucherCode()+" | 金额 "+productVoucher.getPrice()+"元 "+" | 状态 "+productVoucher.getStatus());
			sb.append("<em>使用有效期: "+DateUtil.date2YMDHMS(productVoucher.getExpireTime())+"前</em>");
			sb.append("</p>");
			sb.append("</div>");
			return sb.toString();
		}
		return "";
	}
	
	
	/**
	 * 构造优惠券的html
	 * @param productSkuList
	 * @return
	 */
	public static String buildFallLoadOrdersHtml(List<ProductOrder> productOrderList) {
		// TODO freemarker template
		if (productOrderList != null && productOrderList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (ProductOrder productOrder : productOrderList) {
				i++;
				sb.append(buildOrderItemHtml(productOrder));
			}
			return sb.toString();
		}
		return "";
	}
	
	private static String buildOrderItemHtml(ProductOrder productOrder) {
		if (productOrder != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<div>");
			sb.append("<p class='quote-item'>");
			sb.append("订单号： "+productOrder.getOutTradeNo()+"<br/>");
			sb.append("订单信息： "+productOrder.getTitle()+"<br/>");
			sb.append("合计： " +
					"商品 <span class='text-highlight highlight-blue'>"+productOrder.getProductFee()+"</span>&nbsp;" +
						"+&nbsp;运费 <span class='text-highlight highlight-red'>"+productOrder.getTransportFee()+"</span>&nbsp;");
			if(productOrder.getDiscountFee()!=null&&productOrder.getDiscountFee()>0){
				sb.append("+&nbsp;折扣 <span class='text-highlight highlight-dark'>"+productOrder.getDiscountFee()+"</span>&nbsp;");
			}
			if(productOrder.getVoucherFee()!=null&&productOrder.getVoucherFee()>0){
				sb.append("+&nbsp;折扣 <span class='text-highlight highlight-yellow'>"+productOrder.getVoucherFee()+"</span>&nbsp;");
			}
			sb.append("=&nbsp;<span class='text-highlight highlight-green'>"+productOrder.getTotalFee()+"</span>元");
			sb.append("&nbsp;|&nbsp;<span class='text-highlight highlight-dark'><a href='./orderInfo?orderId="+productOrder.getId()+"&tradeNo="+productOrder.getOutTradeNo()+"'>查看详情</a></span>");
			sb.append("</p>");
//			sb.append("<p class='quote-item'>");
//			sb.append("订单信息： "+productOrder.getTitle()+" | 商品 "+productOrder.getProductFee()+"元，运费 "+productOrder.getTransportFee()+"元，合计 "+productOrder.getTotalFee()+"元");
//			sb.append("<em></em>");
//			sb.append("</p>");
			sb.append("</div>");
			return sb.toString();
		}
		return "";
	}
	
	
	/**
	 * 构造推荐产品的html
	 * @param productSkuList
	 * @return
	 */
	public static String buildRecommendProductsHtml(List<Product> productList) {
		// TODO freemarker template
		if (productList != null && productList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (Product product : productList) {
				i++;
				sb.append(buildRecommendProductItemHtml(product));
			}
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 构造系统推荐商品
	 * @param product
	 * @return
	 */
	private static String buildRecommendProductItemHtml(Product product) {
		if (product != null) {
			StringBuilder sb = new StringBuilder();
			
//			sb.append("<div class='portfolio-item-thumb one-third'>");
//			sb.append("<a href='" + ProductLinkUtil.getProductSkuLink(product.getId()) + "'>");
//			sb.append("<img class='responsive-image' src='"+product.getProductPic1Url()+"'>");
//			sb.append("</a>");
//			sb.append("<h4>"+product.getName()+"</h4>");
//			sb.append("<ul>");
//			sb.append("<li>原 价：&nbsp;<span id='originPrice' class='text-highlight highlight-red'><del>"+product.getOriginPrice()+"</del></span>元</li>");
//			sb.append("<li>现 价：&nbsp;<span id='price' class='text-highlight highlight-green>"+product.getPrice()+"</span>元</li>");
//			sb.append("</ul>");
//			sb.append("</div>");
			
			sb.append("<p class='quote-item'>");
			sb.append("<a href='" + ShopLinkUtil.getProductLink4Mobile(product.getId()) + "'>");
			sb.append("<img src='"+UploadUtil.getQiniuResizeImageUrl(product.getCoverPicUrl(), 100, 0) +"'>");
			sb.append("</a>");
			sb.append(product.getName());
			sb.append("<em>");
			sb.append("原 价：&nbsp;<span id='originPrice' class='text-highlight highlight-red'><del>3</del></span>元");
			sb.append("，现 价：&nbsp;<span id='price' class='text-highlight highlight-green'>2</span>元");
			sb.append("</em>");
			sb.append("</p>");
			
			return sb.toString();
		}
		return "";
	}

}
