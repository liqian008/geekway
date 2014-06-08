package com.bruce.geekway.dao;

import java.util.List;

import com.bruce.geekway.model.WxMaterialArticle;

public interface IWxMaterialArticleDao extends IBaseDao<WxMaterialArticle, Integer> {
	
	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId);
	
	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId, int limit);

	public List<WxMaterialArticle> queryMaterialArticlesOutNewsId(int newsId);
	
	/*查询commandId对应的素材列表*/
	public List<WxMaterialArticle> queryMaterialArticlesByCommandId(int commandId);
	
	
	
	
	/*查询普通的素材列表*/
	public List<WxMaterialArticle> queryGeneralMaterials();
	
	/*查询关注时素材列表*/
	public List<WxMaterialArticle> querySubscribedMaterials();
	
	/*查询关注状态对应的素材列表*/
	public List<WxMaterialArticle> querySubscribedMaterials(short subscribeStatus);
	
	
	
	
	
}
