package com.bruce.geekway.service;

import java.util.List;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialArticleCriteria;

public interface IWxMaterialArticleService extends IFoundationPagingService<WxMaterialArticle, Integer, WxMaterialArticleCriteria>{

	/*查询commandId对应的素材列表*/
	public List<WxMaterialArticle> queryMaterialArticlesByCommandId(int commandId);
	
	public List<WxMaterialArticle> querySubscribedMaterials();

	/**
	 * 根据状态查询关注素材
	 * @param subscribeStatus 1为首次关注，2为重复关注
	 * @return
	 */
	public List<WxMaterialArticle> querySubscribedMaterials(short subscribeStatus);
	
	
	
	/*查询普通的素材列表*/
	public List<WxMaterialArticle> queryGeneralMaterials();
	
//	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId);
//	
//	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId, int limit);
//
//	public List<WxMaterialArticle> queryMaterialArticlesOutNewsId(int newsId);
}