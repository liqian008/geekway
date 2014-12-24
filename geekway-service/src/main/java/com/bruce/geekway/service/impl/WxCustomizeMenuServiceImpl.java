package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxCustomizeMenuMapper;
import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.WxCustomizeMenuCriteria;
import com.bruce.geekway.service.IWxCustomizeMenuService;

/**
 * 自定义菜单service
 * 
 * @author liqian
 * 
 */
@Service
public class WxCustomizeMenuServiceImpl implements IWxCustomizeMenuService, InitializingBean {

	@Autowired
	private WxCustomizeMenuMapper wxCustomizeMenuMapper;

	@Override
	public int save(WxCustomizeMenu t) {
		return wxCustomizeMenuMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxCustomizeMenu t) {
		return wxCustomizeMenuMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxCustomizeMenu t, WxCustomizeMenuCriteria criteria) {
		return wxCustomizeMenuMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxCustomizeMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxCustomizeMenuCriteria criteria) {
		return wxCustomizeMenuMapper.deleteByExample(criteria);
	}

	@Override
	public WxCustomizeMenu loadById(Integer id) {
		return wxCustomizeMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxCustomizeMenu> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxCustomizeMenu> queryAll(String orderByClause) {
		WxCustomizeMenuCriteria criteria = new WxCustomizeMenuCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxCustomizeMenu> queryByCriteria(WxCustomizeMenuCriteria criteria) {
		return wxCustomizeMenuMapper.selectByExample(criteria);
	}

	@Override
	public List<WxCustomizeMenu> queryChildrenMenus(int parentId) {
		WxCustomizeMenuCriteria criteria = new WxCustomizeMenuCriteria();
		criteria.createCriteria().andParentIdEqualTo(parentId);
		List<WxCustomizeMenu> menuList = wxCustomizeMenuMapper.selectByExample(criteria);
		return menuList;
	}

	@Override
	public List<WxCustomizeMenu> querySortedMenus() {
		// WxCustomizeMenuCriteria criteria = new WxCustomizeMenuCriteria();
		// criteria.createCriteria();
		// criteria.setOrderByClause(" sort, id");
		// List<WxCustomizeMenu> menuList =
		// wxCustomizeMenuMapper.selectByExample(criteria);
		// return menuList;

		return queryAll(" sort, id");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	public WxCustomizeMenuMapper getWxCustomizeMenuMapper() {
		return wxCustomizeMenuMapper;
	}

	public void setWxCustomizeMenuMapper(WxCustomizeMenuMapper wxCustomizeMenuMapper) {
		this.wxCustomizeMenuMapper = wxCustomizeMenuMapper;
	}

	@Override
	public int countByCriteria(WxCustomizeMenuCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

}