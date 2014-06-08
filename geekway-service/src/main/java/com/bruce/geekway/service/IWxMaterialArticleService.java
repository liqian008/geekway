package com.bruce.geekway.service;

import java.util.List;

import com.bruce.geekway.model.WxMaterialArticle;

public interface IWxMaterialArticleService extends IBaseService<WxMaterialArticle, Integer>{

	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId);
	
	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId, int limit);

	public List<WxMaterialArticle> queryMaterialArticlesOutNewsId(int newsId);
	
	/*查询commandId对应的素材列表*/
	public List<WxMaterialArticle> queryMaterialArticlesByCommandId(int commandId);
	
	public List<WxMaterialArticle> querySubscribedMaterials();

	/**
	 * 根据状态查询关注素材
	 * @param subscribeStatus
	 * @return
	 */
	public List<WxMaterialArticle> querySubscribedMaterials(short subscribeStatus);
	
	
	
	/*查询普通的素材列表*/
	public List<WxMaterialArticle> queryGeneralMaterials();
	
}