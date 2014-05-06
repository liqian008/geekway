package com.bruce.geekway.utils;


public class MaterialLinkUtil {

	private static final String ARTICLE_INFO_URL = ConfigUtil.getString("geekway_material_link");
	
	private static final String ARTICLE_GROUP_URL = ConfigUtil.getString("geekway_material_group_link");
	
	/**
	 * 获取material链接
	 * @param materialId
	 * @return
	 */
	public static String getMaterialLink(int materialId){
		String link = String.format(ARTICLE_INFO_URL, materialId);
		return link;
	}
	
	
	/**
	 * 获取material链接
	 * @param commandId
	 * @return
	 */
	public static String getMaterialsLink(int commandId){
		String link = String.format(ARTICLE_GROUP_URL, commandId);
		return link;
	}
	
	public static void main(String[] args) {
		String link = String.format("aaa%sabbb", 1);
		System.out.println(link);
	}
}
