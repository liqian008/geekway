package com.bruce.geekway.service;

import com.bruce.geekway.model.WxCommand;

public interface IWxCommandService extends IBaseService<WxCommand, Integer>{

//	@Deprecated
//	public WxCommand loadByCode(String eventCode);
	
	public WxCommand loadByCommandType(short commandType, String command);
	
	/**
	 * 变更对应的单图文Id
	 * @param commandId
	 * @param articleId
	 * @return
	 */
	public int updateMaterialArticle(int commandId, int articleId);
	
	/**
	 * 变更对应的多图文Id
	 * @param commandId
	 * @param newsId
	 * @return
	 */
	public int updateMaterialNews(int commandId, int newsId);
	
	
}