package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxCodeModuleDao;
import com.bruce.geekway.dao.mapper.WxCodeModuleMapper;
import com.bruce.geekway.model.WxCodeModule;


@Repository
public class WxCodeModuleDaoImpl implements IWxCodeModuleDao, InitializingBean {

     @Autowired
    private WxCodeModuleMapper wxCodeModuleMapper;

    @Override
    public int save(WxCodeModule t) {
        return wxCodeModuleMapper.insert(t);
    }

    @Override
    public int updateById(WxCodeModule t) {
        return wxCodeModuleMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxCodeModuleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxCodeModule loadById(Integer id) {
        return wxCodeModuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxCodeModule> queryAll() {
        return wxCodeModuleMapper.selectByExample(null);
    }

    @Override
    public List<WxCodeModule> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

	public WxCodeModuleMapper getWxCodeModuleMapper() {
		return wxCodeModuleMapper;
	}

	public void setWxCodeModuleMapper(WxCodeModuleMapper wxCodeModuleMapper) {
		this.wxCodeModuleMapper = wxCodeModuleMapper;
	}

}
