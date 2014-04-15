package com.bruce.geekway.dao;

import java.util.List;

import com.bruce.geekway.model.WxArticle;

public interface IWxArticleDao extends IBaseDao<WxArticle, Integer> {
	
    public List<WxArticle> queryArticlesByModuleId(int moduleId);
    
    public List<WxArticle> queryArticlesByModuleId(int moduleId, int limit);

    public List<WxArticle> queryArticlesOutModuleId(int moduleId);

    
}
