package com.bruce.geekway.dao;

import com.bruce.geekway.model.WxCommand;

public interface IWxCommandDao extends IBaseDao<WxCommand, Integer> {

	@Deprecated
	public WxCommand loadByCode(String eventCode);

	public WxCommand loadByCommandType(short commandType,String command);

	/**
	 * 变更对应的单图文Id
	 * @param commandId
	 * @param articleId
	 * @param materialType
	 * @return
	 */
	public int updateMaterialArticle(int commandId, int articleId, short materialType);

	/**
	 * 变更对应的多图文Id
	 * @param commandId
	 * @param newsId
	 * @param rowLimit
	 * @param materialType
	 * @return
	 */
	public int updateMaterialNews(int commandId, int newsId, short rowLimit, short materialType);
	
}
