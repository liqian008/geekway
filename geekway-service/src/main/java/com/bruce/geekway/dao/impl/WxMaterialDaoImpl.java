package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxMaterialDao;
import com.bruce.geekway.dao.mapper.WxMaterialMapper;
import com.bruce.geekway.model.WxMaterial;


@Repository
public class WxMaterialDaoImpl implements IWxMaterialDao, InitializingBean {

     @Autowired
    private WxMaterialMapper wxMaterialMapper;

    @Override
    public int save(WxMaterial t) {
        return wxMaterialMapper.insert(t);
    }

    @Override
    public int updateById(WxMaterial t) {
        return wxMaterialMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxMaterialMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxMaterial loadById(Integer id) {
        return wxMaterialMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxMaterial> queryAll() {
        return wxMaterialMapper.selectByExample(null);
    }

    @Override
    public List<WxMaterial> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public List<WxMaterial> queryMaterialsByCommandId(int commandId) {
        return wxMaterialMapper.queryMaterialsByCommandId(commandId);
    }
    
    @Override
	public List<WxMaterial> queryMaterialsByCommandId(int commandId, int limit){
    	return wxMaterialMapper.queryMaterialsByCommandIdLimit(commandId, limit);
	}
    
    @Override
    public List<WxMaterial> queryMaterialsOutCommandId(int commandId) {
        return wxMaterialMapper.queryMaterialsOutCommandId(commandId);
    }
    
	public WxMaterialMapper getWxMaterialMapper() {
		return wxMaterialMapper;
	}

	public void setWxMaterialMapper(WxMaterialMapper wxMaterialMapper) {
		this.wxMaterialMapper = wxMaterialMapper;
	}

	

}
