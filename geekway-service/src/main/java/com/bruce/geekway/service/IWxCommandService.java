package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxCommandCriteria;

public interface IWxCommandService extends IFoundationPagingService<WxCommand, Integer, WxCommandCriteria>{

	/*查询用户重复关注时的指令*/
	public WxCommand loadByCommand(String key);
	
	/*查询用户关注时的指令*/
	public WxCommand loadNewSubscribedCommand();
	
	
	/*查询用户重复关注时的指令*/
	public WxCommand loadReSubscribedCommand();
	
	
	
	
//	@Deprecated
//	public WxCommand loadByCode(String eventCode);
	
//	public WxCommand loadByCommandType(short commandType, String command);
//	
//	
//	public WxCommand loadOrSave(short commandType, String command);
	
//	/**
//	 * 变更对应的单图文Id
//	 * @param commandId
//	 * @param articleId
//	 * @return
//	 */
//	public int updateMaterialArticle(int commandId, int articleId);
//	
//	/**
//	 * 变更对应的多图文Id
//	 * @param commandId
//	 * @param newsId
//	 * @return
//	 */
//	public int updateMaterialNews(int commandId, int newsId);
	
	/*查询materialId对应的关键词列表*/
//	public List<WxCommand> queryCommandsByMaterialId(int materialId);
	
	
	
	
	
}