package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxCustomizeMenuDao;
import com.bruce.geekway.dao.mapper.WxCustomizeMenuMapper;
import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.WxCustomizeMenuCriteria;

@Repository
public class WxCustomizeMenuDaoImpl implements IWxCustomizeMenuDao, InitializingBean {

     @Autowired
    private WxCustomizeMenuMapper wxCustomizeCodeMapper;

    @Override
    public int save(WxCustomizeMenu t) {
        return wxCustomizeCodeMapper.insert(t);
    }

    @Override
    public int updateById(WxCustomizeMenu t) {
        return wxCustomizeCodeMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxCustomizeCodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxCustomizeMenu loadById(Integer id) {
        return wxCustomizeCodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxCustomizeMenu> queryAll() {
        return wxCustomizeCodeMapper.selectByExample(null);
    }

    @Override
    public List<WxCustomizeMenu> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
	public WxCustomizeMenu loadByCode(String menuCode) {
    	WxCustomizeMenuCriteria criteria = new WxCustomizeMenuCriteria();
    	criteria.createCriteria().andMenuCodeEqualTo(menuCode);
    	List<WxCustomizeMenu> textCodeList =  wxCustomizeCodeMapper.selectByExample(criteria);
    	if(textCodeList!=null&&textCodeList.size()>0){
    		return textCodeList.get(0);
    	}
    	return null;
	}
    
    @Override
    public void afterPropertiesSet() throws Exception {
    }

	public WxCustomizeMenuMapper getWxCustomizeMenuMapper() {
		return wxCustomizeCodeMapper;
	}

	public void setWxCustomizeMenuMapper(WxCustomizeMenuMapper wxCustomizeCodeMapper) {
		this.wxCustomizeCodeMapper = wxCustomizeCodeMapper;
	}

}
