package com.bruce.geekway.utils;

import java.util.List;

import com.bruce.foundation.util.DateUtil;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductVoucher;

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
	public static String buildFallLoadProductHtml(List<WxProductSku> productSkuList) {
		// TODO freemarker template
		if (productSkuList != null && productSkuList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (WxProductSku productSku : productSkuList) {
				i++;
				sb.append(buildProductItemHtml(productSku, i % 2));
			}
			return sb.toString();
		}
		return "";
	}

	private static String buildProductItemHtml(WxProductSku productSku, int mod) {
		String lastColumnCss = mod == 0 ? " last-column" : ""; 

		if (productSku != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<div class='portfolio-item-thumb one-half " + lastColumnCss + "'>");
			sb.append("<a href='" + ProductLinkUtil.getProductSkuLink(productSku.getProductId()) + "'>");
			sb.append("<img class='responsive-image' src='" + UploadUtil.getQiniuResizeImageUrl(productSku.getSkuPicUrl(), 300, 0) + "'");
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
	public static String buildFallLoadVouchersHtml(List<WxProductVoucher> productVoucherList) {
		// TODO freemarker template
		if (productVoucherList != null && productVoucherList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (WxProductVoucher productVoucher : productVoucherList) {
				i++;
				sb.append(buildVoucherItemHtml(productVoucher));
			}
			return sb.toString();
		}
		return "";
	}
	
	private static String buildVoucherItemHtml(WxProductVoucher productVoucher) {
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
	public static String buildFallLoadOrdersHtml(List<WxProductOrder> productOrderList) {
		// TODO freemarker template
		if (productOrderList != null && productOrderList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (WxProductOrder productOrder : productOrderList) {
				i++;
				sb.append(buildOrderItemHtml(productOrder));
			}
			return sb.toString();
		}
		return "";
	}
	
	private static String buildOrderItemHtml(WxProductOrder productOrder) {
		if (productOrder != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<div>");
			sb.append("<p class='quote-item'>");
			sb.append("订单信息： 编号 "+productOrder.getTitle()+" | 金额 "+productOrder.getTotalFee()+"元");
			sb.append("<em></em>");
			sb.append("</p>");
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
	public static String buildRecommendProductsHtml(List<WxProduct> productList) {
		// TODO freemarker template
		if (productList != null && productList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (WxProduct product : productList) {
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
	private static String buildRecommendProductItemHtml(WxProduct product) {
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
			sb.append("<a href='" + ProductLinkUtil.getProductSkuLink(product.getId()) + "'>");
			sb.append("<img src='"+UploadUtil.getQiniuResizeImageUrl(product.getProductPic1Url(), 100, 0) +"'>");
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
