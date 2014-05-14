package com.bruce.geekway.utils;


public class MaterialLinkUtil {

	private static final String WX_ARTICLE_URL = ConfigUtil.getString("geekway_material_article_link");
	
	private static final String WX_NEWS_URL = ConfigUtil.getString("geekway_material_news_link");
	
	/**
	 * 获取material链接
	 * @param materialId
	 * @return
	 */
	public static String getMaterialLink(int materialId){
		String link = String.format(WX_ARTICLE_URL, materialId);
		return link;
	}
	
	
	/**
	 * 获取material链接
	 * @param commandId
	 * @return
	 */
	public static String getMaterialsLink(int commandId){
		String link = String.format(WX_NEWS_URL, commandId);
		return link;
	}
	
	public static void main(String[] args) {
		String link = String.format("aaa%sabbb", 1);
		System.out.println(link);
	}
}
