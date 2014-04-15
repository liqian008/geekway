package com.bruce.geekway.utils;


public class ArticleLinkUtil {

	private static final String ARTICLE_INFO_URL = ConfigUtil.getString("geekway_article_link");
	
	private static final String ARTICLE_MODULE_URL = ConfigUtil.getString("geekway_module_link");
	
	/**
	 * 获取article链接
	 * @param articleId
	 * @return
	 */
	public static String getArticleLink(int articleId){
		String link = String.format(ARTICLE_INFO_URL, articleId);
		return link;
	}
	
	
	/**
	 * 获取article链接
	 * @param articleId
	 * @return
	 */
	public static String getArticlesLink(int moduleId){
		String link = String.format(ARTICLE_MODULE_URL, moduleId);
		return link;
	}
	
	public static void main(String[] args) {
		String link = String.format("aaa%sabbb", 1);
		System.out.println(link);
	}
}
