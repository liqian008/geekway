package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxMaterialNewsDao;
import com.bruce.geekway.dao.mapper.WxMaterialNewsMapper;
import com.bruce.geekway.model.WxMaterialNews;


@Repository
public class WxMaterialNewsDaoImpl implements IWxMaterialNewsDao, InitializingBean {

     @Autowired
    private WxMaterialNewsMapper wxMaterialNewsMapper;

    @Override
    public int save(WxMaterialNews t) {
        return wxMaterialNewsMapper.insert(t);
    }

    @Override
    public int updateById(WxMaterialNews t) {
        return wxMaterialNewsMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxMaterialNewsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxMaterialNews loadById(Integer id) {
        return wxMaterialNewsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxMaterialNews> queryAll() {
        return wxMaterialNewsMapper.selectByExample(null);
    }

    @Override
    public List<WxMaterialNews> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    @Override
//    public List<WxMaterialNews> queryMaterialssByCommandId(int commandId) {
//        return wxMaterialNewsMapper.queryMaterialssByCommandId(commandId);
//    }
//    
//    @Override
//	public List<WxMaterialNews> queryMaterialssByCommandId(int commandId, int limit){
//    	return wxMaterialNewsMapper.queryMaterialssByCommandIdLimit(commandId, limit);
//	}
//    
//    @Override
//    public List<WxMaterialNews> queryMaterialssOutCommandId(int commandId) {
//        return wxMaterialNewsMapper.queryMaterialssOutCommandId(commandId);
//    }
//    
//	public WxMaterialNewsMapper getWxMaterialNewsMapper() {
//		return wxMaterialNewsMapper;
//	}

	public void setWxMaterialNewsMapper(WxMaterialNewsMapper wxMaterialNewsMapper) {
		this.wxMaterialNewsMapper = wxMaterialNewsMapper;
	}

	

}
