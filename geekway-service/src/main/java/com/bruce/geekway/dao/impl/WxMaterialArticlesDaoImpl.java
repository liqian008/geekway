package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxMaterialArticlesDao;
import com.bruce.geekway.dao.mapper.WxMaterialArticlesMapper;
import com.bruce.geekway.model.WxMaterialArticles;


@Repository
public class WxMaterialArticlesDaoImpl implements IWxMaterialArticlesDao, InitializingBean {

     @Autowired
    private WxMaterialArticlesMapper wxMaterialArticlesMapper;

    @Override
    public int save(WxMaterialArticles t) {
        return wxMaterialArticlesMapper.insert(t);
    }

    @Override
    public int updateById(WxMaterialArticles t) {
        return wxMaterialArticlesMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxMaterialArticlesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxMaterialArticles loadById(Integer id) {
        return wxMaterialArticlesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxMaterialArticles> queryAll() {
        return wxMaterialArticlesMapper.selectByExample(null);
    }

    @Override
    public List<WxMaterialArticles> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    @Override
//    public List<WxMaterialArticles> queryMaterialssByCommandId(int commandId) {
//        return wxMaterialArticlesMapper.queryMaterialssByCommandId(commandId);
//    }
//    
//    @Override
//	public List<WxMaterialArticles> queryMaterialssByCommandId(int commandId, int limit){
//    	return wxMaterialArticlesMapper.queryMaterialssByCommandIdLimit(commandId, limit);
//	}
//    
//    @Override
//    public List<WxMaterialArticles> queryMaterialssOutCommandId(int commandId) {
//        return wxMaterialArticlesMapper.queryMaterialssOutCommandId(commandId);
//    }
//    
//	public WxMaterialArticlesMapper getWxMaterialArticlesMapper() {
//		return wxMaterialArticlesMapper;
//	}

	public void setWxMaterialArticlesMapper(WxMaterialArticlesMapper wxMaterialArticlesMapper) {
		this.wxMaterialArticlesMapper = wxMaterialArticlesMapper;
	}

	

}
