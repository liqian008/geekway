package com.bruce.geekway.utils;

import java.util.List;

import com.bruce.geekway.model.WxProductSku;

/**
 * html工具，用于ajax
 * 
 * @author liqian TODO 条件允许的话，最好改用freemarker，更灵活
 */
public class ProductHtmlUtils {

	/**
	 * 构造正文页的专辑html
	 * 
	 * @param albumList
	 * @param column
	 * @return
	 */
	public static String buildFallLoadHtml(List<WxProductSku> productSkuList) {
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
			sb.append("<img class='responsive-image' src='" + "/geekway-pay/slideby/images/general-nature/2s.jpg" + "'");
			sb.append("</a>");
			sb.append("<h4>"+productSku.getName()+"</h4>");
			sb.append("<ul id='choose'>");
			sb.append("<li>原 价：&nbsp;<span id='price' class='text-highlight highlight-dark'><del>" + productSku.getOriginPrice() + "</del></span>元</li>");
			sb.append("<li>现 价：&nbsp;<span id='price' class='text-highlight highlight-red'>" + productSku.getPrice() + "</span>元</li>");
			sb.append("</ul>");
			sb.append("</div>");
			return sb.toString();
		}
		return null;
	}

}
