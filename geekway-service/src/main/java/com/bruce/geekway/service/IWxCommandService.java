package com.bruce.geekway.service;

import java.util.List;

import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxMaterialArticle;

public interface IWxCommandService extends IBaseService<WxCommand, Integer>{

//	@Deprecated
//	public WxCommand loadByCode(String eventCode);
	
	public WxCommand loadByCommandType(short commandType, String command);
	
	
	public WxCommand loadOrSave(short commandType, String command);
	
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
	
	/*查询materialId对应的关键词列表*/
	public List<WxCommand> queryCommandsByMaterialId(int materialId);
	
}