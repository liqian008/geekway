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
    private WxCustomizeMenuMapper wxCustomizeMenuMapper;

    @Override
    public int save(WxCustomizeMenu t) {
        return wxCustomizeMenuMapper.insert(t);
    }

    @Override
    public int updateById(WxCustomizeMenu t) {
        return wxCustomizeMenuMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxCustomizeMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxCustomizeMenu loadById(Integer id) {
        return wxCustomizeMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxCustomizeMenu> queryAll() {
        return wxCustomizeMenuMapper.selectByExample(null);
    }

    @Override
    public List<WxCustomizeMenu> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
	public List<WxCustomizeMenu> queryChildrenMenus(int parentId) {
    	WxCustomizeMenuCriteria criteria = new WxCustomizeMenuCriteria();
    	criteria.createCriteria().andParentIdEqualTo(parentId);
    	List<WxCustomizeMenu> menuList =  wxCustomizeMenuMapper.selectByExample(criteria);
    	return menuList;
	}
    
    @Override
	public List<WxCustomizeMenu> querySortedMenus() {
    	WxCustomizeMenuCriteria criteria = new WxCustomizeMenuCriteria();
    	criteria.createCriteria();
    	criteria.setOrderByClause(" sort, id");
    	List<WxCustomizeMenu> menuList =  wxCustomizeMenuMapper.selectByExample(criteria);
    	return menuList;
	}
    
    @Override
    public void afterPropertiesSet() throws Exception {
    }

	public WxCustomizeMenuMapper getWxCustomizeMenuMapper() {
		return wxCustomizeMenuMapper;
	}

	public void setWxCustomizeMenuMapper(WxCustomizeMenuMapper wxCustomizeCodeMapper) {
		this.wxCustomizeMenuMapper = wxCustomizeCodeMapper;
	}

}
